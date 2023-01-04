import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        // Milestone 1 : Create the CSV reader and populate datastructure


        //CSV_Reader csvreader = new CSV_Reader("stocks.csv");    // CSVreader
        //csvreader.read();   //item_map gets populated with data


        // Milestone 2 : Create a server and accept 1 connection

        Server server = new Server();   // Server
        server.start(); // Server starts running here.


        // Milestone 3 : Modify server to accept multiple connections (multi-threading)

    }
}
