package main.java.GUI;

import main.java.Web.ParticipantSystemManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskButton implements ActionListener {
    public String taskID;
    public JButton button;
    public String raffleID;
    public JFrame frame;

    /**
     * Initializes the instance variables
     * @param taskID - ID of the task to be completed
     * @param raffleID - ID of the raffle whose task is to be completed
     * @param button - The JButton that is pressed when the task is to be completed
     * @param frame - The JFrame where the participant is interacting with the raffle
     */
    public TaskButton(String taskID, String raffleID,JButton button,JFrame frame)
    {
        this.taskID = taskID;
        this.button = button;
        this.raffleID = raffleID;
        this.frame = frame;
    }
    /**
     * Completes the current task of the current raffle and disables button. Displays the error on frame, if any
     * @param e ActionEvent that is triggered when button is pressed
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        ParticipantSystemManager psm = new ParticipantSystemManager();
        try {
            psm.setUsername(raffleID.split(":")[0]);
            psm.completeTask(raffleID.split(":")[1], taskID);
            button.setEnabled(false);
            button.setText("Task Completed");
        }
        catch(Exception exc)
        {
            JOptionPane.showMessageDialog(frame,exc.getStackTrace(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
