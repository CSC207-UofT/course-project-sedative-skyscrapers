package main.java.GUI;

import main.java.Presenters.OrganizerRafflePresenter;
import main.java.SystemManagers.OrganizerSystemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    /**
     * Initializes raffleID and username and calls initComponents()
     * @param raffleID - raffleID
     * @param username - username
     */

    public OrganizerViewRafflePage(String raffleID, String username)
    {
        this.raffleID = raffleID;
        this.username = username;
        o = new OrganizerRafflePresenter(username,raffleID);
        this.winnersGenerated = o.winnersGenerated();
        initComponents();
    }

    /**
     * initComponents initializes and sets important attributes of all the components of the GUI
     */
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
        try {
            raffleInfo = new JTextArea(o.getRaffleDetails());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,"Cannot load raffle details. Please go back and try again.","Error",JOptionPane.ERROR_MESSAGE);
            raffleInfo = new JTextArea();
        }
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
                OrganizerMainPage omp = new OrganizerMainPage(username);
                frame.setVisible(false);
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
                    OrganizerSystemManager osm = new OrganizerSystemManager();
                    osm.setRaffleID(raffleID);
                    osm.setUsername(username);
                    osm.generateWinnersList();

                    findWinners.setText("Winners declared!");
                    findWinners.setEnabled(false);
                    try {
                        raffleInfo.setText(o.getRaffleDetails());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
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

