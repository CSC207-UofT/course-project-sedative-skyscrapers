package main.java.GUI;

import main.java.Presenters.OrganizerRafflePresenter;
import main.java.Presenters.ParticipantRafflePresenter;
import main.java.Web.OrganizerSystemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class OrganizerViewRafflePage extends JFrame {
    private JFrame frame;
    private JPanel bigPanel;
    private JTextArea raffleInfo;
    private JButton findWinners;
    private JButton backButton;
    private JPanel smallPanel;
    private JScrollPane scrollPane;
    private String raffleID;
    private String username;
    private boolean winnersGenerated;
    private OrganizerRafflePresenter o;

    public OrganizerViewRafflePage(String raffleID, String username)
    {
        this.raffleID = raffleID;
        this.username = username;
        o = new OrganizerRafflePresenter(username,raffleID);
        this.winnersGenerated = o.winnersGenerated();
        initComponents();
    }
    private void initComponents()
    {

        frame = new JFrame("View Raffle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        bigPanel = new JPanel();
        bigPanel.setBounds(0,0,frame.getWidth(),frame.getHeight());
        bigPanel.setBackground(new Color(0,120,60,120));

        smallPanel = new JPanel();
        GroupLayout gl = new GroupLayout(smallPanel);
        smallPanel.setLayout(gl);
        smallPanel.setBackground(new Color(255,255,255));

        scrollPane = new JScrollPane(smallPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0,frame.getHeight()/10,frame.getWidth(),(6*frame.getHeight()/8));
        scrollPane.setBackground(new Color(255,255,255));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        OrganizerSystemManager osm = new OrganizerSystemManager();
        ArrayList<Object> raffleDetails = osm.getRaffleDetails(raffleID);
        raffleInfo = new JTextArea(o.getRaffleDetails());
        raffleInfo.setEditable(false);
        raffleInfo.setFont(new Font("Chalkboard",Font.PLAIN,22));

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



        if(!winnersGenerated)
        {

            findWinners.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    findWinners.setText("Winners declared!");
                    findWinners.setEnabled(false);
                    raffleInfo.setText(o.getRaffleDetails());
                }
            });
        }
        else
        {
            findWinners.setText("Winners declared!");
            findWinners.setEnabled(false);
        }

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

