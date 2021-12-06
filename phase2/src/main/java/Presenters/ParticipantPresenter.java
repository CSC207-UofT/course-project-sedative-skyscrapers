package main.java.Presenters;

import main.java.Web.OrganizerSystemManager;
import main.java.Web.ParticipantSystemManager;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ParticipantPresenter {
    private String username;
    ParticipantSystemManager p;
    OrganizerSystemManager o;

    public ParticipantPresenter(String username)
    {
        this.username = username;
        p = new ParticipantSystemManager();
        o = new OrganizerSystemManager();
    }
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
    public String formatDetails(String raffleID, int index)
    {
        ArrayList<Object> t = o.getRaffleDetails(raffleID);
        return  ((index+1) + ". Raffle Name: "+t.get(0)+"\t\t\t\tRaffle ID: "+raffleID+"\t\t\t\tOrganizer: "+t.get(7)+"\t\t\t\tEnd Date: "+ ((LocalDate)t.get(3)).toString());
    }

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
    public String[] getAllRafflesWithRaffleName(String raffleName)
    {
        ArrayList<String> raffleIDs = o.getRaffleIDsFromName(raffleName);
        String[] info = new String[raffleIDs.size()];
        for(int i = 0;i<raffleIDs.size();i++)
            info[i] = raffleIDs.get(i);
        return info;
    }
    public String[] getAllRafflesWithOrgName(String orgName)
    {
        ArrayList<String> ids = o.getOrgIDsByOrgName(orgName);
        String[] raffleIDs = new String[ids.size()];
        for(int i = 0;i<ids.size();i++)
            raffleIDs[i] = ids.get(i);
        return raffleIDs;
    }
    public String getAllRafflesWithRaffleID(String raffleID)
    {
        if(o.getAllRaffleID().contains(raffleID))
            return formatDetails(raffleID,0);
        else
            return "";
    }


}
