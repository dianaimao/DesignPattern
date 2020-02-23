package base.concurrent_read_write;

/**
 * 利用的显示锁方法，共享资源分别有读方法和写方法，分别调用读写锁的读写方法。
 *      每次每个线程调用这个共享资源，都会调用读写方法并进入读写锁。此时同步方法，判断
 *      根据读写锁的规则判断是否执行操作或让这个线程wait阻塞，读写锁会保存当前的读写线程的数量
 *
 * 提高读写共享资源效率，又保证原子性。有线程读操作的时候，另外一个线程也能进行读操作。
 */
public class ReadWriteClient {
    public static void main(String[] args) {
        final SharedData sharedData=new SharedData(10);
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new WriterWorker(sharedData,"ttgdfsfgsdgsd").start();
        new WriterWorker(sharedData,"TTGDFSFGSDGSD").start();
    }
}
