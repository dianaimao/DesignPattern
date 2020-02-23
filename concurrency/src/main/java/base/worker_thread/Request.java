package base.worker_thread;

public class Request {
    private String name;
    private final int number;
    public Request(String name,final int number){
        this.name=name;
        this.number=number;
    }

    public void execute(){
        System.out.println(Thread.currentThread().getName()+" executed " +this);
    }
    @Override
    public String toString() {
        return "Request -》NO."+number+" Name. "+name;
    }
}
