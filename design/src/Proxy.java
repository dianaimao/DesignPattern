public class Proxy implements IntFood{
    Food food=null;
    Proxy(){
        this.food=new Food();
    }
    @Override
    public void eat() {
        System.out.println("=========");
        food.eat();
        System.out.println("=========");
    }
    public static void main(String[] args) {
        Proxy proxy=new Proxy();
        proxy.eat();
    }
}
interface IntFood{
    void eat();
}
class Food implements IntFood{
    @Override
    public void eat() {
        System.out.println("Delicious");
    }
}
