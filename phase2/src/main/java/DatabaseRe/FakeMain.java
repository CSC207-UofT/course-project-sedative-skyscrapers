package main.java.DatabaseRe;

import main.java.DatabaseRe.Mediators.Getters.RaffleGetter;
import main.java.DatabaseRe.Mediators.Getters.TaskGetter;
import main.java.DatabaseRe.Mediators.Getters.UserGetter;
import main.java.UserComponent.Participant;
import main.java.Web.RaffleDataHelper;

import java.time.LocalDate;
import java.util.ArrayList;

public class FakeMain {

    public static void main(String[] args) throws Exception {
        TaskGetter taskGetter = new TaskGetter();
//        System.out.print(taskGetter.getTaskStatus("P7597", "T4903"));
//        AccessData accessData = new AccessData();
//        System.out.print(accessData.getParticipantRaffleIds("shih"));
////        System.out.print(accessData.getTaskById("T5811"));
//        ProvideData provideData = new ProvideData();
//        Participant ptc = new Participant("P1234", "x", "123", "mi", "mas");
//        provideData.addRaffleIDtoParticipant("P1234", "R1001");
//        provideData.addParticipant("P9997", "varun123", "Task123", "Michele", "Massa", "2001-11-21", "9090987878", "michele@mass.com", new ArrayList<>());
//        UserGetter ug = new UserGetter();
//        System.out.println(ug.getUserID("varun123", false));
//        System.out.println("I hva eprint erhte name");
//
//        RaffleGetter raffleGetter = new RaffleGetter();
//        System.out.print(raffleGetter.getOrganizer("R012"));
//        ProvideData provideData = new ProvideData();
//        provideData.addParticipant("P012", "usernae", "passw", "je", "nnifer", "2001-11-21", "909098282", "kskda@jaf.com", new ArrayList<>());
//        AccessData accessData = new AccessData();
//        System.out.print(accessData.getParticipantInfo("usernae"));
//        System.out.print(accessData.getParticipantInfo("usernae"));
//        ProvideData provideData = new ProvideData();
//        ArrayList<String> chut = new ArrayList<>();
//        chut.add("chutiya");
//        chut.add("gando.maro");
//        chut.add("bjhosdaksd");
//
////        LocalDate date = LocalDate.of(2025, 12, 12);
//        provideData.addDetailsOfTask("T001", chut);
//        provideData.changeRaffleEndDate("O12", date);
//        ArrayList<String> raffles = new ArrayList<>();
//        raffles.add("T02");
//        provideData.deleteTask(raffles);
//        provideData.changeOrganizerDetail("Khushaal", "organization", "Congress Communist");
//        ArrayList<String> raffles = new ArrayList<>();
//        raffles.add("R01");
//        provideData.addParticipant("0989", "chutiya123", "bhosadika123", "gand", "land", "2009-11-21", "9090987878", "janam@chutiya.com", raffles);
//        provideData.updateRaffleRules("R01", "do you mean it?");
//
//        TaskAdder taskAdder = new TaskAdder();
//        //        UserAdder userAdder = new UserAdder();
//        ArrayList<String> ptcs = new ArrayList<>();
//        ptcs.add("P01");
//        ptcs.add("P02");
//        taskAdder.assignParticipantsTaskStatus("R01", ptcs);
//        userAdder.winnersToRaffle("R03", ptcs);
//        RaffleAdder raffleAdder = new RaffleAdder();
//        UserGetter userGetter = new UserGetter();
//        System.out.print(userGetter.checkParticipantID("P01"));
//        ProvideData provideData = new ProvideData();

//        ArrayList<String> places = new ArrayList<>(Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));
//        taskAdder.addTaskIds("R04", places);
//        System.out.println("Bhosadike khatam ho gaya program.");
//        int year = 2023;
//        int month = 12;
//        Date date = new Date(year - 1900, month - 1, 21);
//        System.out.print(date.toString());
//        raffleAdder.addRaffleDetails("R04", "melt", 12,
//                "These are super important rules", date);
//        AccessData accessData = new AccessData();
////        System.out.print(accessData.getOrgIDByOrganization("organization"));
//        System.out.print(accessData.getTakenTaskIds());
//        System.out.println("This is how you see participant info");
//        System.out.print(accessData.getParticipantInfo("chutiya"));
//        System.out.println("\nThis is how you see organizer info");
//        System.out.print(accessData.getOrganizerInfo("chutiya"));
        RaffleDataHelper dh = new RaffleDataHelper();
        System.out.println(dh.getEmailFromParticipantId("P9232"));

    }
}
