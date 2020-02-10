package base;

/**
 * 银行叫号 Runable
 */
public class TicketRunable {
    public static void main(String[] args) {
        TicketAble ticketAble=new TicketAble();
        Thread thread1=new Thread(ticketAble,"窗口一号");
        Thread thread2=new Thread(ticketAble,"窗口二号");
        Thread thread3=new Thread(ticketAble,"窗口三号");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
class TicketAble implements Runnable{
    private final int max = 50;
    private static int index = 1;

    public void run() {
        while (index < max) {
            System.out.println("当前" + Thread.currentThread().getName() + "==" + index++);
        }
    }
}