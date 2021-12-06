package main.java.Presenters;

import main.java.Web.OrganizerSystemManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrganizerPresenter {
    private String username;
    private OrganizerSystemManager o;

    public OrganizerPresenter(String username)
    {
        this.username = username;
    }
    public String[] getAllRaffleIDs()
    {
        return o.getOrgRaffleID(username);
    }
    public String formatRaffleDetails(String raffleID, int index)
    {
        ArrayList<Object> details = o.getRaffleDetails(raffleID);
        return (index+1)+". Raffle Name: " + details.get(0)+"\t\t\t\tRaffle ID: "+raffleID+"\t\t\t\tEnd Date: "+((LocalDate)details.get(3)).toString();
    }
}
