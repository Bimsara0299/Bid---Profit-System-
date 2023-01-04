import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server
{
    private BufferedReader br=null;
    private PrintStream pr=null;


    public void start() throws IOException {
        int port=2021;  //Server Port
        ServerSocket serverSocket = new ServerSocket(port);
        while(true)
        {
            System.out.println("[SERVER]    Waiting for Clients....");
            Socket clientSocket=serverSocket.accept();      //Accepting client connections

            //OutputStream outputStream = clientSocket.getOutputStream();
            //outputStream.write("[SERVER]    Client Connected\n".getBytes());

            br=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            pr=new PrintStream(clientSocket.getOutputStream());


            pr.println("[SERVER]     Client Connected");


            Server_Handler Handle = new Server_Handler(clientSocket);
            Handle.start();




        }
    }
}
