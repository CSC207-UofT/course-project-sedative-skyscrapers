package main.java.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DataMain {

    public static void main(String[] args) throws Exception {
        DataExtractor de = new DataExtractor();
        String[] chaman = de.getUserDetails("janam_patri", "P");
        System.out.print(Arrays.toString(chaman));

        ArrayList<String> used_raffle_ids = de.getUsedRaffleIDs();

        DataMiner dm = new DataMiner();
//        de.get_controller_data("1");
    }
}
