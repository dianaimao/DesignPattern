package base.threadlocal;

import java.util.stream.IntStream;

/**
 * 每一个线程都有一个上下文,可能要传参数给不同的任务，有些任务会依赖上个任务的参数，还会传多个参数
 *  上下文设计模式
 *      Context 保存多个参数的容器   ActionContext  单例的ThreadLocal<Context>,保证同一个线程多个任务可以传值
 *  优点：
 *      多个任务可以共享这个容器的值，上下文协调任务不用传多个参数，高效
 *      多个线程之间访问的同一个Context的内容不同，线程安全
 * 注意：
 *      同一个线程上下文任务结束时，注意清理容器的内容，下次在启用的时候，容器存在
 */
public class ContextTest {
    public static void main(String[] args) {
        IntStream.range(1,5).forEach(
                i->new Thread(new ExecutionTask()).start()
        );
    }
}
