package main.java.GUI;
import main.java.Web.OrganizerSystemManager;
import main.java.Web.ParticipantSystemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//Sign up page

public class SignUpPage extends JFrame {
    public JFrame optionFrame;
    public JPanel optionPanel;
    public JButton orgButton;
    public JButton parButton;
    public JLabel optionLabel;
    public JLabel optionSignInLabel;
    public JButton optionSignInButton;
    public JLabel exitLabel;
    public JButton exitButton;

    /**
     * Calls initComponents()
     */

    public SignUpPage()
    {
        initComponents();
    }
    /**
     * initComponents initializes and sets important attributes of all the components of the GUI
     */
    private void initComponents()
    {
        optionFrame = new JFrame("Choose Account");
        optionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        optionFrame.setSize(new Dimension(600,400));

        optionPanel = new JPanel();
        GroupLayout optionGL = new GroupLayout(optionPanel);
        optionPanel.setLayout(optionGL);
        optionPanel.setBounds(optionFrame.getX(),optionFrame.getY(),optionFrame.getWidth(),optionFrame.getHeight());
        optionPanel.setBackground(new Color(0,240,100,100));

        optionGL.setAutoCreateGaps(true);
        optionGL.setAutoCreateContainerGaps(true);

        optionLabel = new JLabel("Select an account that suits you best",SwingConstants.CENTER);
        optionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionLabel.setPreferredSize(new Dimension(optionPanel.getWidth(),50));
        optionLabel.setMinimumSize(optionLabel.getPreferredSize());
        optionLabel.setMaximumSize(optionLabel.getPreferredSize());
        optionLabel.setFont(new Font("Century Gothic",Font.BOLD,24));
        optionLabel.setForeground(new Color(0,0,0));

        parButton = new JButton("Become a participant");
        parButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        parButton.setPreferredSize(new Dimension(optionPanel.getWidth()/4,50));
        parButton.setMinimumSize(parButton.getPreferredSize());
        parButton.setMaximumSize(parButton.getPreferredSize());
        parButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionFrame.setVisible(false);
                ParticipantSignUpPage p = new ParticipantSignUpPage();
            }
        });

        orgButton = new JButton("Become an organizer");
        orgButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        orgButton.setPreferredSize(new Dimension(optionPanel.getWidth()/4,50));
        orgButton.setMinimumSize(orgButton.getPreferredSize());
        orgButton.setMaximumSize(orgButton.getPreferredSize());
        orgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionFrame.setVisible(false);
                OrganizerSignUpPage o = new OrganizerSignUpPage();
            }
        });

        optionSignInLabel = new JLabel("Already have an account? Sign in instead!",SwingConstants.CENTER);
        //optionLabel = new JLabel("Choose the most appropriate account tyoe that suits your interests and needs to the best of your extensive abilities and capabilities", SwingConstants.CENTER);
        optionSignInLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionSignInLabel.setPreferredSize(new Dimension(optionPanel.getWidth(),50));
        optionSignInLabel.setMinimumSize(optionSignInLabel.getPreferredSize());
        optionSignInLabel.setMaximumSize(optionSignInLabel.getPreferredSize());
        optionSignInLabel.setFont(new Font("Century Gothic",Font.BOLD,24));
        optionSignInLabel.setForeground(new Color(0,0,0));

        optionSignInButton = new JButton("Sign In");
        optionSignInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionSignInButton.setPreferredSize(new Dimension(optionPanel.getWidth()/4,50));
        optionSignInButton.setMinimumSize(optionSignInButton.getPreferredSize());
        optionSignInButton.setMaximumSize(optionSignInButton.getPreferredSize());
        optionSignInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionFrame.setVisible(false);
                SignInPage sip = new SignInPage();
            }
        });

        exitLabel = new JLabel("Press the button below to exit",SwingConstants.CENTER);
        //optionLabel = new JLabel("Choose the most appropriate account tyoe that suits your interests and needs to the best of your extensive abilities and capabilities", SwingConstants.CENTER);
        exitLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        exitLabel.setPreferredSize(new Dimension(optionPanel.getWidth(),50));
        exitLabel.setMinimumSize(exitLabel.getPreferredSize());
        exitLabel.setMaximumSize(exitLabel.getPreferredSize());
        exitLabel.setFont(new Font("Century Gothic",Font.BOLD,24));
        exitLabel.setForeground(new Color(0,0,0));

        exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setPreferredSize(new Dimension(optionPanel.getWidth()/4,50));
        exitButton.setMinimumSize(exitButton.getPreferredSize());
        exitButton.setMaximumSize(exitButton.getPreferredSize());
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(optionFrame,"Are you sure you want to exit?","Confirm exit",JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });

        optionGL.setHorizontalGroup(optionGL.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(optionLabel)
                .addGroup(optionGL.createSequentialGroup()
                        .addComponent(parButton)
                        .addComponent(orgButton)
                )
                .addComponent(optionSignInLabel)
                .addComponent(optionSignInButton)
                .addComponent(exitLabel)
                .addComponent(exitButton)
        );

        optionGL.setVerticalGroup(optionGL.createSequentialGroup()
                .addComponent(optionLabel)
                .addGroup(optionGL.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(parButton)
                        .addComponent(orgButton)
                )
                .addComponent(optionSignInLabel)
                .addComponent(optionSignInButton)
                .addComponent(exitLabel)
                .addComponent(exitButton)
        );

        optionFrame.getContentPane().add(optionPanel);
        optionFrame.setVisible(true);
    }

}
