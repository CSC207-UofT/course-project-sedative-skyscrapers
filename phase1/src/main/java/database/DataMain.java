package main.java.database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DataMain {

    public static void main(String[] args) throws Exception {
//        DataExtractor de = new DataExtractor();
//        DataExtractorFrontEnd defe = new DataExtractorFrontEnd();
////        String[] chaman = de.getUserDetails("faltu", "P");
////        System.out.print(Arrays.toString(chaman));
//        System.out.print(defe.checkUser("pavbhaji", "O"));
//
//        ArrayList<String> used_raffle_ids = de.getUsedRaffleIDs();
//
//        DataMiner dm = new DataMiner();
//        de.get_controller_data("1");
        AddOrganizer dw = new AddOrganizer();
        dw.updateOrganizerPool("khushaal", "admin123", "uoft", "9099272288", "khushaal@uoft.com");

        ArrayList<String> faltu = new ArrayList<String >();
        ArrayList<Object> given = new ArrayList<>();
        given.add("new raffle");
        given.add(4);
        given.add("these are very strict rules for raffle");
        given.add(LocalDate.of(2021, 12, 25));
//        ArrayList<String> taskids = new ArrayList<String>();
//        taskids.add("1");
//        taskids.add("2");
//        given.add(taskids);
//
        dw.uploadCreatedRaffle(faltu, "136", given);
        dw.uploadCreatedTask("1", "first task", "www.google.com", "this is a hardcore descrp of task");




    }
}
