package base.class_load;

public class test {
    public static void main(String[] args) {
        System.out.println(son.i);
    }
}

class obj{
    public static int i=66;
    static {
        System.out.println("parent class loading");
    }
}

class son extends obj{
    public static int s=77;
    static {
        System.out.println("son class loading");
    }
}