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

     * @return String[] of details of the order given in database
     * @throws Exception if database file is not found
     */


    public String[] getOrganizerDetails(String user_search) throws Exception{
        
        boolean user_found = false;
        //String[] details = new String[13];
        String userType = "OuserCred";

        String[] attributes = data.get_line(userType, true);
        //System.out.println(attributes.length);
        while (attributes != null) {
            if (attributes[0].equals(user_search)) {
                user_found = true;
                return attributes;
            }
            attributes = data.get_line(userType, false);
        }

        if(!user_found) {
            throw new Exception("No such user found");
        }
        return new String[0];
        //return ;
    }

    public String[] getParticipantDetails(String user_search) throws Exception{

        boolean user_found = false;
        String[] details;
        String userType = "PuserCred";

        String[] attributes = data.get_line(userType, true);
        //System.out.println(attributes.length);

        while (attributes != null) {
            //System.out.println("Attribute[0] = "+attributes[0]);
            if (attributes[0].equals(user_search)) {
                user_found = true;
                details = attributes;
                return details;
            }
            attributes = data.get_line(userType, false);
        }
        if(!user_found) {
            throw new Exception("No such user found");
        }
        return new String[0];
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

        URL data_path = DataMain.class.getResource("raffleDetails.csv");
        File f = new File(((URL) data_path).getFile());
        ArrayList<String> used_raffleIDs = new ArrayList<>();

        String[] attributes = data.get_line("raffleDetails", true);
        while (attributes != null ) {
            if(attributes[0].equals("")){
                attributes = data.get_line("raffleDetails", false);
                continue;
            }

            String raffleID = attributes[RAFFLEID];
            boolean contains = used_raffleIDs.contains(raffleID);
            if (!contains) {
                used_raffleIDs.add(raffleID);
            }
            attributes = data.get_line("raffleDetails", false);
        }

        //used_raffleIDs.remove(0);
        return (used_raffleIDs);

    }

    /*public void get_controller_data(String raffleID) throws IOException {
        HashMap<Integer, ArrayList<Object>> controller_data = new HashMap<>();

        ArrayList<String> usernames = getUsersInRaffle(raffleID);
        ArrayList<String> winners = getWinners(raffleID);
        Integer noOfWinners = winners.size();
        ArrayList<String> tasks = getTasks(raffleID);
        // RaffleDetails = [raffleName, raffleRules, raffleEndDate]
        String[] raffleDetails = getRaffleDetails(raffleID);

    }*/


    public HashMap<String, ArrayList<Object>> getAllOrgRaffleInfo() throws IOException{
        HashMap<String, ArrayList<Object>> hashMapToReturn = new HashMap<>();

        try {
            for (String orgRaffleId: getUsedRaffleIDs()){
                hashMapToReturn.put(orgRaffleId, getOrgRaffleInfo(orgRaffleId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hashMapToReturn;
    }

    public HashMap<String, ArrayList<Object>> getAllPtcRaffleInfo(String orgRaffleId) throws Exception{
        HashMap<String, ArrayList<Object>> allPtcRafflesHashMap = new HashMap<>();
        HashMap<String, ArrayList<Object>> allRafflesHashMap = this.getAllOrgRaffleInfo();
        for (String raffleIdKey: allRafflesHashMap.keySet()){
            if (raffleIdKey.contains(":")){
                String[] ptcRaffleIdParts = raffleIdKey.split(":");
                if (ptcRaffleIdParts[1].equals(orgRaffleId)){
                    allPtcRafflesHashMap.put(raffleIdKey, allRafflesHashMap.get(raffleIdKey));
                }
            }
        }

        return allPtcRafflesHashMap;
    }
    //
    public ArrayList<Object> getOrgRaffleInfo(String orgRaffleId) throws IOException{
        ArrayList<Object> orgRaffleInfo = new ArrayList<>(Arrays.asList(getRaffleDetails(orgRaffleId)));
        orgRaffleInfo.add(getTasks(orgRaffleId));
        orgRaffleInfo.add(getUsersInRaffle(orgRaffleId));
        //System.out.println("Getting winners");
        orgRaffleInfo.add(getWinners(orgRaffleId));


        return orgRaffleInfo;
    }

    public ArrayList<Object> getPtcRaffleInfo(String ptcRaffleId) throws IOException{
        ArrayList<Object> orgRaffleInfo = new ArrayList<>(Arrays.asList(getRaffleDetails(ptcRaffleId)));
        orgRaffleInfo.add(getTasks(ptcRaffleId));

        return orgRaffleInfo;
    }



    private String[] getRaffleDetails(String raffleID) throws IOException {

        int RAFFLEID = 7;
        int RAFFLENAME = 2;
        int RAFFLENUMWINNERS = 1;
        int RAFFLEENDDATE = 6;
        int RAFFLEUSERNAME = 0;


        String[] raffleDetails = new String[4];
        DataMiner data2 = new DataMiner();

        String[] attributes = data2.get_line("raffleDetails", true);

        while(attributes != null) {
            if (raffleID.equals(attributes[RAFFLEID])) {

                raffleDetails[0] = attributes[RAFFLENAME];
                raffleDetails[1] = attributes[RAFFLENUMWINNERS];
                raffleDetails[2] = attributes[RAFFLEENDDATE];
                raffleDetails[3] = attributes[RAFFLEUSERNAME];
                break;
            }
            attributes = data2.get_line("raffleDetails", false);
        }
        return raffleDetails;
    }

    public ArrayList<String> getTasks(String raffleID) throws IOException {
        ArrayList<String> tasks = new ArrayList<String>();
        //Integer currentRaffleID = -1;
        //Integer targetRaffleID = Integer.parseInt(raffleID);
        if(raffleID.split(":").length == 2)
            raffleID = raffleID.split(":")[1];
        //System.out.println("RaffleID in getTasks() == "+raffleID);
        String[] attributes = data.get_line("raffleTaskDetails", true);
        while (attributes!=null) {
            if(attributes[0].length()==6)
                attributes[0] = attributes[0].substring(1,6);
            if(raffleID.equals(attributes[0]))
            {
                tasks.add(attributes[1]);
            }
            attributes = data.get_line("raffleTaskDetails", false);
        }
        //!Objects.equals(attributes[0], raffleID)
        return tasks;
    }

    public ArrayList<String> getValidParticipants(String raffleID) throws IOException
    {
        int USERNAME = 0;
        int RAFFLEID = 1;
        int TASKSTATUS = 3;
        ArrayList<String> usernames = new ArrayList<String>();

        String[] attributes = data.get_line("raffleUserDetails", true);
        attributes = data.get_line("raffleUserDetails", false);
        while (attributes != null) {
            String testing_user = attributes[USERNAME];
            if ((!attributes[0].equals(""))&&raffleID.equals(attributes[RAFFLEID])) {
                if (!(usernames.contains(testing_user))) {
                    if(attributes[TASKSTATUS].equals("T")) {
                        usernames.add(testing_user);
                    }
                }
                else
                {
                    if(attributes[TASKSTATUS].equals("F")) {
                        usernames.remove(testing_user);
                    }
                }
            }

            attributes = data.get_line("raffleUserDetails", false);

        }

        return usernames;
    }


    private ArrayList<String> getUsersInRaffle(String rID) throws IOException {

        int USERNAME = 0;

        ArrayList<String> usernames = new ArrayList<>();

        String[] attributes = data.get_line("raffleUserDetails", true);

        while (attributes != null) {

            if ((!(usernames.contains(attributes[USERNAME])))&&rID.equals(attributes[1])) {
                usernames.add(attributes[USERNAME]);
            }
            attributes = data.get_line("raffleUserDetails", false);
        }
        return usernames;
    }



    private ArrayList<String> getWinners(String rID) throws IOException {
        int USERNAME = 1;
        ArrayList<String> usernames= new ArrayList<String>();
        //System.out.println("STart");
        String[] attributes = data.get_line("raffleWinners", true);
        //System.out.println("Finish");
        while (attributes != null) {
            //System.out.println("Attr[0] = " +attributes[0]);
            //System.out.println("rID = "+rID);
            if (rID.equals(attributes[0])) {
                //System.out.println("True");
                usernames.add(attributes[USERNAME]);
            }
            attributes = data.get_line("raffleWinners", false);
        }
        return usernames;
    }

    public ArrayList<String> getParticipantRaffleId(String ptcusername) throws IOException {
        ArrayList<String> raffles = new ArrayList<>();
        String[] attributes = new String[0];
        attributes = data.get_line("raffleUserDetails", true);
        while (attributes != null) {

            if ((Objects.equals(attributes[0], ptcusername)) && (!raffles.contains(attributes[1]))) {
                raffles.add(attributes[1]);
            }
            attributes = data.get_line("raffleUserDetails", false);
        }
        return raffles;
    }

    public String[] getOrganizerRaffleId(String orgusername) {
        try {
            String[] raffles;
            ArrayList<String> raffleIDs = new ArrayList<>(0);
            String[] attributes = data.get_line("raffleDetails", true);

            while (attributes != null) {
                if(attributes[0].equals(orgusername))
                {
                    raffleIDs.add(attributes[7]);
                }
                attributes = data.get_line("raffleDetails",false);
            }
            raffles = new String[raffleIDs.size()];
            for(int i = 0;i<raffleIDs.size();i++)
                raffles[i] = raffleIDs.get(i);
            return raffles;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] userNotFound = new String[1];
        userNotFound[0] = "User not found";
        return userNotFound;
    }

    public boolean hasCompletedTask(String raffleID, String username,String taskID)
    {

        try {


            String[] attributes = data.get_line("raffleUserDetails", true);
            while (attributes != null) {
                //System.out.println("ATTRIBUTES[0] = "+attributes[0]);
                if(attributes[0].equals(username)&&attributes[1].equals(raffleID)&&attributes[2].equals(taskID))
                {
                    if(attributes[3].equals("T"))
                        return true;
                }
                attributes = data.get_line("raffleUserDetails",false);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
