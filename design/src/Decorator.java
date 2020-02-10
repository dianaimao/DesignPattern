public class Decorator implements intGun{
    Gun gun=null;
    Decorator(Gun gun){
        this.gun=gun;
    }
    @Override
    public void shoot() {
        System.out.println("哔哔哔哔哔");
        gun.shoot();
        System.out.println("BOOM");
    }
    public static void main(String[] args) {
        Decorator decorator=new Decorator(new Gun());
        decorator.shoot();
    }
}
interface  intGun{
    void shoot();
}
class Gun implements intGun{
    @Override
    public void shoot() {
        System.out.println("咻咻咻");
    }
}
