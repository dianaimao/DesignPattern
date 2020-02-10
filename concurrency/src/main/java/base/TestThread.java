package base;

public class TestThread {
    public static void main(String[] args) {
        Thread thread1=new Thread(){
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    System.out.println(Thread.currentThread().getName()+"==="+i);
                }
            }
        };
        Thread thread2=new Thread(){
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    System.out.println(Thread.currentThread().getName()+"==="+i);
                }
            }
        };
        thread1.start();
        thread2.start();
    }
}
