package base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * wait notify 综合
 * 10个线程最多MAX线程在工作，其他线程处于wait状态，直到工作的线程工作完成，其他线程争抢这个（锁）工作
 * 思路：10个线程共同一个LOCK锁（数组），每有一个线程工作向数组添加对象，结束工作去除一个对象。
 *      通过LOCK锁的数组大小条件，判定线程是否wait()。结束工作时notifyAll(),让所有等待的线程争抢这个锁。
 * 巧思：LOCK锁是ArrayList，以List大小判断，不需要新增一个变量来声明工作线程的数量
 * 注意：wait()要写在while循环中，在被唤醒时可再次判断需要wait()条件
 *
 */
public class ThreadWFA {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> works= new ArrayList<Thread>();
        Thread t;
        for (int i=0;i<10;i++){
           t=new WorkThread("Thread-"+i);
            t.start();
            works.add(t);
        }
        for (Thread thread:works){
            thread.join();
        }
        System.out.println("全部工作结束~");
    }
}
class WorkThread extends Thread{
    public static final LinkedList LOCK=new LinkedList();
    public static int MAX=5;
    private String name;
    WorkThread(String name){
        this.name=name;
    }
    @Override
    public void run() {
        synchronized(LOCK){
            System.out.println("线程==>"+this.name+"==获得锁");
            while (LOCK.size()>MAX){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            LOCK.add(new Object());
        }
        System.out.println("线程==>"+this.name+"==开始工作");
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (LOCK){
            System.out.println("线程==>"+this.name+"==结束工作");
            LOCK.removeFirst();
            LOCK.notifyAll();
        }
    }
}

