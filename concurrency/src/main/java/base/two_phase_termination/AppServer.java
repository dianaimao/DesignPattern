package base.two_phase_termination;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppServer extends Thread{
    private final int port;
    private static final int DEFAULT_PORT=12722;
    private volatile boolean start=true;
    private List<ClientHandle> clientHandlers=new ArrayList<>();
    private  final ExecutorService executor= Executors.newFixedThreadPool(10);
    private  ServerSocket server;
    public AppServer(){
        this(DEFAULT_PORT);
    }
    public AppServer(int port){
        this.port=port;
    }

    @Override
    public void run() {
        try {
            server=new ServerSocket(port);
            while (start){
                Socket client=server.accept();
                ClientHandle clientHandle=new ClientHandle(client);
                executor.submit(clientHandle);
                this.clientHandlers.add(clientHandle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            this.dispose();

        }
    }

    private void dispose()  {
        this.clientHandlers.stream().forEach(ClientHandle::stop);
        this.executor.shutdown();
    }

    public void shutdown() throws IOException {
        this.start=false;
        this.interrupt();
        this.server.close();
    }
}
