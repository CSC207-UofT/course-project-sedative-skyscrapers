package main.java.Presenters;

import main.java.SystemManagers.OrganizerSystemManager;
import main.java.SystemManagers.ParticipantSystemManager;
import main.java.DatabaseRe.RaffleDataHelper;

import java.util.ArrayList;

public class ParticipantPresenter {
    private String username;
    ParticipantSystemManager p;
    OrganizerSystemManager o;

    /**
     * Initializes username
     * @param username - username of the current participant
     */

    public ParticipantPresenter(String username)
    {
        this.username = username;
        p = new ParticipantSystemManager();
        o = new OrganizerSystemManager();
    }

    /**
     * Returns raffle IDs of the raffles to be displayed
     * @return String[]
     */
    public String[] getRaffleDetailstoDisplay()
    {
        ArrayList<String> temp= p.getPartRaffleList(username);
        String[] info = new String[temp.size()];
        for (int i=0; i<temp.size();i++)
        {
            info[i] = temp.get(i);
        }
        return info;
    }

    /**
     *
     * @param raffleID - Raffle ID of the current raffle
     * @param index - index of display
     * @return String
     */
    public String formatDetails(String raffleID, int index)
    {
        ArrayList<Object> t = o.getRaffleDetails(raffleID);
        return  ((index+1) + ". Raffle Name: "+t.get(0)+"\t\t\t\tRaffle ID: "+raffleID+"\t\t\t\tOrganizer: "+t.get(7)+"\t\t\t\tEnd Date: "+ (t.get(3)).toString());
    }

    /**
     * Gets the raffle IDs that the participant has won
     * @return String[]
     */
    public String[] getWonRafflesDetailstoDisplay()
    {
        ArrayList<String> raffles =  p.getPartRaffleList(username);
        ArrayList<String> wonRaffles = new ArrayList<String>(0);
        for(int i = 0; i < raffles.size();i++)
        {
            if(o.getRaffleDetails(raffles.get(i)).contains(username))
                wonRaffles.add(raffles.get(i));
        }
        String[] info = new String[wonRaffles.size()];
        for(int i = 0;i<wonRaffles.size();i++)
            info[i] = wonRaffles.get(i);
        return info;
    }

    /**
     * Returns raffle IDs that have the raffle name
     * @param raffleName - Raffle Name to be searched
     * @return String[]
     */
    public String[] getAllRafflesWithRaffleName(String raffleName)
    {
        ArrayList<String> raffleIDs = o.getRaffleIDsFromName(raffleName);
        String[] info = new String[raffleIDs.size()];
        for(int i = 0;i<raffleIDs.size();i++)
            info[i] = raffleIDs.get(i);
        return info;
    }

    /**
     * Returns a list of raffle IDs that have this organization name
     * @param orgName - Organization to be searched
     * @return String[]
     */
    public String[] getAllRafflesWithOrgName(String orgName)
    {
        ArrayList<String> ids = o.getOrgIDsByOrgName(orgName);
        ArrayList<String> rids = new ArrayList<>();
        RaffleDataHelper dh = new RaffleDataHelper();

        for (int i = 0; i<ids.size(); i++){
            String username = dh.getUsernameFromUserId(ids.get(i), true);
            String[] temp = o.getOrgRaffleID(username);
            for (int j = 0; j < temp.length ; j++) {
                rids.add(temp[j]);
            }

    }
        String[] raffleIDs = new String[rids.size()];

        for(int i = 0;i<rids.size();i++) {
            raffleIDs[i] = rids.get(i);
        }
        return raffleIDs;
    }

    /**
     * Gets all raffles with this ID
     * @param raffleID - Raffle ID to be searched
     * @return String
     */
    public String getAllRafflesWithRaffleID(String raffleID)
    {
        if(o.getAllRaffleID().contains(raffleID))
            return formatDetails(raffleID,0);
        else
            return "";
    }


}
