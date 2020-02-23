package base.thread_future;

public interface Future<T> {
    T get() throws InterruptedException;
}
