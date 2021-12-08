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

    public RaffleButton(String raffleID,JFrame frame, boolean role,String username)
    {
        this.raffleID = raffleID;
        this.frame1 = frame;
        this.role = role;
        this.username = username;
    }

    public RaffleButton(String raffleID, JFrame frame1, JFrame frame2, boolean role, String username){
        this.frame1 = frame1;
        this.raffleID = raffleID;
        this.frame2 = frame2;
        this.role = role;
        this.username = username;
    }

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
