package base.thread_future;

import static java.lang.Thread.sleep;

/**
 * FutureService把futureService和FutureTask桥接起来，把结果存在AsynFuture中
 * 把任务给FutureService--》开启一个线程跑任务--》线程完成任务会执行AsynFuture的done，改变done标识符
 *      --》主线程才能调用AsynFuture的get()获取保存在其中的结果
 *
 * 缺点：过一个段时间要取结果
 */
public class FutureTest {
    public static void main(String[] args) throws InterruptedException {
        FutureService futureService = new FutureService();
        futureService.submit(() -> {
            try {
                sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Finish";
        }, System.out::println);
        System.out.println("==============");
        System.out.println(" do other thing ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==============");
    }
}
