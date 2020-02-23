package base.two_phase_termination;

import java.io.*;
import java.net.Socket;

public class ClientHandle implements Runnable {
    private final Socket socket;
    private volatile boolean running=true;
    public ClientHandle(Socket sockets){
        this.socket=sockets;
    }
    @Override
    public void run() {
        try {
            InputStream inputStream=socket.getInputStream();
            OutputStream outputStream=socket.getOutputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter printStream=new PrintWriter(outputStream);
            while(running){
                String message=br.readLine();
                if (message==null){
                    break;
                }
                System.out.println("Come from client >"+message);
                printStream.write("echo "+message+"\n");
                printStream.flush();
            }
        } catch (IOException e) {
            this.running=true;
        }finally {
            this.stop();
        }
    }
    public void stop()  {
        if (!running){
            return;
        }
        this.running=false;
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
