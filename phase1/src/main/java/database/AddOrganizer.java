package main.java.database;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AddOrganizer {
    String username = "";
    String password = "";
    String phone = "";
    String email = "";
    String raffleID = "";
    String rafflename = "";
    // name, noOfWinners, endDate, raffleRules
    String rafflerules = "";
    String affiliatedorg = "";
    String startdate = "21112001";
    String enddate = "";
    String possiblewinners = "";

    String COMMA = ",";
    GetFileToAppend getfile = new GetFileToAppend();

    public AddOrganizer() throws IOException {
    }

    public void updateOrganizerPool(String iusername, String ipassword,
                                    String iaffiliatedOrganization, String iphone, String iemail) throws IOException {
        username = iusername;
        password = ipassword;
        affiliatedorg = iaffiliatedOrganization;
        phone = iphone;
        email = iemail;
        addToOuserCred(false);

    }

    public void uploadCreatedRaffle(ArrayList<String> takenIds,
                                    String orgRaffleId,
                                    ArrayList<Object> raffleCreatedInfo) throws IOException {
        raffleID = orgRaffleId;
        rafflename = (String) raffleCreatedInfo.get(0);
        possiblewinners = raffleCreatedInfo.get(1).toString();
        rafflerules = (String) raffleCreatedInfo.get(2);
        enddate = raffleCreatedInfo.get(3).toString();
        addToOuserCred(true);
    }

    public void uploadCreatedTask(String taskID, String taskname, String link, String description) throws IOException {
        String data = "\n" + raffleID + COMMA + taskID + COMMA + taskname + COMMA + link + COMMA + description;
        FileWriter fw = getfile.getFile("raffleTaskDetails");
        fw.append(data);
        fw.flush();
        fw.close();
    }


    private void addToOuserCred(boolean raffle) throws IOException {
        FileWriter fw = getfile.getFile("OuserCred");
        String data = "\n" + username + COMMA + password + COMMA + "firstname" + COMMA + "lastname" + COMMA +
                "21112001" + COMMA + phone + COMMA + email;
        String data2 = COMMA + possiblewinners + COMMA + rafflename + COMMA + rafflerules + COMMA + affiliatedorg + COMMA + startdate + COMMA +
                enddate + COMMA + raffleID;
        if (raffle) {
            fw.append(data2);
        }
        else {
            fw.append(data);
        }

        fw.flush();
    }
}

