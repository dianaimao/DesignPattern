public class Singleton {
    private Singleton(){};

    private static class SingletonFactory{
        private static Singleton instance=new Singleton();
    }
    public static Singleton getInstance(){
        return SingletonFactory.instance;
    }
    private void say(){
        System.out.println("Singleton is mine");
    }

    public static void main(String[] args) {
        Singleton singleton= Singleton.getInstance();
        singleton.say();
    }
}
