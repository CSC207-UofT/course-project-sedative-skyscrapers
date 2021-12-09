package main.java.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RaffleButton implements ActionListener {
    public String raffleID;
    public JFrame frame1;
    public JFrame frame2;
    public boolean role;
    public String username;
    //role = true -> participant
    //role = false -> organizer

    /**
     * Initializes instance attributes
     * @param raffleID - Raffle ID of the current raffle
     * @param frame - JFrame of the page where the raffles are displayed
     * @param role - user: true if partipant, false if organizer
     * @param username - username of the current user
     */

    public RaffleButton(String raffleID,JFrame frame, boolean role,String username)
    {
        this.raffleID = raffleID;
        this.frame1 = frame;
        this.role = role;
        this.username = username;
    }

    /**
     * Initializes instance attributes
     * @param raffleID - Raffle ID of the current raffle
     * @param frame1 - JFrame of the page where raffles are displayed
     * @param frame2 - JFrame of the page where searched raffles are displayed
     * @param role - user: true if partipant, false if organizer
     * @param username - username of the current user
     */

    public RaffleButton(String raffleID, JFrame frame1, JFrame frame2, boolean role, String username){
        this.frame1 = frame1;
        this.raffleID = raffleID;
        this.frame2 = frame2;
        this.role = role;
        this.username = username;
    }

    /**
     * Opens the raffle page when the button is triggered
     * @param e - ActionEvent that occurs when the button is pressed
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        //function to get to Raffle at index
        //System.out.println(index);
        if(role)
        {
            ParticipantRafflePage p = new ParticipantRafflePage(raffleID,username);
            frame2.setVisible(false);

        }
        else
        {
            OrganizerViewRafflePage o = new OrganizerViewRafflePage(raffleID,username);
        }
        frame1.setVisible(false);
    }

}
