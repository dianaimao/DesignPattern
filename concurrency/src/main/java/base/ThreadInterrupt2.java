package base;

import static java.lang.Thread.sleep;
/**
 * 情况：线程中有个任务阻塞,不能判断interrpt标志，如何优雅的打断？
 * 同一个ThreadGroup，在一个t线程中新建个inner线程执行会阻塞的任务，并设置守护线程。inner.join()等待inner线程
 * 任务结束，之后还会执行一些其他任务。打断t线程，会执行join()方法之后的任务，然后t线程结束。同一组的守护线程
 * inner也将结束。
 */
public class ThreadInterrupt2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(){
            @Override
            public void run() {
                Thread inThread=new Thread(){
                    @Override
                    public void run() {
                        while (true){

                        }
                    }
                };
                inThread.setDaemon(true);
                inThread.start();
                try {
                    inThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("这是非守护线程");
            }
        };
        thread.start();
        sleep(5000);
        System.out.println("开始打断");
        thread.interrupt();
    }
}
