package singleton;

/**
 *普通的单例模式
 * 利用了static只加载一次
 */
public class SingletonObject1 {
    private static SingletonObject1 instance=new SingletonObject1();
    private SingletonObject1(){
    }
    public SingletonObject1 getInstance(){
        return SingletonObject1.instance;
    }
}
