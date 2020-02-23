package base.threadlocal;

public class QueryFromDBAction {
    public void execute(){
        try {
            Thread.sleep(1000L);
            String name="Alex";
            ActionContext.getActionContext().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
