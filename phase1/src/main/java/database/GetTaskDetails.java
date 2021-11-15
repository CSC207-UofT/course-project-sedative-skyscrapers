package main.java.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GetTaskDetails {
    DataMiner data;

    {
        try {
            data = new DataMiner();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public GetTaskDetails() throws FileNotFoundException {

    }

    public ArrayList<String> getTaskDetails(String taskID) throws FileNotFoundException{
        ArrayList<String> taskDetails = new ArrayList<String>();

        String[] attributes = new String[0];
        try {
            attributes = data.get_line("raffleTaskDetails", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true) {
            if (attributes[1].equals(taskID)) {
                taskDetails.add(attributes[2]);
                taskDetails.add(attributes[3]);
                taskDetails.add(attributes[4]);
                break;
            }
            try {
                attributes = data.get_line("raffleTaskDetails", false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return taskDetails;
    }

    public ArrayList<String> getUsedTaskIDs() throws IOException {
        ArrayList<String> usedTaskIDs = new ArrayList<String>();

        String[] attributes = new String[0];
        try {
            attributes = data.get_line("raffleTaskDetails", true);
            attributes = data.get_line("raffleTaskDetails", false);
            while (attributes != null) {
                usedTaskIDs.add(attributes[1]);
                attributes = data.get_line("raffleTaskDetails", false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usedTaskIDs;
    }
}
