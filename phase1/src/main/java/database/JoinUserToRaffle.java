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
        //String raffleID = ptcRaffleID.split(":")[1];
        addTasks(ptcRaffleID, username, fw);
    }

    private void addTasks(String ptcRaffleID, String username, FileWriter fw) throws IOException {

        String orgRaffleId = ptcRaffleID;



        ArrayList<String> taskIds = extractData.getTasks(orgRaffleId);
        for(int i = 0;i<taskIds.size();i++)
        for (String taskID:
             taskIds) {
            //String row = getRow(ptcRaffleID, username, taskID);
            fw.append(username+COMMA+orgRaffleId+COMMA+taskID+COMMA+"F\n");
        }
        fw.flush();
    }

    private String getRow(String raffleID, String username, String taskID) {
        return username + COMMA + raffleID + COMMA + taskID + COMMA + "F"+"\n" ;
    }

    public void setCompletedTask(String ptcRaffleID, String taskID) throws IOException
    {
        String orgRaffleId = ptcRaffleID;
        FileWriter fw = getfile.getFile("raffleUserDetails");

        ArrayList<String> taskIds = extractData.getTasks(orgRaffleId);
        //String row = getRow(ptcRaffleID, username, taskID);
        String IDs[] = ptcRaffleID.split(":");
        fw.append(IDs[0]+COMMA+IDs[1]+COMMA+taskID+COMMA+"T\n");

        fw.flush();
    }

    public void uploadRaffleWinners(String raffleID, ArrayList<String> usernameList) throws IOException
    {
        FileWriter fw = getfile.getFile("raffleWinners");
        for(int i = 0;i< usernameList.size();i++) {
            fw.append(raffleID + COMMA + usernameList.get(i) + "\n");
        }
        fw.flush();
    }
}
