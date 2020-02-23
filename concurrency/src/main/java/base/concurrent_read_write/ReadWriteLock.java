package base.concurrent_read_write;

public class ReadWriteLock {
    private int readingReaders = 0;
    private int readingWaiters = 0;
    private int writtingWriters = 0;
    private int writtingWaiters = 0;
    private boolean perfectWriter=true;
    public ReadWriteLock(){
        this(true);
    }
    public ReadWriteLock(boolean perfectWriter){
        this.perfectWriter=perfectWriter;
    }

    public synchronized void readLock() throws InterruptedException {
        readingWaiters++;
        try {
            while (writtingWriters > 0||(perfectWriter&&writtingWaiters>0)){
                this.wait();
            }
            this.readingReaders++;
        } finally {
            this.readingWaiters--;
        }
    }
    public synchronized void readUnlock(){
        this.readingReaders--;
        this.notifyAll();
    }
    public synchronized void writeLock() throws InterruptedException {
        this.writtingWaiters++;
        try {
            while (writtingWriters > 0||readingReaders>0){
                this.wait();
            }
            this.writtingWriters++;
        } finally {
            this.writtingWaiters--;
        }
    }
    public synchronized void writeUnlock(){
        this.writtingWriters--;
        this.notifyAll();
    }
}

