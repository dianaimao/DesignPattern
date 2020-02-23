package base.worker_thread;

import java.util.Random;

public class WorkThread extends Thread{
    private final Channel channel;
    private final static Random random=new Random(System.currentTimeMillis());

    public WorkThread(String s, Channel channel) {
        super(s);
        this.channel=channel;
    }

    @Override
    public void run() {
        while (true) {
            channel.take().execute();
            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
