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

            String[] command = input.split(" ", 3);

            if (command != null && command.length > 0) {
                String cmd=command[0];

                if ("quit".equalsIgnoreCase(cmd)) {
                    out.println("[SERVER]     Client Disconnected");
                    break;

                }
                else if(command.length==2)
                {
                    read_profit(out,command);
                }

                else if(command.length==3)
                {
                    handle_profit(out,command);
                }

                else
                {
                    out.println("[SERVER]     Invalid Command");
                }

            }
        }

        clientSocket.close();

    }



//==================================================================================================================================================================

    public void handle_profit(PrintStream out, String[] command) throws IOException
    {

        String symbol=command[0];
        String security=command[1];
        String prfit= command[2];

        float val2=Float.parseFloat(prfit);


        System.out.println("[CLIENT]    "+symbol+" "+security+" "+prfit);


        Float val=csvreader.profit(symbol,security,prfit);

        out.println();
        //out.println("Test 0 : "+val);
        //out.println();

        if(val==0)
        {
            out.println("[SERVER]" + "     "+"-1");
        }

        else
        {
            out.println("[SERVER]" + "     " +"0");
        }

    }




    public void read_profit(PrintStream out, String[] command) throws IOException
    {

        String symbol=command[0];
        String security=command[1];



        System.out.println("[CLIENT]    "+symbol+" "+security);


        Float val=csvreader.read(symbol,security);

        out.println();
        out.println("Test 0 : "+val);
        out.println();

        if(val==0)
        {
            out.println("[SERVER]" + "     "+"-1");
        }

        else
        {
            out.println("[SERVER]" + "     " +val);
        }

    }
}
