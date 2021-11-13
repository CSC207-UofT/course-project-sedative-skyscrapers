package main.java.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DataMain {

    public static void main(String[] args) throws Exception {
        DataExtractor de = new DataExtractor();
        DataExtractorFrontEnd defe = new DataExtractorFrontEnd();
//        String[] chaman = de.getUserDetails("faltu", "P");
//        System.out.print(Arrays.toString(chaman));
        System.out.print(defe.checkUser("pavbhaji", "O"));

        ArrayList<String> used_raffle_ids = de.getUsedRaffleIDs();

        DataMiner dm = new DataMiner();
//        de.get_controller_data("1");
    }
}
