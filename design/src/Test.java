public class Test {
    public static void main(String[] args) {
        FactoryFruit factoryFruit=new FactoryFruit();
        Fruit myFruit=factoryFruit.getFruit("orange");
        myFruit.say();
    }
}

abstract class Fruit{
    void say(){};
}
class Melon extends Fruit{
    String name;
    Melon(String name){
        this.name=name;
    }
    public void say(){
        System.out.println("Melon say hello~\nMy name is "+name);
    }
}
class Orange extends Fruit{
    String name;
    Orange(String name){
        this.name=name;
    }
    public void say(){
        System.out.println("Orange say hello~\nMy name is "+name);
    }
}
class FactoryFruit{
    Fruit getFruit(String fruitName){
        if ("melon".equals(fruitName)){
            return new Melon("BeatifuleMelon");
        }else {
            return new Orange("BeatifuleOrange");
        }
    }
}