package main.java.DatabaseRe;

import main.java.DatabaseRe.Mediators.Getters.RaffleGetter;

import java.time.LocalDate;
import java.util.ArrayList;

public class FakeMain {
    public static void main(String[] args) throws Exception {
//
//        RaffleGetter raffleGetter = new RaffleGetter();
//        System.out.print(raffleGetter.getOrganizer("R012"));

//        ProvideData provideData = new ProvideData();
//        LocalDate date = LocalDate.of(2025, 12, 12);
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
        AccessData accessData = new AccessData();
        System.out.println("This is how you see participant info");
        System.out.print(accessData.getParticipantInfo("chutiya"));
        System.out.println("\nThis is how you see organizer info");
        System.out.print(accessData.getOrganizerInfo("chutiya"));

    }
}
