package base.count_down;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * 第一阶段有多个线程要处理任务，第二阶段要等第一阶段结束才能执行
 * 不使用join方法    使用JDK的CountDown
 */
public class CountDownClient {
    public static void main(String[] args) throws InterruptedException {
        Random random=new Random(System.currentTimeMillis());
        final CountDown latch=new CountDown(5);
        IntStream.rangeClosed(1,5).forEach(i->
                new Thread(()->{
                    System.out.println(Thread.currentThread().getName()+" is working !");
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.down();
                },String.valueOf(i)).start()
        );
        latch.await();
        System.out.println("第一阶段多线程任务全部结束，第二阶段开始");
        System.out.println("..........");
        System.out.println("Finish");
    }
}
