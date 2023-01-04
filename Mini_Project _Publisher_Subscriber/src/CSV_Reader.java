import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSV_Reader {

    float val1;
    float val2;

    String filename = "stocks.csv";
    HashMap<String, Float> hmap1 = new HashMap<>();
    HashMap<Integer, Float> hmap2 = new HashMap<>();


    public HashMap<String, Float> read_csv() {

        String line;

        if (hmap1.isEmpty()) {

            try {
                BufferedReader readcsv = new BufferedReader(new FileReader(filename));
                readcsv.readLine();                            //Skip the header line
                while ((line = readcsv.readLine()) != null) {
                    String[] list = line.split(",");    //Splitting the values of each row

                    ArrayList<String> list2 = new ArrayList<>();  //Add the symbol to a String list

                    ArrayList<Float> list3 = new ArrayList<>();

                    list2.add(list[0]);                     //Add String values to string list
                    list3.add(Float.valueOf(list[3]));      //Add float values to a Float list


                    hmap1.put(list2.get(0), list3.get(0));   //populate hashmap with string list & float list
                    //System.out.println(hmap);


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return hmap1;
        }


        return hmap1;
    }

//========================================================================================================================================


    public HashMap<Integer, Float> read_csv_2() {

        String line;

        if (hmap2.isEmpty()) {

            try {
                BufferedReader readcsv = new BufferedReader(new FileReader(filename));
                readcsv.readLine();                            //Skip the header line
                while ((line = readcsv.readLine()) != null) {
                    String[] list = line.split(",");    //Splitting the values of each row

                    ArrayList<Integer> list2 = new ArrayList<>();  //Add the symbol to a String list

                    ArrayList<Float> list3 = new ArrayList<>();

                    list2.add(Integer.valueOf(list[2]));                     //Add String values to string list
                    list3.add(Float.valueOf(list[3]));      //Add float values to a Float list


                    hmap2.put(list2.get(0), list3.get(0));   //populate hashmap with string list & float list



                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return hmap2;
        }


        return hmap2;
    }


//===============================================================================================================================


    public Float profit(String symbol, String security, String prfit) throws IOException {

        int security2 = Integer.parseInt(security);
        float pr = Float.parseFloat(prfit);


        if (read_csv_2().containsKey(security2) && read_csv().containsKey(symbol))            //If the symbol presents on Hashmap
        {

            read_csv_2().put(security2, pr);
            read_csv().put(symbol, pr);

            val1 = read_csv().get(symbol);
            val2 = read_csv_2().get(security2);


        }


        return val1;                                     //Return val variable to Server_Handler


    }

//===========================================================================================================================================

    public Float read(String symbol, String security) throws IOException {

        int security2 = Integer.parseInt(security);


        if (read_csv_2().containsKey(security2) && read_csv().containsKey(symbol))            //If the symbol presents on Hashmap
        {

            val1 = read_csv().get(symbol);
            val2 = read_csv_2().get(security2);


        }


        return val1;                                     //Return val variable to Server_Handler


    }

}

















