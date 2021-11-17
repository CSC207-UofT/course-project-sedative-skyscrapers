package main.java.database;

import java.io.FileNotFoundException;

public class DataExtractorFrontEnd {

    DataMiner data = new DataMiner();
    DataExtractor de = new DataExtractor();

    public DataExtractorFrontEnd() throws FileNotFoundException {
    }

    /**
     * @param username username you want to check in the database
     * @param userType type of user; "O" for Organizer, "P" for Participant
     * @return boolean, true if the username exists else false
     */
    public boolean checkUser(String username, String userType) {
        try {
            String[] testing;
            if (userType == "O")
                testing = de.getOrganizerDetails(username);
            else if(userType == "P")
                testing = de.getParticipantDetails(username);
            else
                return false;
            return true;
        } catch (final Exception e) {
            return false;
        }
    }

    /**
     * @param username username you want to check password in the database
     * @param userType type of user; "O" for Organizer, "P" for Participant
     * @param inputPassword the password that is to be compared with in the database i.e. one that is input by the user
     * @return boolean: true if the username and password match. false if they don't match or if the username does not exist
     */
    public boolean checkPassword(String username, String userType, String inputPassword) {
        try {
            String testing[];
            if (userType == "O")
                testing = de.getOrganizerDetails(username);
            else if(userType == "P")
                testing = de.getParticipantDetails(username);
            else
                return false;
            return testing[1].equals(inputPassword);
        } catch (final Exception e) {
            return false;
        }
    }

}
