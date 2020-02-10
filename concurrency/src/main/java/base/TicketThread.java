package base;

/**
 * 银行叫号 Thread
 */
public class TicketThread extends Thread {
    private final int max=50;
    private static int index=1;
    private String name;
    TicketThread(String name){
        this.name=name;
    }
    @Override
    public void run() {
        while(index<max) {
            System.out.println("当前" + name + "==" + index++);
        }
    }

    public static void main(String[] args) {
        TicketThread ticketThread1=new TicketThread("窗口一号");
        TicketThread ticketThread2=new TicketThread("窗口二号");
        TicketThread ticketThread3=new TicketThread("窗口三号");
        ticketThread1.start();
        ticketThread2.start();
        ticketThread3.start();
        ThreadGroup threadGroup=Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup.activeCount());
        Thread[] threads=new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for(Thread temp:threads){
            System.out.println(temp);
        }
    }
}
