import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSV_Reader {

    float val;

    String filename="stocks.csv";
    HashMap<String, Float> hmap = new HashMap<>();

    public HashMap<String, Float> read_csv()
    {

        String line;

        if (hmap.isEmpty()) {

            try {
                BufferedReader readcsv = new BufferedReader(new FileReader(filename));
                readcsv.readLine();                            //Skip the header line
                while ((line = readcsv.readLine()) != null) {
                    String[] list = line.split(",");    //Splitting the values of each row

                    ArrayList<String> list2 = new ArrayList<>();  //Add the symbol to a String list

                    ArrayList<Float> list3 = new ArrayList<>();

                    list2.add(list[0]);                     //Add String values to string list
                    list3.add(Float.valueOf(list[1]));      //Add float values to a Float list


                    hmap.put(list2.get(0), list3.get(0));   //populate hashmap with string list & float list
                    //System.out.println(hmap);


                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return hmap;
        }


        return hmap;
    }






//===============================================================================================================================


    public Float bid(String symbol, String bid) throws IOException
    {

     if (read_csv().containsKey(symbol))            //If the symbol presents on Hashmap
        {
            val = read_csv().get(symbol);               //Assign its value to the val variable


            float symbol2 = Float.parseFloat(bid);
            //System.out.println(symbol2);

            if (symbol2 > val)                     //If user bid is higher than the previous value,
            {
                read_csv().put(symbol, symbol2);   //Replace the corresponding value with user's value
                val = read_csv().get(symbol);
                //System.out.println("Test : "+val);

            }
                          //Assign its new value to the val variable


        }
        //System.out.println(val);
        return val;                                     //Return val variable to Server_Handler


    }

//===================================================================================================================================



    public Float read (String symbol) throws IOException
        {
            if (read_csv().containsKey(symbol))            //If the symbol presents on Hashmap
                {
                    val = read_csv().get(symbol);               //Assign its value to the val variable
                }

            return val;                                     //Return val variable to Server_Handler


        }
    }

