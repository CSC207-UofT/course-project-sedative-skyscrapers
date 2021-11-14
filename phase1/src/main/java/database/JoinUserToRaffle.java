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
     * @param raffleID the raffleID user joined
     * @param username the username you want to join the raffle
     * @throws IOException dw
     */
    public void joinUserToRaffle(String raffleID, String username) throws IOException {
        FileWriter fw = getfile.getFile("raffleUserDetails");
        addTasks(raffleID, username, fw);
    }

    private void addTasks(String raffleID, String username, FileWriter fw) throws IOException {
        ArrayList<String> taskIds = extractData.getTasks(raffleID);
        for (String taskID:
             taskIds) {
            String row = getRow(raffleID, username, taskID);
            fw.append(row);
        }
        fw.flush();
        fw.close();
    }

    private String getRow(String raffleID, String username, String taskID) {
        return "\n" + COMMA + username + COMMA + raffleID + COMMA + taskID + COMMA + "F";
    }
}
