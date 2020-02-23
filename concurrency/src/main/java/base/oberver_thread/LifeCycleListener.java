package base.oberver_thread;

public interface LifeCycleListener {

    void onEvent(ObserverableRunnable.RunnableEvent event);
}
