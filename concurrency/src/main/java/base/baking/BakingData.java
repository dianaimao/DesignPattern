package base.baking;

public class BakingData {
    private String fileName;
    private String content;
    private boolean changed;
    public BakingData(String fileName,String content) {
        this.fileName=fileName;
        this.content=content;
        this.changed=true;
    }
    public synchronized void changeData(String newContent){
        this.content=newContent;
        this.changed=true;
    }
    public synchronized void save(){
        if (!changed){
            return;
        }
        dosave();
        this.changed=false;
    }

    private void dosave() {
        //todo save
    }
}
