package base.threadlocal;

public class ExecutionTask implements Runnable{

    private QueryFromDBAction queryFromDBAction =new QueryFromDBAction();

    private QueryFromHttpAction queryFromHttpAction=new QueryFromHttpAction();
    @Override
    public void run() {
        final Context context=ActionContext.getActionContext().getContext();
        queryFromDBAction.execute();
        System.out.println("The name is query successful ");
        queryFromHttpAction.execute();
        System.out.println("The ID is query successful");
        System.out.println("The name is "+context.getName()+" and the ID is "+context.getCardId());
    }
}
