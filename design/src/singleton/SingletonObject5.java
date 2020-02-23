package singleton;

/**
 * 枚举类型构造函数只能被装载一次
 */
public class SingletonObject5 {
    private SingletonObject5(){}
    private enum Singleton{
        INSTANCE;
        private final SingletonObject5 instance;
        Singleton(){
            instance=new SingletonObject5();
        }

        public SingletonObject5 getInstance() {
            return instance;
        }
    }
    public SingletonObject5 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
}
