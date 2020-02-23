package base.concurrent_read_write;

import static java.lang.Thread.sleep;

public class SharedData {
    private char[] buffer;
    private ReadWriteLock readWriteLock=new ReadWriteLock();
    public SharedData(int size){
        buffer=new char[size];
        for (int i=0;i<size;i++){
            buffer[i]='*';
        }
    }
    public char[] read() throws InterruptedException {
        try {
            readWriteLock.readLock();
            return this.doRead();
        } finally {
            readWriteLock.readUnlock();
        }
    }
    public void write(char c) throws InterruptedException {
        try {
            readWriteLock.writeLock();
            for (int i=0;i<buffer.length;i++){
                doWrite(c);
            }
        } finally {
            readWriteLock.writeUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i=0;i<buffer.length;i++){
            buffer[i]=c;
            slowly(10);
        }
    }

    private char[] doRead() {
        char[] newBuffer=new char[buffer.length];
        for (int i=0;i<buffer.length;i++){
            newBuffer[i]=buffer[i];
        }
        slowly(50);
        return newBuffer;
    }
    public void slowly(int ms){
        try {
            sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
