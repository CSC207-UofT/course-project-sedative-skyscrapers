package main.java.DatabaseRe;

import java.sql.SQLException;
import java.text.ParseException;

public class FakeMain {
    public static void main(String[] args) throws SQLException, ParseException {
        AccessData accessData = new AccessData();
//        System.out.print(accessData.getOrganizerRaffleById("R02"));
//        System.out.print(accessData.getTaskById("T02"));
//        System.out.print(accessData.getOrganizerInfo("chutiyanfkljf"));
//        System.out.print(accessData.getParticipantInfo("Sharma Ji"));
//        System.out.print(accessData.getOrganizerRaffleIds("CHUsTIYE"));
          System.out.print(accessData.getValidParticipants("R01"));
    }
}
