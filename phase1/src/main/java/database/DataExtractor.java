package main.java.database;

import javax.naming.directory.AttributeInUseException;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;


public class DataExtractor {

    DataMiner data = new DataMiner();

    public DataExtractor() throws FileNotFoundException {
    }

    /**
     * Find details of the user you want to look for
     *
     * @param user_search the user to be searched for
     * @param user_type indicate with "O" for Organizer and "P" for Participant
     * @return String[] of details of the order given in database
     * @throws Exception if database file is not found
     */


    public String[] getUserDetails(String user_search, String user_type) throws Exception{
        if (!(user_type.equals("O") | user_type.equals("P")))
        {
            throw new Exception("Invalid User Type Entered. Make sure " +
                    "it is either O or P for organiser or participant respectively");
        }
        boolean organizer = user_type.equals("O");
        
        boolean user_found = false;
        String[] details = new String[13];
        String userType = "PuserCred";
        
        if (organizer) {
            userType = "OuserCred";
        }

        String[] attributes = data.get_line(userType, true);
        
        while (attributes != null) {
            if (attributes[0].equals(user_search)) {
                user_found = true;
                details = attributes;
                break;
            }
            attributes = data.get_line(userType, false);
        }
        if (!user_found) {
            throw new Exception("No such user found");
        }
        return details;
    }

    /**
     * Returns the ArrayList<String> of RaffleIDs that are already used
     *
     * @return  arraylist of raffleIDs that are used
     * @throws IOException when the file is not found.
     *
     * Required file: raffleTaskDetails.csv in the usage directory.
     */
    public ArrayList<String> getUsedRaffleIDs() throws IOException {

        int RAFFLEID = 7;

        URL data_path = DataMain.class.getResource("OuserCred.csv");
        File f = new File(((URL) data_path).getFile());
        ArrayList<String> used_raffleIDs = new ArrayList<>();

        String[] attributes = data.get_line("OuserCred", true);
        while (attributes != null) {
            String raffleID = attributes[RAFFLEID];
            boolean contains = used_raffleIDs.contains(raffleID);
            if (!contains) {
                used_raffleIDs.add(raffleID);
            }
            attributes = data.get_line("OuserCred", false);
        }

        used_raffleIDs.remove(0);
        return (used_raffleIDs);

    }

    public void get_controller_data(String raffleID) throws IOException {
        HashMap<Integer, ArrayList<Object>> controller_data = new HashMap<>();

        ArrayList<String> usernames = getUsersInRaffle(raffleID);
        HashMap<String, Boolean> winners = getWinners(raffleID);
        Integer noOfWinners = winners.size();
        ArrayList<String> tasks = getTasks(raffleID);
        // RaffleDetails = [raffleName, raffleRules, raffleEndDate]
        String[] raffleDetails = getRaffleDetails(raffleID);

    }

    private String[] getRaffleDetails(String raffleID) throws IOException {

        int RAFFLEID = 7;
        int RAFFLENAME = 8;
        int RAFFLERULES = 9;
        int RAFFLEENDDATE = 11;

        String[] raffleDetails = new String[3];

        String[] attributes = data.get_line("OuserCred", true);
        attributes = data.get_line("OuserCred", false);

        while(attributes != null) {
            if (raffleID.equals(attributes[RAFFLEID])) {
                raffleDetails[0] = attributes[RAFFLENAME];
                raffleDetails[1] = attributes[RAFFLERULES];
                raffleDetails[2] = attributes[RAFFLEENDDATE];
                break;
            }
            attributes = data.get_line("OuserCred", false);
        }
        return raffleDetails;
    }

    public ArrayList<String> getTasks(String raffleID) throws IOException {
        ArrayList<String> tasks = new ArrayList<String>();
        Integer currentRaffleID = -1;
        Integer targetRaffleID = Integer.parseInt(raffleID);

        String[] attributes = data.get_line("raffleTaskDetails", true);
        while (!Objects.equals(attributes[0], raffleID)) {
            attributes = data.get_line("raffleTaskDetails", false);
            if (attributes[0] == null) {
                break;
            }
        }


        while (raffleID.equals(attributes[0])) {
            tasks.add(attributes[1]);
            attributes = data.get_line("raffleTaskDetails", false);
            if (attributes == null) {break;}
        }
        return tasks;
    }


    private ArrayList<String> getUsersInRaffle(String rID) throws IOException {

        int USERNAME = 0;

        ArrayList<String> usernames = new ArrayList<>();

        String[] attributes = data.get_line("raffle", true);
        attributes = data.get_line("raffle", false);

        while (attributes != null) {
            if (!(usernames.contains(attributes[USERNAME]))) {
                usernames.add(attributes[USERNAME]);
            }
            attributes = data.get_line("raffle", false);
        }
        return usernames;
    }


    private HashMap<String, Boolean> getWinners(String rID) throws IOException {
        int USERNAME = 0;
        int RAFFLEID = 1;
        int TASKSTATUS = 3;
        HashMap<String, Boolean> usernames = new HashMap<String, Boolean>();

        String[] attributes = data.get_line("raffle", true);
        attributes = data.get_line("raffle", false);

        while (attributes != null) {
            String testing_user = attributes[USERNAME];
            if (rID.equals(attributes[RAFFLEID])) {
                if (!(usernames.containsKey(testing_user))) {
                    usernames.put(testing_user, true);
                }
                if (attributes[TASKSTATUS].equals("F")) {
                    usernames.replace(testing_user, false);
                }
            }

            attributes = data.get_line("raffle", false);

        }

        return usernames;
    }

    public ArrayList<String> getParticipantRaffleId(String ptcusername) {
        ArrayList<String> raffles = new ArrayList<>();
        String[] attributes = new String[0];
        try {
            attributes = data.get_line("raffleUserDetails", true);
            while (attributes != null) {
                String tempRaffle = attributes[1];
                if ((Objects.equals(attributes[0], ptcusername)) && (!raffles.contains(tempRaffle))) {
                    raffles.add(tempRaffle);
                }
                attributes = data.get_line("raffleUserDetails", false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return raffles;
    }

    public String getOrganizerRaffleId(String orgusername) {
        try {
            String[] details = getUserDetails(orgusername, "O");
            return details[7];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "User Not Found";
    }
}

//public interface IUserDetail {
//         String FirstName = null;
//         String LastName = null;
//         int Age = 0;
//    }
//
//    public static class OuserDetail implements IUserDetail {
//        String FirstName = null;
//        String LastName = null;
//        int Age = 0;
//    }
//
//    public static class PuserDetail implements IUserDetail {
//        String FirstName = null;
//        String LastName = null;
//        int Age = 0;
//        String Email="";
//    }
//public enum UserType
//{
//    Organizer,
//    Partyner
//}
//    // TODO: convert into enumeration
//    public IUserDetail[] GetUserDetails(UserType userType)
//    {
//        if(userType==UserType.Organizer)
//            return new OuserDetail();
//            else if (user)
//    }
