package base.worker_thread;

public class WorkClient {
    public static void main(String[] args) {
        final Channel channel=new Channel(5);
        channel.startWorker();

        new TransportThread("Alex",channel).start();
        new TransportThread("Jack",channel).start();
        new TransportThread("Willam",channel).start();

    }
}
