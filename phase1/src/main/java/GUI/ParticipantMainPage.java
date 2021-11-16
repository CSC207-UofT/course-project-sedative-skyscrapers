package main.java.GUI;

import main.java.Web.OrganizerSystemManager;
import main.java.Web.ParticipantSystemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Set;

public class ParticipantMainPage extends JFrame {
    public String username;
    public static JLabel welcomeLabel;
    public static JFrame mpFrame;
    public static JPanel mpPanel;
    public static JPanel bigPanel;
    public static JScrollPane scrollPane;
    public JButton[] userRaffles;
    public static JLabel userRaffleLabel;
    public static JLabel searchLabel;
    public static JTextField searchField;
    public static JButton searchButton;
    public static JButton searchedButton;

    public ParticipantMainPage(String username)
    {
        this.username = username;
        initComponents();
    }

    private void initComponents()
    {
        mpFrame = new JFrame("Home Page");
        mpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mpFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        bigPanel = new JPanel();
        bigPanel.setBounds(0,0,mpFrame.getWidth(),mpFrame.getHeight());
        bigPanel.setBackground(new Color(0,240,100,100));

        // When you add code for displaying a user's raffles, if a user has not joined any raffles, then make the label display you aren't in any raffle

        welcomeLabel = new JLabel("Welcome",SwingConstants.CENTER);
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        welcomeLabel.setPreferredSize(new Dimension(mpFrame.getWidth(),60));
        welcomeLabel.setMinimumSize(welcomeLabel.getPreferredSize());
        welcomeLabel.setMaximumSize(welcomeLabel.getPreferredSize());
        welcomeLabel.setFont(new Font("Apple Chancery",Font.BOLD,50));
        welcomeLabel.setForeground(new Color(0,0,0));

        userRaffleLabel = new JLabel("Your raffles:",SwingConstants.LEFT);
        userRaffleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userRaffleLabel.setPreferredSize(new Dimension(7*mpFrame.getWidth()/8,40));
        userRaffleLabel.setMinimumSize(userRaffleLabel.getPreferredSize());
        userRaffleLabel.setMaximumSize(userRaffleLabel.getPreferredSize());
        userRaffleLabel.setFont(new Font("Apple Chancery",Font.PLAIN,28));
        userRaffleLabel.setForeground(new Color(0,0,0));

        /*Raffle[] sampleRaffles = new Raffle[15];
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        sampleRaffles[0] = new Raffle(new User("org1"), "Raffle 1", 5, LocalDate.of(2021,12,31));
        sampleRaffles[1] = new Raffle(new User("org2"), "Raffle 2", 5, LocalDate.of(2021,12,31));
        sampleRaffles[2] = new Raffle(new User("org3"), "Raffle 3", 5, LocalDate.of(2021,12,31));
        sampleRaffles[3] = new Raffle(new User("org4"), "Raffle 4", 5, LocalDate.of(2021,12,31));
        sampleRaffles[4] = new Raffle(new User("org4"), "Raffle 4", 5, LocalDate.of(2021,12,31));
        sampleRaffles[5] = new Raffle(new User("org4"), "Raffle 4", 5, LocalDate.of(2021,12,31));
        sampleRaffles[6] = new Raffle(new User("org4"), "Raffle 4", 5, LocalDate.of(2021,12,31));
        sampleRaffles[7] = new Raffle(new User("org4"), "Raffle 4", 5, LocalDate.of(2021,12,31));
        sampleRaffles[8] = new Raffle(new User("org4"), "Raffle 4", 5, LocalDate.of(2021,12,31));
        sampleRaffles[9] = new Raffle(new User("org4"), "Raffle 4", 5, LocalDate.of(2021,12,31));
        sampleRaffles[10] = new Raffle(new User("org4"), "Raffle 4", 5, LocalDate.of(2021,12,31));
        sampleRaffles[11] = new Raffle(new User("org4"), "Raffle 4", 5, LocalDate.of(2021,12,31));
        sampleRaffles[12] = new Raffle(new User("org4"), "Raffle 4", 5, LocalDate.of(2021,12,31));
        sampleRaffles[13] = new Raffle(new User("org4"), "Raffle 4", 5, LocalDate.of(2021,12,31));
        sampleRaffles[14] = new Raffle(new User("org4"), "Raffle 4", 5, LocalDate.of(2021,12,31));*/

        ParticipantSystemManager psm = new ParticipantSystemManager();
        ArrayList<String> raffleArrayList = psm.getPartRaffleList(username);
        String[] raffleList = new String[raffleArrayList.size()];
        OrganizerSystemManager osm = new OrganizerSystemManager();
        for(int i = 0;i<raffleList.length;i++)
        {
            String[] temp = raffleArrayList.get(i).split(":");
            raffleList[i] = temp[0];
        }

        userRaffles = new JButton[raffleList.length];
        for(int i=0;i<userRaffles.length;i++)
        {
            ArrayList<Object> raffleInfo = osm.getRaffleDetails(raffleList[i]);
            userRaffles[i] = new JButton((i+1)+". \t\t\t"+raffleInfo.get(0)+"\t\t\t"+raffleList[i]+"\t\t\t"+raffleInfo.get(3));
            userRaffles[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            userRaffles[i].setPreferredSize(new Dimension(7*mpFrame.getWidth()/8, 50));
            userRaffles[i].setMinimumSize(userRaffles[i].getPreferredSize());
            userRaffles[i].setMaximumSize(userRaffles[i].getPreferredSize());
            userRaffles[i].addActionListener(new RaffleButton(raffleList[i],mpFrame,true,username));

        }

        if(userRaffles.length == 0)
        {
            userRaffleLabel.setText("You haven't joined any raffles so far. Join a raffle by searching the raffle ID below!!");
        }

        searchLabel = new JLabel("Search new and upcoming Raffles:",SwingConstants.LEFT);
        searchLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchLabel.setPreferredSize(new Dimension(7*mpFrame.getWidth()/8,40));
        searchLabel.setMinimumSize(searchLabel.getPreferredSize());
        searchLabel.setMaximumSize(searchLabel.getPreferredSize());
        searchLabel.setFont(new Font("Apple Chancery",Font.BOLD,28));
        searchLabel.setForeground(new Color(0,0,0));

        searchField = new JTextField("Enter Raffle ID");
        searchField.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchField.setPreferredSize(new Dimension(6*mpFrame.getWidth()/8,40));
        searchField.setMinimumSize(searchField.getPreferredSize());
        searchField.setMaximumSize(searchField.getPreferredSize());
        searchField.setFont(new Font("TimesRoman",Font.ITALIC,16));
        searchField.setForeground(new Color(0,0,0, 100));
        searchField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (searchField.getText().equals("Enter Raffle ID"))
                {
                    searchField.setText("");
                    searchField.setFont(new Font("Calibri",Font.PLAIN,16));
                    searchField.setForeground(new Color(0,0,0));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (searchField.getText().equals(""))

                {
                    searchField.setText("Enter Raffle ID");
                    searchField.setFont(new Font("TimesRoman",Font.ITALIC,16));
                    searchField.setForeground(new Color(0,0,0,100));
                }
            }
        });

        searchButton = new JButton("Search");
        searchButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchButton.setPreferredSize(new Dimension(mpFrame.getWidth()/8, 40));
        searchButton.setMinimumSize(searchButton.getPreferredSize());
        searchButton.setMaximumSize(searchButton.getPreferredSize());
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<String> ids = osm.getAllRaffleID();
                if(ids.contains(searchField.getText()))
                {
                    ArrayList<Object> raffleInfo = osm.getRaffleDetails(searchField.getText());
                    searchedButton = new JButton(raffleInfo.get(0)+"\t\t\t"+searchField.getText()+"\t\t\t"+raffleInfo.get(3));
                    searchedButton.setAlignmentX(Component.LEFT_ALIGNMENT);
                    searchedButton.setPreferredSize(new Dimension(7*mpFrame.getWidth()/8, 50));
                    searchedButton.setMinimumSize(searchedButton.getPreferredSize());
                    searchedButton.setMaximumSize(searchedButton.getPreferredSize());
                    searchedButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ParticipantRafflePage prp = new ParticipantRafflePage(searchField.getText(),username);
                            mpFrame.setVisible(false);
                        }
                    });
                }
                else
                {
                    JOptionPane.showMessageDialog(mpFrame,"No such Raffle found","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mpPanel = new JPanel();
        GroupLayout gl = new GroupLayout(mpPanel);
        mpPanel.setLayout(gl);
        mpPanel.setBackground(new Color(255,255,255));

        scrollPane = new JScrollPane(mpPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0,mpFrame.getHeight()/6,mpFrame.getWidth(),(4*mpFrame.getHeight()/6));
        scrollPane.setBackground(new Color(255,255,255));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        GroupLayout.ParallelGroup pg = gl.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(welcomeLabel).addComponent(userRaffleLabel);
        GroupLayout.SequentialGroup sg = gl.createSequentialGroup().addComponent(welcomeLabel).addGap(30).addComponent(userRaffleLabel).addGap(30);
        for(int i = 0;i<userRaffles.length;i++) {
            pg.addComponent(userRaffles[i]);
            sg.addComponent(userRaffles[i]);
        }
        pg.addComponent(searchLabel).addGroup(gl.createSequentialGroup().addComponent(searchField).addComponent(searchButton));
        sg.addGap(50).addComponent(searchLabel).addGap(10).addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(searchField).addComponent(searchButton)).addGap(30);
        gl.setHorizontalGroup(pg);
        gl.setVerticalGroup(sg);

        mpFrame.getContentPane().add(scrollPane);
        mpFrame.getContentPane().add(bigPanel);
        mpFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mpFrame.setVisible(true);

    }
}
