package base;

import static java.lang.Thread.sleep;

/**
 * 同步代码块：selfClassName.clas  Object  this
 * 同步方法：静态方法    非静态方法
 *
 * this锁：同一个实例对象，一个时间只能有一个线程能访问带this锁的方法/代码块、
 *      同步代码块   Object this 、非静态方法
 * 类锁：类，一个时间只能有一个线程能访问带类锁的方法/代码块
 *      同步代码块 selfClassName.class 、静态方法
 */

public class ThreadSynchronized {
    public static void main(String[] args) {
        final TSynchronized tSynchronized1=new TSynchronized();
        final TSynchronized tSynchronized2=new TSynchronized();
        new Thread(){
            @Override
            public void run() {
                TSynchronized.synStatic1();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                TSynchronized.synStatic2();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                tSynchronized1.synMethod1();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                tSynchronized1.synMethod2();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                tSynchronized1.synBlockClass();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                tSynchronized2.synMethod1();
            }
        }.start();
    }
}
class TSynchronized{
    private static Object MONITOR=new Object();

    public void synBlockClass(){
        synchronized (TSynchronized.class){
            System.out.println("class方法块");
            try {
                sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized static void synStatic1(){
        System.out.println("Method synStatic1----");
        try {
            sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized static void synStatic2(){
        System.out.println("Method synStatic1----");
        try {
            sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized  void synMethod1(){
        System.out.println("Method syn1----");
        try {
            sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized  void synMethod2(){
        System.out.println("Method syn2----");
        try {
            sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
