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
    public TaskButton(String taskID, String raffleID,JButton button,JFrame frame)
    {
        this.taskID = taskID;
        this.button = button;
        this.raffleID = raffleID;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ParticipantSystemManager psm = new ParticipantSystemManager();
        try {
            psm.completeTask(raffleID, taskID);
            button.setEnabled(false);
            button.setText("Task Completed");
        }
        catch(Exception exc)
        {
            JOptionPane.showMessageDialog(frame,exc.getStackTrace(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
