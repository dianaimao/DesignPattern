package base.two_phase_termination;

public class AppServerClient {
    public static void main(String[] args) {
        AppServer appServer=new AppServer(13345);
        appServer.start();
    }
}
