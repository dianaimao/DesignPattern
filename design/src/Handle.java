public class Handle {
    public static void main(String[] args) {
        MyHandle myHandle1=new MyHandle("Handle First");
        MyHandle myHandle2=new MyHandle("Handle Second");
        MyHandle myHandle3=new MyHandle("Handle Third");

        myHandle1.setHandle(myHandle2);
        myHandle2.setHandle(myHandle3);
        myHandle1.oprater();
    }
}
interface IntHandle{
    void oprater();
}
class MyHandle implements IntHandle{
    private IntHandle handle;
    private String name;
    MyHandle(String name){
        this.name=name;
    }
    public IntHandle getHandle(){
        return handle;
    }
    public void setHandle(IntHandle handle){
        this.handle=handle;
    }
    @Override
    public void oprater() {
        System.out.println("\nHandle Name is "+name);
        if (getHandle()!=null){
            getHandle().oprater();
        }
    }
}