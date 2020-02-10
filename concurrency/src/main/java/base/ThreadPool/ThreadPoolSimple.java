package base.ThreadPool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 包装Thread，让Thread线程执行完任务就wait(),永远挂起不让它挂掉。通过两个while语句，外层while死循环让线程永远执行，
 * 内层while判断wait()条件(任务队列是否为空)，不为空就取出并执行。在添加任务的时候notifyAll().
 * 巧思：通过不断添加Runnable队列,线程不断取任务并执行Runnable.run()
 * 线程每次执行下一次功能代码时，改变任务状态，可以查看线程实时的状态。
 * 注意:添加任务方法、线程run方法都对任务队列有操作，要保证原子性，添加synchronized同步锁
 * run方法wait()被打断了，会造成逻辑异常，添加break标签--》打断后重新判断任务队列
 *
 * 添加拒绝策略：策略设计模式，策略接口，通过抛出异常显示实现的拒绝策略。在初始化时给线程池一个策略接口实现，
 *              可以改变使用的拒绝策略。本示例策略：线程超过指定的大小，就执行拒绝策略，不能添加任务
 * 关闭线程池：改变外层while的条件，让线程池的线程不能一直运行，并且把block的线程打断。在running的线程会等待它结束
 * 扩充或缩小线程数：需要监视当前任务数量和线程池的数量，让线程池这个线程继承Thread，自己start()来监视和改变线程池中的数量。
 *                    该线程也可以用来监控当前线程的状态。
 */
public class ThreadPoolSimple extends Thread {
    private final static LinkedList<Runnable> taskQueue = new LinkedList<Runnable>();
    private final static List<ThreadP> threadQueue = new ArrayList<ThreadP>();
    private final static ThreadGroup threadGroup = new ThreadGroup("kzk_ThreadGroup");
    private final static int defalutTaskSize = 100;
    private final static DiscardPolicy defalutDiscardPolicy = () -> {
        throw new DiscardException("拒绝策略");
    };
    private static int taskSize;
    private static DiscardPolicy discardPolicy;
    private static int nameInt = 0;
    private boolean destory = false;
    private static int size;  //当前线程数
    private int min;
    private int active;
    private int max;

    public ThreadPoolSimple() {
        this(4, 8, 12, defalutTaskSize, defalutDiscardPolicy);
    }

    public ThreadPoolSimple(int min, int active, int max, int taskSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.taskSize = taskSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    public void init() {
        size = this.min;
        for (int i = 0; i < this.min; i++) {
            createWork();
        }
        this.start();
    }

    private void createWork() {
        ThreadP threadP = new ThreadP(threadGroup, "threadPool-" + nameInt++);
        threadP.start();
        threadQueue.add(threadP);
    }

    public void addTask(Runnable runnable) {

        synchronized (taskQueue) {
            if (this.destory) {
                System.out.println("fdfdfasdfsgad");
                throw new IllegalStateException("thread poll is destory!!!");
            }
            if (taskQueue.size() > taskSize) {
                discardPolicy.diacrad();
            }
            taskQueue.addLast(runnable);
            taskQueue.notify();
        }
    }

    public void shutdown() throws InterruptedException {
        while (!taskQueue.isEmpty()) {
            sleep(50L);
        }
        int iniValue = threadQueue.size();
        while (iniValue > 0) {
            for (ThreadP threadP : threadQueue) {
                if (threadP.taskStauts == TaskStauts.BLOCK) {
                    threadP.interrupt();
                    threadP.taskStauts = TaskStauts.Dead;
                    iniValue--;
                } else {
                    sleep(10);
                }
            }
        }
        System.out.println("Threadpoll is disposed!!!!");
        this.destory = true;
    }

    @Override
    public void run() {
        while (!destory) {
            System.out.printf("Threadpool #min=%d,#active=%d,#max=%d,#threadSize=%d,#taskSize=%d\n",
                    this.min, this.max, this.max, threadQueue.size(), taskQueue.size());
            try {
                sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (taskQueue.size() > active && size < active) {
                for (int i = size; i < active; i++) {
                    createWork();
                }
                System.out.println("The pool incremented to active!!");
                size = active;
            } else if (taskQueue.size() > max && size < max) {
                for (int i = size; i < max; i++) {
                    createWork();
                }
                System.out.println("The pool incremented to max!!");
                size = max;
            }
            if (taskQueue.isEmpty() && size > active) {
                synchronized (taskQueue) {
                    int releaseSize = size = active;
                    for (Iterator<ThreadP> it = threadQueue.iterator(); it.hasNext(); ) {
                        if (releaseSize < 0)
                            break;
                        ThreadP threadP = it.next();
                        threadP.taskStauts = TaskStauts.Dead;
                        threadP.interrupt();
                        it.remove();
                        releaseSize--;
                    }
                    size = active;
                }
            }
        }
    }

    private int getTaskSize() {
        return taskQueue.size();
    }

    private enum TaskStauts {
        FREE, BLOCK, RUNNING, Dead
    }

    public static class DiscardException extends RuntimeException {
        DiscardException(String message) {
            super(message);
        }
    }

    interface DiscardPolicy {
        void diacrad() throws DiscardException;
    }

    private static class ThreadP extends Thread {
        private TaskStauts taskStauts;

        public ThreadP(ThreadGroup threadGroup, String name) {
            super(threadGroup, name);
        }

        @Override
        public void run() {
            OUTER:
            while (taskStauts != TaskStauts.Dead) {
                Runnable runnable;
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty()) {
                        try {
                            taskStauts = TaskStauts.BLOCK;
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            break OUTER;    //阻塞时被打断
                        }
                    }
                    runnable = taskQueue.removeFirst();
                }
                taskStauts = TaskStauts.RUNNING;
                runnable.run();
                taskStauts = TaskStauts.FREE;
            }
        }
    }

    public static void main(String[] args) {
        final ThreadPoolSimple threadPoolSimple = new ThreadPoolSimple();

        IntStream.rangeClosed(0, 100).forEach(i ->
                {
                    System.out.println("task：" + i + "申请任务");
                    threadPoolSimple.addTask(new Runnable() {
                        public void run() {
                            System.out.println(Thread.currentThread().getName() + "===>正在执行任务" + i + "    当前任务队列数量==" + threadPoolSimple.getTaskSize());
                            try {
                                sleep(1000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
        );
        System.out.println("ttttttt");
        try {
            threadPoolSimple.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolSimple.addTask(() -> {
            System.out.println("new add Task~");
        });
    }
}
