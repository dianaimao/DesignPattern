package base.class_load;

/**
 * 类加载
 * new Singleton 在 1   2  位置不同
 */
public class Singleton {
    //1
    /**
     * main调用静态变量开始加载类，new初始化x=0，y=0   ---》构造函数x=1,y=1 --->new 初始化完成
     *                              new完需要给x赋正确的值初始化    x=0，y=1
     * Singleton类链接的 准备阶段 singleton=null     x=0， y=0
     * Singleton类的初始化阶段（赋正确的值）
     * singleton  调用构造函数    x=1,  y=1
     * x                          x=0   y=1
     */
    private static int x=0;
    private static int y;
    //2
    /**
     * main调用静态变量开始加载类，x=0，y=0,singleton=null(链接阶段，准备工作 初始化默认值)
     *                             初始化阶段（正确的值）x=0,y=0,singleton 初始化构造函数  y=1,x=1
     */
    private static Singleton singleton=new Singleton();
    Singleton(){
        x++;
        y++;
    }
    public Singleton getInstance(){
        return singleton;
    }

    public static void main(String[] args) {
        System.out.println(Singleton.x);
        System.out.println(Singleton.y);
    }
}
