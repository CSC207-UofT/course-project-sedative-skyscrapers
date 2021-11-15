package main.java.GUI;

import main.java.Web.OrganizerSystemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ParticipantRafflePage extends JFrame {
    public static JFrame rFrame;
    public static JScrollPane rScrollPane;
    public static JPanel rPanel;
    public boolean isEnrolled;
    public String raffleID;
    public static JTextArea raffleInfoField;
    public static JButton joinButton;
    public JTextArea[] tasks;
    public JTextField[] taskNames;
    public JButton[] executeButtons;
    public JButton backButton;
    public static JPanel bigPanel;
    public String username;
    public ArrayList<Object> raffleInfo;

    public ParticipantRafflePage(String raffleID,String username)
    {
        this.raffleID = raffleID;
        this.username = username;
        OrganizerSystemManager osm = new OrganizerSystemManager();
        raffleInfo = osm.getRaffleDetails(raffleID);
        if(((ArrayList<String>)raffleInfo.get(5)).contains(username))
            isEnrolled = true;
        else
            isEnrolled = false;
        // code for checking whether the guy is enrolled.
        //this.isEnrolled = false;
        //p should be passed in the parameter
        // this.p = p;
        initComponents();
    }
    private void initComponents()
    {
        rFrame = new JFrame("Raffle Page");
        rFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        bigPanel = new JPanel();
        bigPanel.setBounds(0,0,rFrame.getWidth(),rFrame.getHeight());
        bigPanel.setBackground(new Color(0,240,100,100));

        rPanel = new JPanel();
        GroupLayout gl = new GroupLayout(rPanel);
        rPanel.setLayout(gl);
        rPanel.setBackground(new Color(255,255,255));

        rScrollPane = new JScrollPane(rPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        rScrollPane.setBounds(0,rFrame.getHeight()/6,rFrame.getWidth(),(4*rFrame.getHeight()/6));
        rScrollPane.setBackground(new Color(255,255,255));
        rScrollPane.getVerticalScrollBar().setUnitIncrement(16);


        OrganizerSystemManager osm = new OrganizerSystemManager();
        //Function to
        raffleInfoField = new JTextArea("Raffle Name: "+raffleInfo.get(0));
        raffleInfoField.setEditable(false);
        raffleInfoField.setLineWrap(true);
        raffleInfoField.append("Raffle Rules:");
        raffleInfoField.append("\n"+raffleInfo.get(2));
        raffleInfoField.setPreferredSize(new Dimension(3*rFrame.getWidth()/4,100));
        raffleInfoField.setMinimumSize(raffleInfoField.getPreferredSize());
        raffleInfoField.setMaximumSize(raffleInfoField.getPreferredSize());
        raffleInfoField.setFont(new Font("Calibri",Font.PLAIN,20));

        if(!isEnrolled)
            joinButton = new JButton("Join this raffle!");
        else
        {
            joinButton = new JButton("Joined");
            joinButton.setEnabled(false);
        }
        joinButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        joinButton.setPreferredSize(new Dimension(rFrame.getWidth()/6,30));
        joinButton.setMinimumSize(joinButton.getPreferredSize());
        joinButton.setMaximumSize(joinButton.getPreferredSize());

        backButton = new JButton("Go back");
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setPreferredSize(new Dimension(rFrame.getWidth()/6,30));
        backButton.setMinimumSize(backButton.getPreferredSize());
        backButton.setMaximumSize(backButton.getPreferredSize());
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ParticipantMainPage p = new ParticipantMainPage(username);
                int option = JOptionPane.showConfirmDialog(rFrame,"Are you sure you want to go back? If you do all progress will be lost","Confirm",JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION)
                {
                    rFrame.setVisible(false);
                    ParticipantMainPage p = new ParticipantMainPage(username);
                }

            }
        });

        ArrayList<String> taskIDs = (ArrayList<String>)raffleInfo.get(4);
        this.taskNames = new JTextField[taskIDs.size()];
        this.executeButtons = new JButton[taskIDs.size()];
        this.tasks = new JTextArea[taskIDs.size()];

        for(int i=0;i<taskIDs.size();i++)
        {

            try{
                ArrayList<String> taskInfo = osm.getTaskInfo(taskIDs.get(i));
                taskNames[i] = new JTextField(taskInfo.get(0));
                taskNames[i].setEditable(false);
                taskNames[i].setPreferredSize(new Dimension(rFrame.getWidth()/2,40));
                taskNames[i].setMinimumSize(taskNames[i].getPreferredSize());
                taskNames[i].setMaximumSize(taskNames[i].getPreferredSize());

                executeButtons[i] = new JButton("Complete task");
                executeButtons[i].setAlignmentX(Component.RIGHT_ALIGNMENT);
                executeButtons[i].setPreferredSize(new Dimension(rFrame.getWidth()/4,40));
                executeButtons[i].setMinimumSize(executeButtons[i].getPreferredSize());
                executeButtons[i].setMaximumSize(executeButtons[i].getPreferredSize());
                executeButtons[i].addActionListener( new TaskButton(taskIDs.get(i), raffleID,executeButtons[i],rFrame));

                tasks[i] = new JTextArea(taskInfo.get(1));
                tasks[i].append("\n"+taskInfo.get(2));
                tasks[i].setEditable(false);
            }
            catch(FileNotFoundException fnle)
            {
                JOptionPane.showMessageDialog(rFrame,"No tasks found","Error",JOptionPane.ERROR_MESSAGE);
            }



        }
        GroupLayout.ParallelGroup pg = gl.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(raffleInfoField);
        GroupLayout.SequentialGroup sg = gl.createSequentialGroup().addComponent(raffleInfoField);
        pg.addGroup(gl.createSequentialGroup().addComponent(backButton).addComponent(joinButton));
        sg.addGroup(gl.createParallelGroup().addComponent(backButton).addComponent(joinButton));
        if(!isEnrolled)
        {

            for(int i=0;i<taskNames.length;i++)
            {
                pg.addGroup(gl.createSequentialGroup().addComponent(taskNames[i]).addComponent(executeButtons[i]));
                pg.addComponent(tasks[i]);
                sg.addGroup(gl.createParallelGroup().addComponent(taskNames[i]).addComponent(executeButtons[i]));
                sg.addComponent(tasks[i]);
            }
        }

        gl.setHorizontalGroup(pg);
        gl.setVerticalGroup(sg);




        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new ParticipantRaffle object and add it to the database and stuff
                int option = JOptionPane.showConfirmDialog(rFrame,"Are you sure you want to join this raffle?","Confirm",JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION)
                {
                    //Add user to Raffle's participant
                    joinButton.setEnabled(false);
                    joinButton.setText("Joined");
                    isEnrolled = true;
                    for(int i=0;i<taskNames.length;i++)
                    {
                        pg.addGroup(gl.createSequentialGroup().addComponent(taskNames[i]).addComponent(executeButtons[i]));
                        pg.addComponent(tasks[i]);
                        sg.addGroup(gl.createParallelGroup().addComponent(taskNames[i]).addComponent(executeButtons[i]));
                        sg.addComponent(tasks[i]);
                    }
                }

            }
        });

        rFrame.getContentPane().add(rScrollPane);
        rFrame.getContentPane().add(bigPanel);
        rFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        rFrame.setVisible(true);

    }





}

