package base.active_objects;

public class ActiveObjectTest {
    public static void main(String[] args) {
        ActiveObject activeObject=ActiveObjectFactory.createActiveObject();
        new MakeClientThread(activeObject,"Alice").start();
        new MakeClientThread(activeObject,"Bobby").start();
        new DisplayClientThread("Chris",activeObject).start();
    }
}
