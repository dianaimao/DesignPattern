package base.volatile_package;

public class VolatileTest {
    public volatile static int initValue=0;
    public static int maxValue=5;
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                int localValue=initValue;
                while (initValue<maxValue){
                    if (localValue!=initValue){
                        System.out.printf("This value is updating [%d]\n",initValue);
                        localValue=initValue;
                    }
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                int localValue=initValue;
                while (initValue<maxValue){
                    System.out.printf("update value %d\n",++localValue);
                    initValue=localValue;
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }
}
