package base.oberver_thread;

import java.util.List;

/**
 * 观察线程线程状态，状态改变告诉观察者   RunnableEvent 被观察者 事件   LifeCycleListener 观察者
 * RunnableEvent
 *      初始化 需要观察者
 *      事件改变方法  调用观察者的事件方法
 *      RunnableEvent  内部类 事件   被观察者将事件传递给观察者
 * LifeCycleListener
 *      onEvent 得到事件响应方法
 *      注意   多线程会同时调用  加同步锁
 *
 * 其他线程实现观察者或被观察者：
 *  被观察者 线程
 *      实现ObserverableRunnable
 *      初始化 需要观察者实例
 *      事件发生时 创建ObserverableRunnable的事件改变方法
 *
 * 观察者
 *      实现 LifeCycleListener
 *      实现onEvent事件发生时的操作
 *
 */
public class ThreadLifeCycleObserver implements LifeCycleListener{
    private  final Object Lock=new Object();
    public void concurrentQuery(List<String> ids){
        if (ids==null||ids.isEmpty()){
            return;
        }
        ids.stream().forEach(id->new Thread(new ObserverableRunnable(this) {
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunnableState.RUNNING,Thread.currentThread(),null));
                    System.out.println("query for the id"+id);
                    Thread.sleep(1000L);
                    notifyChange(new RunnableEvent(RunnableState.DONE,Thread.currentThread(),null));

                }catch (Exception e){
                    notifyChange(new RunnableEvent(RunnableState.ERROR,Thread.currentThread(),e));

                }
            }
        },String.valueOf(id)).start());
    }
    @Override
    public void onEvent(ObserverableRunnable.RunnableEvent event) {
            synchronized (Lock){
                System.out.println("The runnable {"+event.getThread().getName()+"} data change and state is {"+event.getState()+"}");
                if (event.getCause()!=null){
                    System.out.println("The runnable {"+event.getThread().getName()+"} process failed");
                    event.getCause().printStackTrace();
                }
            }
    }
}
