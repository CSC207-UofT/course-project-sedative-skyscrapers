package main.java.GUI;

import main.java.Presenters.ParticipantRafflePresenter;
import main.java.Web.ParticipantSystemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class ParticipantRafflePage extends JFrame {
    private JFrame rFrame;
    private JScrollPane rScrollPane;
    private JPanel rPanel;
    private boolean isEnrolled;
    private String raffleID;
    private JScrollPane infoScrollPane;
    private JTextArea raffleInfoField;
    private JButton joinButton;
    private JTextArea[] tasks;
    private JTextField[] taskNames;
    private JButton[] executeButtons;
    private JButton backButton;
    private JPanel bigPanel;
    private String username;
    private JScrollPane[] taskDetailsScrollPane;
    private JButton exitButton;
    private ParticipantRafflePresenter prp;

    public ParticipantRafflePage(String raffleID,String username)
    {
        this.raffleID = raffleID;
        this.username = username;
        prp = new ParticipantRafflePresenter(username, raffleID);
        isEnrolled = prp.isEnrolled();
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
        rScrollPane.setBounds(0,rFrame.getHeight()/6,rFrame.getWidth()-20,(4*rFrame.getHeight()/6));
        rScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        rScrollPane.setBackground(new Color(255,255,255));

        raffleInfoField = new JTextArea(prp.getBasicRaffleDetails());
        raffleInfoField.setEditable(false);
        raffleInfoField.setLineWrap(true);
        raffleInfoField.setPreferredSize(new Dimension(rScrollPane.getWidth(),100));
        raffleInfoField.setMinimumSize(raffleInfoField.getPreferredSize());
        raffleInfoField.setMaximumSize(raffleInfoField.getPreferredSize());
        raffleInfoField.setFont(new Font("Chalkboard",Font.PLAIN,28));

        infoScrollPane = new JScrollPane(raffleInfoField,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        infoScrollPane.setPreferredSize(new Dimension(rScrollPane.getWidth(),180));
        infoScrollPane.setMinimumSize(infoScrollPane.getPreferredSize());
        infoScrollPane.setMaximumSize(infoScrollPane.getPreferredSize());
        infoScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoScrollPane.setBorder(BorderFactory.createEmptyBorder());

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
        joinButton.setFont(new Font("Chalkboard", Font.PLAIN,14));

        exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        exitButton.setPreferredSize(new Dimension(rFrame.getWidth()/6,30));
        exitButton.setMinimumSize(exitButton.getPreferredSize());
        exitButton.setMaximumSize(exitButton.getPreferredSize());
        exitButton.setFont(new Font("Chalkboard", Font.PLAIN,14));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(rFrame,"Are you sure you want to exit?","Confirm exit",JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });

        backButton = new JButton("Go back");
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setPreferredSize(new Dimension(rFrame.getWidth()/6,30));
        backButton.setMinimumSize(backButton.getPreferredSize());
        backButton.setMaximumSize(backButton.getPreferredSize());
        backButton.setFont(new Font("Chalkboard", Font.PLAIN,14));
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

        GroupLayout.ParallelGroup pg = gl.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(infoScrollPane);
        GroupLayout.SequentialGroup sg = gl.createSequentialGroup().addComponent(infoScrollPane);
        pg.addGroup(gl.createSequentialGroup().addComponent(backButton).addComponent(joinButton).addComponent(exitButton));
        sg.addGap(30).addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(backButton).addComponent(joinButton).addComponent(exitButton));
        if(isEnrolled)
        {
            String[] details = new String[0];
            try {
                details = prp.getTaskIDs();
            }catch(IOException ioe)
            {
                JOptionPane.showMessageDialog(rFrame,"Cannot load any tasks for the raffle. Go back to the main page and try again","Error",JOptionPane.ERROR_MESSAGE);
            }
            this.taskNames = new JTextField[details.length];
            this.executeButtons = new JButton[details.length];
            this.tasks = new JTextArea[details.length];
            this.taskDetailsScrollPane = new JScrollPane[details.length];

            for(int i=0;i<details.length;i++)
            {

                try{
                    String[] taskDetails = prp.formatTaskDetails(details[i]);
                    taskNames[i] = new JTextField(taskDetails[0]);
                    taskNames[i].setEditable(false);
                    taskNames[i].setPreferredSize(new Dimension(3*rScrollPane.getWidth()/4,40));
                    taskNames[i].setMinimumSize(taskNames[i].getPreferredSize());
                    taskNames[i].setMaximumSize(taskNames[i].getPreferredSize());
                    taskNames[i].setFont(new Font("Chalkboard",Font.PLAIN,22));

                    if(!prp.hasCompletedTask(details[i]))
                        executeButtons[i] = new JButton("Complete task");
                    else{
                        executeButtons[i] = new JButton("Task completed");
                        executeButtons[i].setEnabled(false);
                    }
                    executeButtons[i].setAlignmentX(Component.RIGHT_ALIGNMENT);
                    executeButtons[i].setPreferredSize(new Dimension(rScrollPane.getWidth()/4,40));
                    executeButtons[i].setMinimumSize(executeButtons[i].getPreferredSize());
                    executeButtons[i].setMaximumSize(executeButtons[i].getPreferredSize());
                    executeButtons[i].setFont(new Font("Chalkboard", Font.PLAIN,14));
                    executeButtons[i].addActionListener( new TaskButton(details[i], username+":"+raffleID,executeButtons[i],rFrame));

                    tasks[i] = new JTextArea(taskDetails[1]);
                    tasks[i].setEditable(false);
                    tasks[i].setLineWrap(true);
                    tasks[i].setPreferredSize(new Dimension(rScrollPane.getWidth(),50));
                    tasks[i].setMinimumSize(tasks[i].getPreferredSize());
                    tasks[i].setMaximumSize(tasks[i].getPreferredSize());
                    tasks[i].setAlignmentX(SwingConstants.CENTER);
                    tasks[i].setFont(new Font("Chalkboard",Font.PLAIN,22));

                    taskDetailsScrollPane[i] = new JScrollPane(tasks[i]);
                    taskDetailsScrollPane[i].setPreferredSize(new Dimension(rScrollPane.getWidth(),100));
                    taskDetailsScrollPane[i].setMinimumSize(taskDetailsScrollPane[i].getPreferredSize());
                    taskDetailsScrollPane[i].setMaximumSize(taskDetailsScrollPane[i].getPreferredSize());
                    taskDetailsScrollPane[i].setAlignmentX(Component.CENTER_ALIGNMENT);
                    taskDetailsScrollPane[i].setBorder(BorderFactory.createEmptyBorder());
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(rFrame,e.getStackTrace(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            for(int i=0;i<taskNames.length;i++)
            {
                pg.addGroup(gl.createSequentialGroup().addComponent(taskNames[i]).addComponent(executeButtons[i]));
                pg.addComponent(taskDetailsScrollPane[i]);
                sg.addGap(30).addGroup(gl.createParallelGroup().addComponent(taskNames[i]).addComponent(executeButtons[i]));
                sg.addComponent(taskDetailsScrollPane[i]);
            }
            gl.setHorizontalGroup(pg);
            gl.setVerticalGroup(sg);
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
                    ParticipantSystemManager psm = new ParticipantSystemManager();
                    try {
                        psm.storeRaffleInParticipantData(raffleID, username);
                    }catch(Exception exp)
                    {
                        JOptionPane.showMessageDialog(rFrame,exp.getMessage() + "\n " + Arrays.toString(exp.getStackTrace()),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                    joinButton.setEnabled(false);
                    joinButton.setText("Joined");
                    isEnrolled = true;

                    String[] details = new String[0];
                    try {
                        details = prp.getTaskIDs();
                    }catch(IOException ioe)
                    {
                        JOptionPane.showMessageDialog(rFrame,"Cannot load any tasks for the raffle. Go back to the main page and try again","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    taskNames = new JTextField[details.length];
                    executeButtons = new JButton[details.length];
                    tasks = new JTextArea[details.length];
                    taskDetailsScrollPane = new JScrollPane[details.length];

                    for(int i=0;i<details.length;i++)
                    {

                        try{
                            String[] taskDetails = prp.formatTaskDetails(details[i]);
                            taskNames[i] = new JTextField(taskDetails[0]);
                            taskNames[i].setEditable(false);
                            taskNames[i].setPreferredSize(new Dimension(3*rScrollPane.getWidth()/4,40));
                            taskNames[i].setMinimumSize(taskNames[i].getPreferredSize());
                            taskNames[i].setMaximumSize(taskNames[i].getPreferredSize());
                            taskNames[i].setFont(new Font("Chalkboard",Font.PLAIN,22));

                            if(!prp.hasCompletedTask(details[i]))
                                executeButtons[i] = new JButton("Complete task");
                            else{
                                executeButtons[i] = new JButton("Task completed");
                                executeButtons[i].setEnabled(false);
                            }
                            executeButtons[i].setAlignmentX(Component.RIGHT_ALIGNMENT);
                            executeButtons[i].setPreferredSize(new Dimension(rScrollPane.getWidth()/4,40));
                            executeButtons[i].setMinimumSize(executeButtons[i].getPreferredSize());
                            executeButtons[i].setMaximumSize(executeButtons[i].getPreferredSize());
                            executeButtons[i].setFont(new Font("Chalkboard", Font.PLAIN,14));
                            executeButtons[i].addActionListener( new TaskButton(details[i], username+":"+raffleID,executeButtons[i],rFrame));

                            tasks[i] = new JTextArea(taskDetails[1]);
                            tasks[i].setEditable(false);
                            tasks[i].setLineWrap(true);
                            tasks[i].setPreferredSize(new Dimension(rScrollPane.getWidth(),50));
                            tasks[i].setMinimumSize(tasks[i].getPreferredSize());
                            tasks[i].setMaximumSize(tasks[i].getPreferredSize());
                            tasks[i].setAlignmentX(SwingConstants.CENTER);
                            tasks[i].setFont(new Font("Chalkboard",Font.PLAIN,22));

                            taskDetailsScrollPane[i] = new JScrollPane(tasks[i]);
                            taskDetailsScrollPane[i].setPreferredSize(new Dimension(rScrollPane.getWidth(),100));
                            taskDetailsScrollPane[i].setMinimumSize(taskDetailsScrollPane[i].getPreferredSize());
                            taskDetailsScrollPane[i].setMaximumSize(taskDetailsScrollPane[i].getPreferredSize());
                            taskDetailsScrollPane[i].setAlignmentX(Component.CENTER_ALIGNMENT);
                            taskDetailsScrollPane[i].setBorder(BorderFactory.createEmptyBorder());
                        }
                        catch(Exception exp)
                        {
                            JOptionPane.showMessageDialog(rFrame,exp.getStackTrace(),"Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    for(int i=0;i<taskNames.length;i++)
                    {
                        pg.addGroup(gl.createSequentialGroup().addComponent(taskNames[i]).addComponent(executeButtons[i]));
                        pg.addComponent(taskDetailsScrollPane[i]);
                        sg.addGap(30).addGroup(gl.createParallelGroup().addComponent(taskNames[i]).addComponent(executeButtons[i]));
                        sg.addComponent(taskDetailsScrollPane[i]);
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

