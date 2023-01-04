import java.io.*;
import java.net.Socket;

public class Server_Handler extends Thread
{
    CSV_Reader csvreader = new CSV_Reader();
    private BufferedReader in=null;
    private PrintStream out=null;
    private final Socket clientSocket;

    public Server_Handler(Socket clientSocket)
    {
        this.clientSocket=clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClient();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient() throws IOException {




        in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out=new PrintStream(clientSocket.getOutputStream());



        BufferedReader reader=new BufferedReader(in);
        String input;
        //out.print("[CLIENT]     ");


        while((input = reader.readLine())!=null) {

            String[] command = input.split(" ", 2);

            if (command != null && command.length > 0) {
                String cmd=command[0];

                if ("quit".equalsIgnoreCase(cmd)) {
                    out.println("[SERVER]     Client Disconnected");
                    break;

                }
                else if(command.length==1)
                {
                    display_bid(out,command);
                }
                else if(command.length==2)
                {
                    handle_bid(out,command);
                }
                else
                {
                    out.println("[SERVER]     Invalid Command");
                }

            }
        }

        clientSocket.close();

    }

    public void display_bid(PrintStream out, String[] command) throws IOException {
        String symbol=command[0];

        System.out.println("[CLIENT]    "+symbol);




        Float val=csvreader.read(symbol);

        out.println();
        if(val==0)

        {

            out.println("-1");
        }

        else
        {

            out.println("[SERVER]" + "     " + val);
        }

    }


//==================================================================================================================================================================

    public void handle_bid(PrintStream out, String[] command) throws IOException {

        String symbol=command[0];
        String bid= command[1];
        float val2=Float.parseFloat(bid);


        System.out.println("[CLIENT]    "+symbol+" "+bid);

        Float val3=csvreader.read(symbol);
        Float val=csvreader.bid(symbol,bid);

        out.println();


        if(val==0)
        {
            out.println("[SERVER]" + "     "+"-1");
        }

        else if(val3>=val2)
        {
            out.println("[SERVER]" + "     "+"-2");
        }

        else
        {
            out.println("[SERVER]" + "     " + val);
        }

    }
}
