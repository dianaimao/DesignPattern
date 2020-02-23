package base.two_phase_termination;

import java.util.Random;

public class CountIncrement extends Thread {
    private volatile boolean flag = false;
    private int count = 0;
    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            while (!flag) {
                System.out.println(Thread.currentThread().getName() + " " + count++);
                Thread.sleep(random.nextInt());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.clean();
        }
    }

    private void clean() {
        System.out.println("do some clean work~~");
    }
    private void close(){
        this.flag=true;
        this.interrupt();
    }

}
