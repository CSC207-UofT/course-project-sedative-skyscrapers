package main.java.GUI;

import main.java.SystemManagers.OrganizerSystemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganizerSignUpPage {
    public JFrame orgFrame;
    public JPanel orgBigPanel;
    public JPanel orgPanel;
    public JLabel orgUsernameLabel;
    public JLabel orgPasswordLabel;
    public JLabel orgConfirmPasswordLabel;
    public JLabel orgOrgNameLabel;
    public JLabel orgEmailLabel;
    public JLabel orgPhoneLabel;
    public JTextField orgUsernameTextField;
    public JPasswordField orgPasswordTextField;
    public JPasswordField orgConfirmPasswordTextField;
    public JTextField orgOrgNameTextField;
    public JTextField orgEmailTextField;
    public JTextField orgPhoneTextField;
    public JButton orgBackButton;
    public JButton orgEnterButton;
    public JButton orgExitButton;

    /**
     * Calls initComponents()
     */
    public OrganizerSignUpPage()
    {
        initComponents();
    }
    /**
     * initComponents initializes and sets important attributes of all the components of the GUI
     */
    public void initComponents()
    {
        orgFrame = new JFrame("Organizer Sign up");
        orgFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orgFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        orgBigPanel = new JPanel();
        orgBigPanel.setBounds(0,0,orgFrame.getWidth(),orgFrame.getHeight());
        orgBigPanel.setBackground(new Color(0,240,100,100));

        orgPanel = new JPanel();
        GroupLayout orgGL = new GroupLayout(orgPanel);
        orgPanel.setLayout(orgGL);
        orgPanel.setBounds(orgFrame.getWidth()/8,orgFrame.getHeight()/8,3*orgFrame.getWidth()/4,7*orgFrame.getHeight()/8);
        orgPanel.setBackground(new Color(255,255,255));

        orgGL.setAutoCreateGaps(true);
        orgGL.setAutoCreateContainerGaps(true);

        orgUsernameLabel = new JLabel("Username:",SwingConstants.LEFT);
        orgUsernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        orgUsernameLabel.setPreferredSize(new Dimension(orgPanel.getWidth(),30));
        orgUsernameLabel.setMinimumSize(orgUsernameLabel.getPreferredSize());
        orgUsernameLabel.setMaximumSize(orgUsernameLabel.getPreferredSize());
        orgUsernameLabel.setFont(new Font("Futura",Font.BOLD,18));
        orgUsernameLabel.setForeground(new Color(0,0,0));

        orgPasswordLabel = new JLabel("Password:",SwingConstants.LEFT);
        orgPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        orgPasswordLabel.setPreferredSize(new Dimension(orgPanel.getWidth()/2,30));
        orgPasswordLabel.setMinimumSize(orgPasswordLabel.getPreferredSize());
        orgPasswordLabel.setMaximumSize(orgPasswordLabel.getPreferredSize());
        orgPasswordLabel.setFont(new Font("Futura",Font.BOLD,18));
        orgPasswordLabel.setForeground(new Color(0,0,0));

        orgConfirmPasswordLabel = new JLabel("Confirm Password:",SwingConstants.LEFT);
        orgConfirmPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        orgConfirmPasswordLabel.setPreferredSize(new Dimension(orgPanel.getWidth()/2,30));
        orgConfirmPasswordLabel.setMinimumSize(orgConfirmPasswordLabel.getPreferredSize());
        orgConfirmPasswordLabel.setMaximumSize(orgConfirmPasswordLabel.getPreferredSize());
        orgConfirmPasswordLabel.setFont(new Font("Futura",Font.BOLD,18));
        orgConfirmPasswordLabel.setForeground(new Color(0,0,0));

        orgOrgNameLabel = new JLabel("Organization Name:",SwingConstants.LEFT);
        orgOrgNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        orgOrgNameLabel.setPreferredSize(new Dimension(orgPanel.getWidth(),30));
        orgOrgNameLabel.setMinimumSize(orgOrgNameLabel.getPreferredSize());
        orgOrgNameLabel.setMaximumSize(orgOrgNameLabel.getPreferredSize());
        orgOrgNameLabel.setFont(new Font("Futura",Font.BOLD,18));
        orgOrgNameLabel.setForeground(new Color(0,0,0));

        orgEmailLabel = new JLabel("Email:",SwingConstants.LEFT);
        orgEmailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        orgEmailLabel.setPreferredSize(new Dimension(orgPanel.getWidth()/2,30));
        orgEmailLabel.setMinimumSize(orgEmailLabel.getPreferredSize());
        orgEmailLabel.setMaximumSize(orgEmailLabel.getPreferredSize());
        orgEmailLabel.setFont(new Font("Futura",Font.BOLD,18));
        orgEmailLabel.setForeground(new Color(0,0,0));

        orgPhoneLabel = new JLabel("Phone:",SwingConstants.LEFT);
        orgPhoneLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        orgPhoneLabel.setPreferredSize(new Dimension(orgPanel.getWidth()/2,30));
        orgPhoneLabel.setMinimumSize(orgPhoneLabel.getPreferredSize());
        orgPhoneLabel.setMaximumSize(orgPhoneLabel.getPreferredSize());
        orgPhoneLabel.setFont(new Font("Futura",Font.BOLD,18));
        orgPhoneLabel.setForeground(new Color(0,0,0));

        orgUsernameTextField = new JTextField();
        orgUsernameTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        orgUsernameTextField.setPreferredSize(new Dimension(orgPanel.getWidth(),30));
        orgUsernameTextField.setMinimumSize(orgUsernameTextField.getPreferredSize());
        orgUsernameTextField.setMaximumSize(orgUsernameTextField.getPreferredSize());
        orgUsernameTextField.setFont(new Font("Calibri",Font.PLAIN,18));
        orgUsernameTextField.setForeground(new Color(0,0,0));

        orgOrgNameTextField = new JTextField();
        orgOrgNameTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        orgOrgNameTextField.setPreferredSize(new Dimension(orgPanel.getWidth(),30));
        orgOrgNameTextField.setMinimumSize(orgOrgNameTextField.getPreferredSize());
        orgOrgNameTextField.setMaximumSize(orgOrgNameTextField.getPreferredSize());
        orgOrgNameTextField.setFont(new Font("Calibri",Font.PLAIN,18));
        orgOrgNameTextField.setForeground(new Color(0,0,0));

        orgEmailTextField = new JTextField();
        orgEmailTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        orgEmailTextField.setPreferredSize(new Dimension(orgPanel.getWidth()/2,30));
        orgEmailTextField.setMinimumSize(orgEmailTextField.getPreferredSize());
        orgEmailTextField.setMaximumSize(orgEmailTextField.getPreferredSize());
        orgEmailTextField.setFont(new Font("Calibri",Font.PLAIN,18));
        orgEmailTextField.setForeground(new Color(0,0,0));

        orgPhoneTextField = new JTextField();
        orgPhoneTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        orgPhoneTextField.setPreferredSize(new Dimension(orgPanel.getWidth()/2,30));
        orgPhoneTextField.setMinimumSize(orgPhoneTextField.getPreferredSize());
        orgPhoneTextField.setMaximumSize(orgPhoneTextField.getPreferredSize());
        orgPhoneTextField.setFont(new Font("Calibri",Font.PLAIN,18));
        orgPhoneTextField.setForeground(new Color(0,0,0));

        orgPasswordTextField = new JPasswordField();
        orgPasswordTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        orgPasswordTextField.setPreferredSize(new Dimension(orgPanel.getWidth()/2,30));
        orgPasswordTextField.setMinimumSize(orgPasswordTextField.getPreferredSize());
        orgPasswordTextField.setMaximumSize(orgPasswordTextField.getPreferredSize());
        orgPasswordTextField.setFont(new Font("Calibri",Font.PLAIN,18));
        orgPasswordTextField.setForeground(new Color(0,0,0));

        orgConfirmPasswordTextField = new JPasswordField();
        orgConfirmPasswordTextField.setAlignmentX(Component.RIGHT_ALIGNMENT);
        orgConfirmPasswordTextField.setPreferredSize(new Dimension(orgPanel.getWidth()/2,30));
        orgConfirmPasswordTextField.setMinimumSize(orgConfirmPasswordTextField.getPreferredSize());
        orgConfirmPasswordTextField.setMaximumSize(orgConfirmPasswordTextField.getPreferredSize());
        orgConfirmPasswordTextField.setFont(new Font("Calibri",Font.PLAIN,18));
        orgConfirmPasswordTextField.setForeground(new Color(0,0,0));

        orgBackButton = new JButton("Back");
        orgBackButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        orgBackButton.setPreferredSize(new Dimension(orgPanel.getWidth()/6,50));
        orgBackButton.setMinimumSize(orgBackButton.getPreferredSize());
        orgBackButton.setMaximumSize(orgBackButton.getPreferredSize());
        orgBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orgFrame.setVisible(false);
                SignUpPage s = new SignUpPage();
            }
        });

        orgEnterButton = new JButton("Enter");
        orgEnterButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        orgEnterButton.setPreferredSize(new Dimension(orgPanel.getWidth()/6,50));
        orgEnterButton.setMinimumSize(orgEnterButton.getPreferredSize());
        orgEnterButton.setMaximumSize(orgEnterButton.getPreferredSize());
        orgEnterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = "";
                for(int i=0;i<orgPasswordTextField.getPassword().length;i++)
                    password += orgPasswordTextField.getPassword()[i];
                String confirmPassword = "";
                for(int i=0;i<orgConfirmPasswordTextField.getPassword().length;i++)
                    confirmPassword += orgConfirmPasswordTextField.getPassword()[i];
                if(password.equals(confirmPassword))
                {
                    try
                    {
                        OrganizerSystemManager osm = new OrganizerSystemManager();
                        if(orgPhoneTextField.getText().length() == 10)
                        {
                            long phone = Long.parseLong(orgPhoneTextField.getText());

                            if(!osm.isValidUsername(orgUsernameTextField.getText()))
                            {
                                osm.storeOrgDetails(orgUsernameTextField.getText(),password,orgOrgNameTextField.getText(),orgEmailTextField.getText(),orgPhoneTextField.getText());
                                OrganizerMainPage omp = new OrganizerMainPage(orgUsernameTextField.getText());
                                orgFrame.setVisible(false);

                            }
                            else
                            {
                                JOptionPane.showMessageDialog(orgFrame,"Username has been taken","Error",JOptionPane.ERROR_MESSAGE);
                            }

                        }
                        else if(orgPhoneTextField.getText().length() == 0)
                        {

                            if(!osm.isValidUsername(orgUsernameTextField.getText()))
                            {

                                osm.storeOrgDetails(orgUsernameTextField.getText(),password,orgOrgNameTextField.getText(),orgEmailTextField.getText(),"");
                                OrganizerMainPage omp = new OrganizerMainPage(orgUsernameTextField.getText());
                                orgFrame.setVisible(false);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(orgFrame,"Username has been taken","Error",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(orgFrame,"Invalid Phone number","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch(NumberFormatException nfe)
                    {
                        JOptionPane.showMessageDialog(orgFrame,nfe.getStackTrace(),"Error",JOptionPane.ERROR_MESSAGE);
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(orgFrame,"Password and Confirm Password don't match","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        orgExitButton = new JButton("Exit");
        orgExitButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        orgExitButton.setPreferredSize(new Dimension(orgPanel.getWidth()/6,50));
        orgExitButton.setMinimumSize(orgExitButton.getPreferredSize());
        orgExitButton.setMaximumSize(orgExitButton.getPreferredSize());
        orgExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(orgFrame,"Are you sure you want to exit?","Confirm exit",JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });


        orgGL.setHorizontalGroup(orgGL.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(orgUsernameLabel)
                .addComponent(orgUsernameTextField)
                .addGroup(orgGL.createSequentialGroup()
                        .addComponent(orgPasswordLabel)
                        .addComponent(orgConfirmPasswordLabel)
                )
                .addGroup(orgGL.createSequentialGroup()
                        .addComponent(orgPasswordTextField)
                        .addComponent(orgConfirmPasswordTextField)
                )
                .addComponent(orgOrgNameLabel)
                .addComponent(orgOrgNameTextField)
                .addGroup(orgGL.createSequentialGroup()
                        .addComponent(orgEmailLabel)
                        .addComponent(orgPhoneLabel)
                )
                .addGroup(orgGL.createSequentialGroup()
                        .addComponent(orgEmailTextField)
                        .addComponent(orgPhoneTextField)
                )
                .addGroup(orgGL.createSequentialGroup()
                        .addComponent(orgBackButton)
                        .addComponent(orgEnterButton)
                        .addComponent(orgExitButton)
                )
        );

        orgGL.setVerticalGroup(orgGL.createSequentialGroup()
                .addComponent(orgUsernameLabel)
                .addComponent(orgUsernameTextField)
                .addGroup(orgGL.createParallelGroup()
                        .addComponent(orgPasswordLabel)
                        .addComponent(orgConfirmPasswordLabel)
                )
                .addGroup(orgGL.createParallelGroup()
                        .addComponent(orgPasswordTextField)
                        .addComponent(orgConfirmPasswordTextField)
                )
                .addComponent(orgOrgNameLabel)
                .addComponent(orgOrgNameTextField)
                .addGroup(orgGL.createParallelGroup()
                        .addComponent(orgEmailLabel)
                        .addComponent(orgPhoneLabel)
                )
                .addGroup(orgGL.createParallelGroup()
                        .addComponent(orgEmailTextField)
                        .addComponent(orgPhoneTextField)
                )
                .addGroup(orgGL.createParallelGroup()
                        .addComponent(orgBackButton)
                        .addComponent(orgEnterButton)
                        .addComponent(orgExitButton)
                )
        );

        orgFrame.getContentPane().add(orgPanel);
        orgFrame.getContentPane().add(orgBigPanel);
        orgFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        orgFrame.setVisible(true);
    }

}
