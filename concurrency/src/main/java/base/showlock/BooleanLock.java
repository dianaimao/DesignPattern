package base.showlock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * 在多线程中共同使用一个这个显示锁的实例方法，运行到这个方法时，该线程需要获得这个实例的锁，线程wait状态(不会处于竞争状态)；
 * 直到获得锁的线程执行完并调用notifyAll()方法，唤醒所有等待这个锁的线程。
 * 在获取这个锁的同时，该显示锁会维护wait的线程组和线程等待时间(超时就放弃这个同步任务)，让锁具有超时功能
 * 巧思：通过Thread.currentThread()获得调用该方法的线程
 *      this获取该类，并可以调用wait()和notifyAll();
 *粗暴理解：多个线程在需要同步的方法块前执行这个类的同步方法，该同步方法会先判断你适合执行方法块(标志，其他线程是否正在执行)，
 *          不适合就wait()这。不wait的线程会正常执行直到调用notifyAll()方法
 */
public class BooleanLock implements LOCK {
    boolean initValue;
    Collection<Thread> threadBlockGroup=new ArrayList<Thread>();
    BooleanLock(){
        initValue=false;
    }
    public synchronized void lock() throws InterruptedException {
        while (initValue){
            threadBlockGroup.add(Thread.currentThread());
            this.wait();
        }
        threadBlockGroup.remove(Thread.currentThread());
        initValue=true;
    }
    //加锁的同时设置wait等待时间，超过时间没有获得锁；将不等待，执行其他任务
    public synchronized void lock(Long mills) throws InterruptedException, TimeOutException {
        if (mills<0){
            lock();
        }
        Long endTime=System.currentTimeMillis()+mills;
        while (initValue){
            Long hasRemain=endTime-System.currentTimeMillis();
            if (hasRemain<0){
               throw new TimeOutException(Thread.currentThread().getName()+"==Time Out");
            }
            threadBlockGroup.add(Thread.currentThread());
            this.wait(mills);
        }
        threadBlockGroup.remove(Thread.currentThread());
        initValue=true;
    }

    public synchronized void unlock() {
        System.out.println(Thread.currentThread().getName()+"释放锁");
        initValue=false;
        threadBlockGroup.clear();
        this.notifyAll();
    }
    //返回了阻塞线程的实例对象，可以被设置成为null的危险
    public Collection<Thread> getBlockThread() {
        return Collections.unmodifiableCollection(threadBlockGroup);
    }

    public int getBlockSize() {
        return threadBlockGroup.size();
    }
}
