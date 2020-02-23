package singleton;

/**
 * 懒加载：调用时才创建单实例
 * 同步锁，影响效率
 */
public class SingletonObject2 {
    private static SingletonObject2 instance;

    private SingletonObject2() {
    }
    public synchronized SingletonObject2 getInstance(){
        if (null==instance){
            instance=new SingletonObject2();
        }
        return instance;
    }
}
