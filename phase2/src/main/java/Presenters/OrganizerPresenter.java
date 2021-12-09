package main.java.Presenters;

import main.java.SystemManagers.OrganizerSystemManager;

import java.util.ArrayList;

public class OrganizerPresenter {
    private String username;
    private OrganizerSystemManager o;

    /**
     * Initializes instance variables
     * @param username - username of the organzier
     */

    public OrganizerPresenter(String username)
    {
        this.username = username;
        this.o = new OrganizerSystemManager();
        o.setUsername(username);
    }

    /**
     * Returns the raffle IDs of the user
     * @return Raffle IDs of the user
     */
    public String[] getAllRaffleIDs()
    {
        return o.getOrgRaffleID(username);
    }

    /**
     * Returns a string that is formmated to be displayed on the screen
     * @param raffleID - Current raffle ID
     * @param index - index of display
     * @return String
     */
    public String formatRaffleDetails(String raffleID, int index)
    {
        ArrayList<Object> details = o.getRaffleDetails(raffleID);
        return (index+1)+". Raffle Name: " + details.get(0)+"\t\t\t\tRaffle ID: "+raffleID+"\t\t\t\tEnd Date: "+(details.get(3)).toString();
    }
}
