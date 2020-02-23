package base.count_down;

public class CountDown {
    private final int total;
    private int count=0;

    public CountDown(int total){
        this.total=total;
    }

    public void down(){
        synchronized (this){
            this.count++;
            this.notifyAll();
        }
    }
    public void await(){
        synchronized (this){
            while (total!=count){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
