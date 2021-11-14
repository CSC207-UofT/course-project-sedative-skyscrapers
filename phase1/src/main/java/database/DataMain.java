package main.java.database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DataMain {

    public static void main(String[] args) throws Exception {
        AddOrganizer dw = new AddOrganizer();
        dw.updateOrganizerPool("khushaal", "admin123", "uoft", "9099272288", "khushaal@uoft.com");

        ArrayList<String> faltu = new ArrayList<String >();
        ArrayList<Object> given = new ArrayList<>();
        given.add("new raffle");
        given.add(4);
        given.add("these are very strict rules for raffle");
        given.add(LocalDate.of(2021, 12, 25));
        dw.uploadCreatedRaffle(faltu, "136", given);
        // SUPREMELY IMPORTANT STEP IN THE NEXT LINE: GOD MODE
        dw.addOrganizer();
        // GN function
        dw.uploadCreatedTask("1", "first task", "www.google.com", "this is a hardcore descrp of task");




    }
}
