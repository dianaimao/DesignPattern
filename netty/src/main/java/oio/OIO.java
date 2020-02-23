package oio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class OIO {
    public static void main(String[] args) throws IOException {
        final int portNumber=12456;
        ServerSocket serverSocket=new ServerSocket(portNumber);
        Socket socket=serverSocket.accept();
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out=new PrintWriter(socket.getOutputStream());
        String request,response;
        while ((request=in.readLine())!=null){
            if ("Done".equals(request)){
                break;
            }
            response=processRequest(request);
            out.write("server say-->"+response+"\n");
            out.flush();
        }
    }

    private static String processRequest(String request) {
        return request.toUpperCase();
    }
}
