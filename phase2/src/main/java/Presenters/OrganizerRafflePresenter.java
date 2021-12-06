package main.java.Presenters;

import main.java.Web.OrganizerSystemManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrganizerRafflePresenter {
    private String username;
    private String raffleID;
    private OrganizerSystemManager o;

    public OrganizerRafflePresenter(String username, String raffleID)
    {
        this.username = username;
        this.raffleID = raffleID;
        o = new OrganizerSystemManager();
        o.setUsername(username);
        o.setRaffleID(username);
    }
    public String getRaffleDetails() throws Exception {
        ArrayList<Object> details = o.getRaffleDetails(raffleID);
        String s = "Raffle Name: "+details.get(0)+"\nRaffle Rules: "+details.get(2)+"\nNumber of Winners: "+details.get(1)+"\nEnd Date: "+((java.util.Date)details.get(3)).toString()+"\nOrganizer: "+details.get(7)+"\n\nParticipants:";
        for(int i = 0;i<((ArrayList<String>)details.get(5)).size();i++)
            s += "\n"+(i+1)+". "+((ArrayList<String>)details.get(5)).get(i);
        s += "\nTask Details:\n";
        for(int i = 0;i<((ArrayList<Object>)details.get(4)).size();i++)
        {
            ArrayList<String> d = o.getTaskInfo(raffleID, ((ArrayList<Object>)details.get(4)).get(i).toString());
            s+= "\nTask "+(i+1)+": "+d.get(1) +"\nDescription: "+d.get(2)+"\nLink: "+d.get(3)+"\n";
        }
        if(winnersGenerated())
        {
            s += "\nWinners:\n";
            for(int i = 0;i<((ArrayList<String>)details.get(6)).size();i++)
                s+="\n"+((ArrayList<String>)details.get(6)).get(i);
        }
        return s;
    }
    public boolean winnersGenerated()
    {
        if(((ArrayList<String>)o.getRaffleDetails(raffleID).get(4)).size()==0)
            return false;
        else
            return true;
    }
}
