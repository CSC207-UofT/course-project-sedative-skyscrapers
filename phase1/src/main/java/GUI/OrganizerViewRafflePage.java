package main.java.GUI;

import main.java.Web.OrganizerSystemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class OrganizerViewRafflePage extends JFrame {
    public static JFrame frame;
    public static JPanel bigPanel;
    public static JTextArea raffleInfo;
    public static JButton findWinners;
    public static JButton backButton;
    public static JTextArea winnerInfo;
    public static JPanel smallPanel;
    public static JScrollPane scrollPane;
    public String raffleID;
    public String username;

    public OrganizerViewRafflePage(String raffleID, String username)
    {
        this.raffleID = raffleID;
        this.username = username;
        initComponents();
    }
    private void initComponents()
    {

        frame = new JFrame("View Raffle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        bigPanel = new JPanel();
        bigPanel.setBounds(0,0,frame.getWidth(),frame.getHeight());
        bigPanel.setBackground(new Color(0,240,100,100));

        smallPanel = new JPanel();
        GroupLayout gl = new GroupLayout(smallPanel);
        smallPanel.setLayout(gl);
        smallPanel.setBackground(new Color(255,255,255));

        scrollPane = new JScrollPane(smallPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0,frame.getHeight()/6,frame.getWidth(),(4*frame.getHeight()/6));
        scrollPane.setBackground(new Color(255,255,255));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        OrganizerSystemManager osm = new OrganizerSystemManager();
        ArrayList<Object> raffleDetails = osm.getRaffleDetails(raffleID);
        raffleInfo = new JTextArea("Raffle Name: "+raffleDetails.get(0));
        raffleInfo.append("\nNumber of Winners: "+raffleDetails.get(1));
        raffleInfo.append("\nRaffle rules: \n"+raffleDetails.get(2));
        raffleInfo.append("\nEnd Date: "+raffleDetails.get(3));
        for(int i=0;i<((ArrayList<String>)raffleDetails.get(4)).size();i++)
        {
            try {
                ArrayList<String> taskInfo = osm.getTaskInfo(((ArrayList<String>) raffleDetails.get(4)).get(i));
                raffleInfo.append("\n\nTask Name: " + taskInfo.get(0));
                raffleInfo.append("\nDescription:\n" + taskInfo.get(1));
                raffleInfo.append("\nLink: " + taskInfo.get(2) + "\n");
            }
            catch(FileNotFoundException fnfe)
            {
                JOptionPane.showMessageDialog(frame,"No tasks found","Error",JOptionPane.ERROR_MESSAGE);
            }

        }
        raffleInfo.setEditable(false);

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(frame.getWidth()/4,40));
        backButton.setMinimumSize(backButton.getPreferredSize());
        backButton.setMaximumSize(backButton.getPreferredSize());
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                OrganizerMainPage omp = new OrganizerMainPage(username);
            }
        });

        findWinners = new JButton("Find Winners");
        findWinners.setPreferredSize(new Dimension(frame.getWidth()/4,40));
        findWinners.setMinimumSize(findWinners.getPreferredSize());
        findWinners.setMaximumSize(findWinners.getPreferredSize());
        findWinners.setAlignmentX(Component.LEFT_ALIGNMENT);



        if((((HashMap<String,Boolean>)raffleDetails.get(6)).size())==0)
        {

            findWinners.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    osm.generateWinnersList(raffleID);
                    findWinners.setText("Winners declared!");
                    findWinners.setEnabled(false);
                }
            });
        }
        else
        {
            findWinners.setText("Winners declared!");
            findWinners.setEnabled(false);
        }

        winnerInfo = new JTextArea();
        winnerInfo.setEditable(false);
        winnerInfo.setPreferredSize(new Dimension(frame.getWidth() - 30,40));
        winnerInfo.setMinimumSize(winnerInfo.getPreferredSize());
        winnerInfo.setMaximumSize(winnerInfo.getPreferredSize());

        if((((HashMap<String,Boolean>)raffleDetails.get(6)).size())==0)
        {
            winnerInfo.append("The winners are:");
            for(int i = 0;i<((ArrayList<String>)raffleDetails.get(6)).size();i++)
            {
                winnerInfo.append(((ArrayList<String>)raffleDetails.get(6)).get(i));
            }
        }
        //winnerInfo

        GroupLayout.ParallelGroup pg = gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(raffleInfo)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(backButton)
                        .addComponent(findWinners)
                );

        GroupLayout.SequentialGroup sg = gl.createSequentialGroup()
                .addComponent(raffleInfo)
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(backButton)
                        .addComponent(findWinners)
                );

        gl.setHorizontalGroup(pg);
        gl.setVerticalGroup(sg);

        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(bigPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }


}

