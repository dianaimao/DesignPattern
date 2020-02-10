package base;

import static java.lang.Thread.sleep;

public class ThreadWFTEST {
    public static void main(String[] args) {
         final Object LOCK=new Object();
        new Thread(new Runnable() {
            public void run() {
                synchronized (LOCK){
                    try {
                        while (true){
                        System.out.println("我要wait");
                        LOCK.wait();}
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("到我了！");
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("我要 notify");
                synchronized (LOCK){
                  LOCK.notify();
                }
            }
        }).start();
    }
}

