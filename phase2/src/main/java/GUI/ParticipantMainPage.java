package main.java.GUI;

import main.java.Presenters.ParticipantPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ParticipantMainPage extends JFrame {
    private String username;
    private JLabel welcomeLabel;
    private JFrame mpFrame;
    private JPanel mpPanel;
    private JPanel bigPanel;
    private JScrollPane scrollPane;
    private JButton[] userRaffles;
    private JLabel userRaffleLabel;
    private JLabel searchLabel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton[] searchedButtons;
    private JButton exitButton;
    private JButton logout;
    private JComboBox<String> searchOption;
    private ParticipantPresenter pp;

    public ParticipantMainPage(String username)
    {
        this.username = username;
        pp = new ParticipantPresenter(username);
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
        userRaffleLabel.setFont(new Font("Apple Chancery",Font.PLAIN,36));
        userRaffleLabel.setForeground(new Color(0,0,0));

        String[] displayRaffles = pp.getRaffleDetailstoDisplay();

        userRaffles = new JButton[displayRaffles.length];
        for(int i=0;i<userRaffles.length;i++)
        {
            userRaffles[i] = new JButton(pp.formatDetails(displayRaffles[i],i));
            userRaffles[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            userRaffles[i].setPreferredSize(new Dimension(7*mpFrame.getWidth()/8, 50));
            userRaffles[i].setMinimumSize(userRaffles[i].getPreferredSize());
            userRaffles[i].setMaximumSize(userRaffles[i].getPreferredSize());
            userRaffles[i].setFont(new Font("Lucida Calligraphy",Font.ITALIC,14));
            userRaffles[i].addActionListener(new RaffleButton(displayRaffles[i], mpFrame,true,username));
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
        searchField.setPreferredSize(new Dimension(5*mpFrame.getWidth()/8,40));
        searchField.setMinimumSize(searchField.getPreferredSize());
        searchField.setMaximumSize(searchField.getPreferredSize());
        searchField.setFont(new Font("TimesRoman",Font.ITALIC,16));
        searchField.setForeground(new Color(0,0,0, 100));
        searchField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
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

        logout = new JButton("Logout");
        logout.setAlignmentX(Component.LEFT_ALIGNMENT);
        logout.setPreferredSize(new Dimension(mpFrame.getWidth()/8, 40));
        logout.setMinimumSize(logout.getPreferredSize());
        logout.setMaximumSize(logout.getPreferredSize());
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(mpFrame,"Are you sure you want to logout?","Confirm",JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION)
                {
                    SignInPage sip = new SignInPage();
                    mpFrame.setVisible(false);
                }
            }
        });

        String options[] = {"Raffle Name","Organizer Name","Raffle ID"};
        searchOption = new JComboBox<String>(options);
        searchOption.setPreferredSize(new Dimension(mpFrame.getWidth()/8, 40));
        searchOption.setMaximumSize(searchOption.getPreferredSize());
        searchOption.setMinimumSize(searchOption.getPreferredSize());

        exitButton = new JButton("Logout & Exit");
        exitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        exitButton.setPreferredSize(new Dimension(mpFrame.getWidth()/8, 40));
        exitButton.setMinimumSize(exitButton.getPreferredSize());
        exitButton.setMaximumSize(exitButton.getPreferredSize());
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(mpFrame,"Are you sure you want to exit?","Confirm exit",JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });

        mpPanel = new JPanel();
        GroupLayout gl = new GroupLayout(mpPanel);
        mpPanel.setLayout(gl);
        mpPanel.setBackground(new Color(160,160,160,100));

        scrollPane = new JScrollPane(mpPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0,mpFrame.getHeight()/10,mpFrame.getWidth(),(7*mpFrame.getHeight()/10));
        scrollPane.setBackground(new Color(160,160,160,100));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        GroupLayout.ParallelGroup pg = gl.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(welcomeLabel).addComponent(userRaffleLabel);
        GroupLayout.SequentialGroup sg = gl.createSequentialGroup().addComponent(welcomeLabel).addGap(30).addComponent(userRaffleLabel).addGap(30);
        for(int i = 0;i<userRaffles.length;i++) {
            pg.addComponent(userRaffles[i]);
            sg.addComponent(userRaffles[i]);
        }

        pg.addGroup(gl.createSequentialGroup().addComponent(logout).addComponent(exitButton)).addComponent(searchLabel).addGroup(gl.createSequentialGroup().addComponent(searchField).addComponent(searchOption).addComponent(searchButton));
        sg.addGap(30).addGroup(gl.createParallelGroup().addComponent(logout).addComponent(exitButton)).addGap(50).addComponent(searchLabel).addGap(10).addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(searchField).addComponent(searchOption).addComponent(searchButton)).addGap(30);
        gl.setHorizontalGroup(pg);
        gl.setVerticalGroup(sg);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupLayout.ParallelGroup tpg = gl.createParallelGroup(GroupLayout.Alignment.CENTER);
                GroupLayout.SequentialGroup tsg = gl.createSequentialGroup();
                String toDisplay[];
                if(searchOption.getSelectedIndex() == 0) {
                    toDisplay = pp.getAllRafflesWithRaffleName(searchField.getText());
                    if (toDisplay.length>0) {
                        searchedButtons = new JButton[toDisplay.length];
                        for (int i = 0; i < toDisplay.length; i++) {
                            searchedButtons[i] = new JButton(pp.formatDetails(toDisplay[i], i));
                            searchedButtons[i].setAlignmentX(Component.LEFT_ALIGNMENT);
                            searchedButtons[i].setPreferredSize(new Dimension(7 * mpFrame.getWidth() / 8, 50));
                            searchedButtons[i].setMinimumSize(searchedButtons[i].getPreferredSize());
                            searchedButtons[i].setMaximumSize(searchedButtons[i].getPreferredSize());
                            searchedButtons[i].addActionListener(new RaffleButton(toDisplay[i], mpFrame, true, username));
                            tpg.addComponent(searchedButtons[i]);
                            tsg.addComponent(searchedButtons[i]);
                        }
                        gl.setHorizontalGroup(pg.addGroup(tpg));
                        gl.setVerticalGroup(sg.addGroup(tsg));
                    } else {
                        JOptionPane.showMessageDialog(mpFrame, "No such Raffle found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(searchOption.getSelectedIndex() == 1)
                {
                    toDisplay = pp.getAllRafflesWithOrgName(searchField.getText());
                    if (toDisplay.length>0) {
                        searchedButtons = new JButton[toDisplay.length];
                        for (int i = 0; i < toDisplay.length; i++) {
                            searchedButtons[i] = new JButton(pp.formatDetails(toDisplay[i], i));
                            searchedButtons[i].setAlignmentX(Component.LEFT_ALIGNMENT);
                            searchedButtons[i].setPreferredSize(new Dimension(7 * mpFrame.getWidth() / 8, 50));
                            searchedButtons[i].setMinimumSize(searchedButtons[i].getPreferredSize());
                            searchedButtons[i].setMaximumSize(searchedButtons[i].getPreferredSize());
                            searchedButtons[i].addActionListener(new RaffleButton(toDisplay[i], mpFrame, true, username));
                            tpg.addComponent(searchedButtons[i]);
                            tsg.addComponent(searchedButtons[i]);
                        }
                        gl.setHorizontalGroup(pg.addGroup(tpg));
                        gl.setVerticalGroup(sg.addGroup(tsg));
                    } else {
                        JOptionPane.showMessageDialog(mpFrame, "No such Raffle found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    String display = pp.getAllRafflesWithRaffleID(searchField.getText());
                    if (display!="") {
                        searchedButtons = new JButton[1];
                        searchedButtons[0] = new JButton(display);
                        searchedButtons[0].setAlignmentX(Component.LEFT_ALIGNMENT);
                        searchedButtons[0].setPreferredSize(new Dimension(7 * mpFrame.getWidth() / 8, 50));
                        searchedButtons[0].setMinimumSize(searchedButtons[0].getPreferredSize());
                        searchedButtons[0].setMaximumSize(searchedButtons[0].getPreferredSize());
                        searchedButtons[0].addActionListener(new RaffleButton(searchField.getText(), mpFrame, true, username));
                        tpg.addComponent(searchedButtons[0]);
                        tsg.addComponent(searchedButtons[0]);
                        gl.setHorizontalGroup(pg.addGroup(tpg));
                        gl.setVerticalGroup(sg.addGroup(tsg));
                    } else {
                        JOptionPane.showMessageDialog(mpFrame, "No such Raffle found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        mpFrame.getContentPane().add(scrollPane);
        mpFrame.getContentPane().add(bigPanel);
        mpFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mpFrame.setVisible(true);

    }
}
