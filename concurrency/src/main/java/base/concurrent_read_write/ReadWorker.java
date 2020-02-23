package base.concurrent_read_write;

public class ReadWorker extends Thread {
    private SharedData data;
    public ReadWorker(SharedData data){
        this.data=data;
    }
    @Override
    public void run() {
        try {
            while (true){
                char[] readbuf=data.read();
                System.out.println(Thread.currentThread().getName()+" reading "+String.valueOf(readbuf));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
