package base;

public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1=new Thread(){
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    System.out.println("Thread-1==>"+i);
                }
            }
        };
        Thread thread2=new Thread(){
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    System.out.println("Thread-2==>"+i);
                }
            }
        };
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        for (int i=0;i<100;i++){
            System.out.println("Main==>"+i);
        }    }
}
