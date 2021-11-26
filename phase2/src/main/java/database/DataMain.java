package main.java.database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DataMain {

    public static void main(String[] args) throws Exception {
        AddOrganizer dw = new AddOrganizer();
//        dw.updateOrganizerPool("khushaal", "admin123", "uoft", "9099272288", "khushaal@uoft.com");

        ArrayList<String> faltu = new ArrayList<String >();
        ArrayList<Object> given = new ArrayList<>();
        given.add("new raffle"); // raffleName
        given.add(4);   // no of winners
        given.add("these are very strict rules for raffle"); //raffleRules
        given.add(LocalDate.of(2021, 12, 25)); //raffleEndDate
        //dw.uploadCreatedRaffle(faltu, "136", given); //RaffleID
//
//        dw.uploadCreatedTask("1", "first task", "www.google.com", "this is a hardcore descrp of task");
//        dw.uploadCreatedTask("2", "Second task", "www.google.com", "this is a hardcore descrp of task");
//
//        AddParticipant ap = new AddParticipant();
//        ap.updateParticipantPool("xi", "mypassword", "anamika", "arora", "21112001", "9809827262", "anamika@gmail.com");
//
//        JoinUserToRaffle jutr = new JoinUserToRaffle();
//        jutr.joinUserToRaffle("136", "xi");
//        ArrayList<String> x = de.getParticipantRaffleId("janam_patri");

    }
}
