package main.java.database;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JoinUserToRaffle {
    GetFileToAppend getfile = new GetFileToAppend();
    DataExtractor extractData = new DataExtractor();
    String COMMA = ",";

    public JoinUserToRaffle() throws IOException {
    }

    /**
     * Joins the username to the raffle.
     * @param ptcRaffleID the raffleID user joined
     * @param username the username you want to join the raffle
     * @throws IOException dw
     */
    public void joinUserToRaffle(String ptcRaffleID, String username) throws IOException {
        FileWriter fw = getfile.getFile("raffleUserDetails");
        addTasks(ptcRaffleID, username, fw);
    }

    private void addTasks(String ptcRaffleID, String username, FileWriter fw) throws IOException {
        String[] ptcRaffleIdParts = ptcRaffleID.split(":");
        String orgRaffleId = ptcRaffleIdParts[1];

        ArrayList<String> taskIds = extractData.getTasks(orgRaffleId);
        for (String taskID:
             taskIds) {
            String row = getRow(ptcRaffleID, username, taskID);
            fw.append(row);
        }
        fw.flush();
    }

    private String getRow(String raffleID, String username, String taskID) {
        return "\n" + username + COMMA + raffleID + COMMA + taskID + COMMA + "F";
    }
}
