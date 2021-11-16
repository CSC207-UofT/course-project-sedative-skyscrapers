package main.java.GUI;

import main.java.Web.OrganizerSystemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrganizerMainPage extends JFrame {
    public String username;
    public static JLabel welcomeLabel;
    public static JFrame orgFrame;
    public static JPanel orgPanel;
    public static JPanel bigPanel;
    public static JScrollPane scrollPane;
    public JButton orgRaffle;
    public static JLabel orgRaffleLabel;
    public static JButton createRaffle;
    public static JButton logout;

    public OrganizerMainPage(String username) {
        this.username = username;
        initComponents();
    }

    private void initComponents() {
        orgFrame = new JFrame("Home Page");
        orgFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orgFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        bigPanel = new JPanel();
        bigPanel.setBounds(0, 0, orgFrame.getWidth(), orgFrame.getHeight());
        bigPanel.setBackground(new Color(0, 240, 100, 100));

        orgPanel = new JPanel();
        GroupLayout gl = new GroupLayout(orgPanel);
        orgPanel.setLayout(gl);
        orgPanel.setBackground(new Color(255,255,255));

        scrollPane = new JScrollPane(orgPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0,orgFrame.getHeight()/6,orgFrame.getWidth(),(4*orgFrame.getHeight()/6));
        scrollPane.setBackground(new Color(255,255,255));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        welcomeLabel = new JLabel("Welcome!", SwingConstants.CENTER);
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        welcomeLabel.setPreferredSize(new Dimension(orgFrame.getWidth(), 60));
        welcomeLabel.setMinimumSize(welcomeLabel.getPreferredSize());
        welcomeLabel.setMaximumSize(welcomeLabel.getPreferredSize());
        welcomeLabel.setFont(new Font("Apple Chancery", Font.BOLD, 50));
        welcomeLabel.setForeground(new Color(0, 0, 0));

        orgRaffleLabel = new JLabel("Your raffles:", SwingConstants.LEFT);
        orgRaffleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        orgRaffleLabel.setPreferredSize(new Dimension(7 * orgFrame.getWidth() / 8, 40));
        orgRaffleLabel.setMinimumSize(orgRaffleLabel.getPreferredSize());
        orgRaffleLabel.setMaximumSize(orgRaffleLabel.getPreferredSize());
        orgRaffleLabel.setFont(new Font("Apple Chancery", Font.PLAIN, 28));
        orgRaffleLabel.setForeground(new Color(0, 0, 0));

        OrganizerSystemManager osm = new OrganizerSystemManager();

        createRaffle = new JButton("Create a Raffle!!");
        createRaffle.setAlignmentX(Component.LEFT_ALIGNMENT);
        createRaffle.setPreferredSize(new Dimension(orgFrame.getWidth()/6,50));
        createRaffle.setMinimumSize(createRaffle.getPreferredSize());
        createRaffle.setMaximumSize(createRaffle.getPreferredSize());
        createRaffle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrganizerCreateRafflePage o = new OrganizerCreateRafflePage(username);
                orgFrame.setVisible(false);
            }
        });

        logout = new JButton("Logout");
        logout.setAlignmentX(Component.LEFT_ALIGNMENT);
        logout.setPreferredSize(new Dimension(orgFrame.getWidth()/6,50));
        logout.setMinimumSize(logout.getPreferredSize());
        logout.setMaximumSize(logout.getPreferredSize());
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(orgFrame,"Are you sure you want to logout?","Logout",JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.NO_OPTION)
                {

                }
                else
                {

                }

            }
        });

        GroupLayout.ParallelGroup pg = gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(welcomeLabel)
                .addComponent(orgRaffleLabel);

        GroupLayout.SequentialGroup sg = gl.createSequentialGroup()
                .addComponent(welcomeLabel)
                .addComponent(orgRaffleLabel);

        String raffleID = osm.getOrgRaffleID(username);

        if(raffleID=="")
        {
            ArrayList<Object> raffleInfo = osm.getRaffleDetails(raffleID);

            orgRaffle = new JButton( username + "\t\t\t" + raffleInfo.get(0) + "\t\t\t" + raffleID + "\t\t\t" + raffleInfo.get(2).toString());
            orgRaffle.setAlignmentX(Component.LEFT_ALIGNMENT);
            orgRaffle.setPreferredSize(new Dimension(7 * orgFrame.getWidth() / 8, 50));
            orgRaffle.setMinimumSize(orgRaffle.getPreferredSize());
            orgRaffle.setMaximumSize(orgRaffle.getPreferredSize());
            orgRaffle.addActionListener(new RaffleButton(raffleID,orgFrame,false,username));
            pg.addComponent(orgRaffle);
            sg.addComponent(orgRaffle);
        }

        pg.addGroup(gl.createSequentialGroup().addComponent(logout).addComponent(createRaffle));
        sg.addGroup(gl.createParallelGroup().addComponent(logout).addComponent(createRaffle));

        gl.setHorizontalGroup(pg);
        gl.setVerticalGroup(sg);

        orgFrame.getContentPane().add(scrollPane);
        orgFrame.getContentPane().add(bigPanel);
        orgFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        orgFrame.setVisible(true);
    }
}
