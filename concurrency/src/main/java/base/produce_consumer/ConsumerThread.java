package base.produce_consumer;

import java.util.Random;

public class ConsumerThread extends Thread {
    private final MessageQueue messageQueue;
    private final static Random random = new Random(System.currentTimeMillis());

    public ConsumerThread(MessageQueue messageQueue, int seq) {
        super(" CONSUMER-" + seq);
        this.messageQueue = messageQueue;

    }

    @Override
    public void run() {
        synchronized (messageQueue) {
            while (true) {
                try {
                    Message message = messageQueue.take();
                    System.out.println(Thread.currentThread().getName() + " take message" + message.getMessage());
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
