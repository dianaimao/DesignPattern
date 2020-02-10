package base;

/**
 * 两个线程调用分别调用同一个锁的代码块，并重复执行。方法会交杂的执行。通过flag来判断此时是否要执行这方法
 * 通过flag判断是否wait()(即使还未生产时消费者线程抢到锁，会让出锁)
 * 但此时wait线程需要另一个线程notify(),进入可运行的状态
 * notify()另一个锁，还需要让出锁，并且flag修改
 */
public class ProduceConsumerWF {
    public static void main(String[] args) {
        final PC pc=new PC();
        new Thread(){
            @Override
            public void run() {
                int i=1;
                while (i<100){
                    pc.produce();
                    i++;
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                while (true){
                pc.consumer();
            }
            }
        }.start();
    }
}
class PC{
    private Object lock=new Object();
    private boolean produceFlag=true;   //需要生产
    private int i=0;
    public void produce(){
        synchronized (lock){
            if (produceFlag){
                i++;
                System.out.println("生产第"+i+"个");
                produceFlag=false;
                lock.notify();
            }else {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void consumer(){
        synchronized (lock){
            if (produceFlag){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("消费第"+i+"个");
                lock.notify();
                produceFlag=true;
            }
        }
    }
}
