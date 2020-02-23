package base.worker_thread;


import java.util.Arrays;

public class Channel {
    private final static int MAX_REQUEST=100;
    private final Request[] requestQueue;
    private int head;
    private int tail;
    private int count;
    private final WorkThread[] workPool;
    public Channel(int workers){
        this.requestQueue=new Request[MAX_REQUEST];
        this.head=0;
        this.tail=0;
        this.count=0;
        this.workPool=new WorkThread[workers];
        this.init();
    }

    private void init() {
        for (int i=0;i<workPool.length;i++){
            workPool[i]=new WorkThread("Worker - "+i,this);
        }
    }
    public void startWorker(){
        Arrays.asList(workPool).forEach(WorkThread::start);
    }

    public synchronized void put(Request request){
        while (count>requestQueue.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.requestQueue[tail]=request;
        this.tail=(tail+1)%requestQueue.length;
        this.count++;
        this.notifyAll();
    }
    public synchronized Request take(){
        while (count<=0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request=this.requestQueue[head];
        this.head=(this.head+1)%this.requestQueue.length;
        this.count--;
        this.notifyAll();
        return request;
    }
}
