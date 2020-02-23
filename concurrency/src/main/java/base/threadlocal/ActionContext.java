package base.threadlocal;

public class ActionContext {
    private final ThreadLocal<Context> threadLocal=new ThreadLocal<Context>(){
        @Override
        protected Context initialValue(){
            return new Context();
        }
    };
    private static class ContextHold{
        private final static ActionContext actionContext=new ActionContext();
    }
    public static ActionContext getActionContext(){
        return ContextHold.actionContext;
    }
    public Context getContext(){
        return threadLocal.get();
    }

}
