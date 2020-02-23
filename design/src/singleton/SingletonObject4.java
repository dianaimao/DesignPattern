package singleton;

/**
 * 优雅实现懒加载
 * 在调用getInstance的时候，主动初始化构造这个单实例
 * static 线程友好，确保实例只初始化一次
 */
public class SingletonObject4 {
    private SingletonObject4() {
    }

    private static class InstanceHolder {
        private final static SingletonObject4 instance = new SingletonObject4();
    }

    public SingletonObject4 getInstance() {
        return InstanceHolder.instance;
    }
}
