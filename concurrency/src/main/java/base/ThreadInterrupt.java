package base;

public class ThreadInterrupt {
    public static void main(String[] args) {
        Thread thread=new Thread(){
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    try {
                        sleep(1000000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("running===>"+i);
                }
            }
        };
        thread.start();
        System.out.println("interrupt 状态==》"+thread.isInterrupted());
        thread.interrupt();
        System.out.println("interrupt 状态==》"+thread.isInterrupted());
    }
}
