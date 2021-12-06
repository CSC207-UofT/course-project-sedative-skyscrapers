package main.java.GUI;

import main.java.Presenters.OrganizerPresenter;
import main.java.Presenters.ParticipantRafflePresenter;
import main.java.Web.OrganizerSystemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//OrganizerMainPage
public class OrganizerMainPage extends JFrame {
    private String username;
    private JLabel welcomeLabel;
    private JFrame orgFrame;
    private JPanel orgPanel;
    private JPanel bigPanel;
    private JScrollPane scrollPane;
    private JButton[] orgRaffle;
    private JLabel orgRaffleLabel;
    private JButton createRaffle;
    private JButton logout;
    private JButton exitButton;
    private OrganizerPresenter op;

    public OrganizerMainPage(String username) {
        this.username = username;
        op = new OrganizerPresenter(username);
        initComponents();
    }

    private void initComponents() {
        orgFrame = new JFrame("Home Page");
        orgFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orgFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        bigPanel = new JPanel();
        bigPanel.setBounds(0, 0, orgFrame.getWidth(), orgFrame.getHeight());
        bigPanel.setBackground(new Color(0,240,100,100));

        orgPanel = new JPanel();
        GroupLayout gl = new GroupLayout(orgPanel);
        orgPanel.setLayout(gl);
        orgPanel.setBackground(new Color(160,160,160,150));

        scrollPane = new JScrollPane(orgPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0,orgFrame.getHeight()/10,orgFrame.getWidth(),(6*orgFrame.getHeight()/8));
        scrollPane.setBackground(new Color(160,160,160,150));
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
        orgRaffleLabel.setFont(new Font("Apple Chancery", Font.PLAIN, 32));
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

        exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        exitButton.setPreferredSize(new Dimension(orgFrame.getWidth()/6,50));
        exitButton.setMinimumSize(exitButton.getPreferredSize());
        exitButton.setMaximumSize(exitButton.getPreferredSize());
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(orgFrame,"Are you sure you want to exit?","Confirm exit",JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION)
                    System.exit(0);
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
                if (choice == JOptionPane.YES_OPTION)
                {
                    orgFrame.setVisible(false);
                    SignInPage sip = new SignInPage();
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
                .addGap(50)
                .addComponent(orgRaffleLabel)
                .addGap(30);



        String[] raffleIDs = op.getAllRaffleIDs();

        orgRaffle = new JButton[raffleIDs.length];

        if(!(raffleIDs.length == 0))
        {
            for(int i = 0;i< raffleIDs.length;i++)
            {
                ArrayList<Object> raffleInfo = osm.getRaffleDetails(raffleIDs[i]);
                orgRaffle[i] = new JButton( op.formatRaffleDetails(raffleIDs[i],i));
                orgRaffle[i].setAlignmentX(Component.LEFT_ALIGNMENT);
                orgRaffle[i].setPreferredSize(new Dimension(7 * orgFrame.getWidth() / 8, 50));
                orgRaffle[i].setMinimumSize(orgRaffle[i].getPreferredSize());
                orgRaffle[i].setMaximumSize(orgRaffle[i].getPreferredSize());
                orgRaffle[i].setFont(new Font("Calibri",Font.ITALIC,14));
                orgRaffle[i].addActionListener(new RaffleButton(raffleIDs[i],orgFrame,false,username));
                pg.addComponent(orgRaffle[i]);
                sg.addComponent(orgRaffle[i]);
            }

        }

        pg.addGroup(gl.createSequentialGroup().addComponent(logout).addComponent(createRaffle).addComponent(exitButton));
        sg.addGap(70).addGroup(gl.createParallelGroup().addComponent(logout).addComponent(createRaffle).addComponent(exitButton));

        gl.setHorizontalGroup(pg);
        gl.setVerticalGroup(sg);

        orgFrame.getContentPane().add(scrollPane);
        orgFrame.getContentPane().add(bigPanel);
        orgFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        orgFrame.setVisible(true);
    }
}
