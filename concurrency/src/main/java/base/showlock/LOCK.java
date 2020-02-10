package base.showlock;

import java.util.Collection;

public interface LOCK {
    class TimeOutException extends Exception{
        TimeOutException(String message){
            super(message);
        }
    }
    void lock() throws InterruptedException;

    void lock(Long mills) throws InterruptedException,TimeOutException;

    void unlock();

    Collection<Thread> getBlockThread();

    int getBlockSize();
}
