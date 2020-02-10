package base.showlock;

import static java.lang.Thread.sleep;

public class BooleanLockTest {
    public static void main(String[] args) {
        final BooleanLock booleanLock=new BooleanLock();
        Runnable BLRunnable =new Runnable(){
            public void run() {
                Long beginTime=System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName()+"要开始跑了"+beginTime);
                try {
                    booleanLock.lock(15000L);
                    System.out.println(Thread.currentThread().getName()+"要开始工作了");
                    sleep(10000);
                    System.out.println(Thread.currentThread().getName()+"要结束工作了"+"当前阻塞线程"+booleanLock.getBlockSize());
                    booleanLock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (LOCK.TimeOutException e) {
                    System.out.println(e+Long.toString(System.currentTimeMillis()-beginTime));
                }
            }
        };
        new Thread(BLRunnable,"线程一").start();
        new Thread(BLRunnable,"线程二").start();
        new Thread(BLRunnable,"线程三").start();
        new Thread(BLRunnable,"线程四").start();
    }
}
