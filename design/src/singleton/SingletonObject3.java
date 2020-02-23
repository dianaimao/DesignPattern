package singleton;

/**
 * double check:双重  不用每次同步锁
 * volatile ：保证实例内容完全创建完，其他线程才能读;但不让优化、重排序，也影响性能和效率
 */
public class SingletonObject3 {
    private volatile static SingletonObject3 instance;

    private SingletonObject3() {
    }

    public SingletonObject3 getInstance() {
        if (null == instance) {
            synchronized (instance) {
                if (null == instance) {
                    instance = new SingletonObject3();
                }
            }
        }
        return instance;
    }
}
