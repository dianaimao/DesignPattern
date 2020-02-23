package base.threadlocal;

public class QueryFromHttpAction {
    public void execute(){
        Context context=ActionContext.getActionContext().getContext();
        String name=context.getName();
        String cardId=getCardId(name);
        context.setCardId(cardId);
    }
    private String getCardId(String name){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "4233653245634"+Thread.currentThread().getId();
    }
}
