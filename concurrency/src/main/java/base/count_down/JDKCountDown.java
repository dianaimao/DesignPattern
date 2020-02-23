package base.count_down;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class JDKCountDown {
    public static void main(String[] args) throws InterruptedException {
        Random random=new Random(System.currentTimeMillis());
        final CountDownLatch latch=new CountDownLatch(5);
        IntStream.rangeClosed(1,5).forEach(i->
                new Thread(()->{
                    System.out.println(Thread.currentThread().getName()+" is working !");
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                },String.valueOf(i)).start()
        );
        latch.await();
        System.out.println("第一阶段多线程任务全部结束，第二阶段开始");
        System.out.println("..........");
        System.out.println("Finish");
    }
}
