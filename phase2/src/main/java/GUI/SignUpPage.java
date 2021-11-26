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
    public static JFrame optionFrame;
    public static JPanel parPanel;
    public static JFrame parFrame;
    public static JPanel optionPanel;
    public static JButton orgButton;
    public static JButton parButton;
    public static JLabel optionLabel;
    public static JLabel optionSignInLabel;
    public static JButton optionSignInButton;
    public static JPanel bigParPanel;
    public static JLabel parUsernameLabel;
    public static JLabel parPasswordLabel;
    public static JLabel parConfirmPasswordLabel;
    public static JLabel parFirstNameLabel;
    public static JLabel parLastNameLabel;
    public static JLabel parDoBLabel;
    public static JFormattedTextField parDoBTextField;
    public static JTextField parUsernameTextField;
    public static JTextField parLastNameTextField;
    public static JTextField parFirstNameTextField;
    public static JPasswordField parPasswordField;
    public static JPasswordField parConfirmPasswordField;
    public static JLabel parEmailLabel;
    public static JTextField parEmailTextField;
    public static JLabel parPhoneLabel;
    public static JTextField parPhoneTextField;
    public static JButton parBackButton;
    public static JButton parEnterButton;
    public static JFrame orgFrame;
    public static JPanel orgBigPanel;
    public static JPanel orgPanel;
    public static JLabel orgUsernameLabel;
    public static JLabel orgPasswordLabel;
    public static JLabel orgConfirmPasswordLabel;
    public static JLabel orgOrgNameLabel;
    public static JLabel orgEmailLabel;
    public static JLabel orgPhoneLabel;
    public static JTextField orgUsernameTextField;
    public static JPasswordField orgPasswordTextField;
    public static JPasswordField orgConfirmPasswordTextField;
    public static JTextField orgOrgNameTextField;
    public static JTextField orgEmailTextField;
    public static JTextField orgPhoneTextField;
    public static JButton orgBackButton;
    public static JButton orgEnterButton;


    public SignUpPage()
    {
        initComponents();
    }
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
                parFrame.setVisible(true);
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
                orgFrame.setVisible(true);
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

        optionGL.setHorizontalGroup(optionGL.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(optionLabel)
                .addGroup(optionGL.createSequentialGroup()
                        .addComponent(parButton)
                        .addComponent(orgButton)
                )
                .addComponent(optionSignInLabel)
                .addComponent(optionSignInButton)
        );

        optionGL.setVerticalGroup(optionGL.createSequentialGroup()
                .addComponent(optionLabel)
                .addGroup(optionGL.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(parButton)
                        .addComponent(orgButton)
                )
                .addComponent(optionSignInLabel)
                .addComponent(optionSignInButton)
        );

        optionFrame.getContentPane().add(optionPanel);
        optionFrame.setVisible(true);

        parFrame = new JFrame("Participant Login");
        parFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        bigParPanel = new JPanel();
        bigParPanel.setBounds(0,0,parFrame.getWidth(),parFrame.getHeight());
        bigParPanel.setBackground(new Color(200,100,50));

        parPanel = new JPanel();
        GroupLayout parGL = new GroupLayout(parPanel);
        parPanel.setLayout(parGL);
        parPanel.setBounds(parFrame.getWidth()/8,parFrame.getHeight()/8,3*parFrame.getWidth()/4,7*parFrame.getHeight()/8);
        parPanel.setBackground(new Color(255,255,255));

        parGL.setAutoCreateContainerGaps(true);
        parGL.setAutoCreateGaps(true);

        parUsernameLabel = new JLabel("Username:",SwingConstants.LEFT);
        parUsernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        parUsernameLabel.setPreferredSize(new Dimension(parPanel.getWidth(),30));
        parUsernameLabel.setMinimumSize(parUsernameLabel.getPreferredSize());
        parUsernameLabel.setMaximumSize(parUsernameLabel.getPreferredSize());
        parUsernameLabel.setFont(new Font("Futura",Font.BOLD,18));
        parUsernameLabel.setForeground(new Color(0,0,0));

        parPasswordLabel = new JLabel("Password:",SwingConstants.LEFT);
        parPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        parPasswordLabel.setPreferredSize(new Dimension(parPanel.getWidth()/2,30));
        parPasswordLabel.setMinimumSize(parPasswordLabel.getPreferredSize());
        parPasswordLabel.setMaximumSize(parPasswordLabel.getPreferredSize());
        parPasswordLabel.setFont(new Font("Futura",Font.BOLD,18));
        parPasswordLabel.setForeground(new Color(0,0,0));

        parConfirmPasswordLabel = new JLabel("Confirm Password:",SwingConstants.LEFT);
        parConfirmPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        parConfirmPasswordLabel.setPreferredSize(new Dimension(parPanel.getWidth()/2,30));
        parConfirmPasswordLabel.setMinimumSize(parConfirmPasswordLabel.getPreferredSize());
        parConfirmPasswordLabel.setMaximumSize(parConfirmPasswordLabel.getPreferredSize());
        parConfirmPasswordLabel.setFont(new Font("Futura",Font.BOLD,18));
        parConfirmPasswordLabel.setForeground(new Color(0,0,0));

        parFirstNameLabel = new JLabel("First Name:",SwingConstants.LEFT);
        parFirstNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        parFirstNameLabel.setPreferredSize(new Dimension(parPanel.getWidth()/2,30));
        parFirstNameLabel.setMinimumSize(parFirstNameLabel.getPreferredSize());
        parFirstNameLabel.setMaximumSize(parFirstNameLabel.getPreferredSize());
        parFirstNameLabel.setFont(new Font("Futura",Font.BOLD,18));
        parFirstNameLabel.setForeground(new Color(0,0,0));

        parLastNameLabel = new JLabel("Last Name:",SwingConstants.LEFT);
        parLastNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        parLastNameLabel.setPreferredSize(new Dimension(parPanel.getWidth()/2,30));
        parLastNameLabel.setMinimumSize(parLastNameLabel.getPreferredSize());
        parLastNameLabel.setMaximumSize(parLastNameLabel.getPreferredSize());
        parLastNameLabel.setFont(new Font("Futura",Font.BOLD,18));
        parLastNameLabel.setForeground(new Color(0,0,0));

        parEmailLabel = new JLabel("Email:",SwingConstants.LEFT);
        parEmailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        parEmailLabel.setPreferredSize(new Dimension(parPanel.getWidth()/2,30));
        parEmailLabel.setMinimumSize(parEmailLabel.getPreferredSize());
        parEmailLabel.setMaximumSize(parEmailLabel.getPreferredSize());
        parEmailLabel.setFont(new Font("Futura",Font.BOLD,18));
        parEmailLabel.setForeground(new Color(0,0,0));

        parPhoneLabel = new JLabel("Phone:",SwingConstants.LEFT);
        parPhoneLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        parPhoneLabel.setPreferredSize(new Dimension(parPanel.getWidth()/2,30));
        parPhoneLabel.setMinimumSize(parPhoneLabel.getPreferredSize());
        parPhoneLabel.setMaximumSize(parPhoneLabel.getPreferredSize());
        parPhoneLabel.setFont(new Font("Futura",Font.BOLD,18));
        parPhoneLabel.setForeground(new Color(0,0,0));

        parDoBLabel = new JLabel("Date of Birth:",SwingConstants.LEFT);
        parDoBLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        parDoBLabel.setPreferredSize(new Dimension(parPanel.getWidth()/2,30));
        parDoBLabel.setMinimumSize(parDoBLabel.getPreferredSize());
        parDoBLabel.setMaximumSize(parDoBLabel.getPreferredSize());
        parDoBLabel.setFont(new Font("Futura",Font.BOLD,18));
        parDoBLabel.setForeground(new Color(0,0,0));

        parUsernameTextField = new JTextField();
        parUsernameTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        parUsernameTextField.setPreferredSize(new Dimension(parPanel.getWidth(),30));
        parUsernameTextField.setMinimumSize(parUsernameTextField.getPreferredSize());
        parUsernameTextField.setMaximumSize(parUsernameTextField.getPreferredSize());
        parUsernameTextField.setFont(new Font("Calibri",Font.PLAIN,18));
        parUsernameTextField.setForeground(new Color(0,0,0));

        parFirstNameTextField = new JTextField();
        parFirstNameTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        parFirstNameTextField.setPreferredSize(new Dimension(parPanel.getWidth()/2,30));
        parFirstNameTextField.setMinimumSize(parFirstNameTextField.getPreferredSize());
        parFirstNameTextField.setMaximumSize(parFirstNameTextField.getPreferredSize());
        parFirstNameTextField.setFont(new Font("Calibri",Font.PLAIN,18));
        parFirstNameTextField.setForeground(new Color(0,0,0));

        parLastNameTextField = new JTextField();
        parLastNameTextField.setAlignmentX(Component.RIGHT_ALIGNMENT);
        parLastNameTextField.setPreferredSize(new Dimension(parPanel.getWidth()/2,30));
        parLastNameTextField.setMinimumSize(parLastNameTextField.getPreferredSize());
        parLastNameTextField.setMaximumSize(parLastNameTextField.getPreferredSize());
        parLastNameTextField.setFont(new Font("Calibri",Font.PLAIN,18));
        parLastNameTextField.setForeground(new Color(0,0,0));

        parEmailTextField = new JTextField();
        parEmailTextField.setAlignmentX(Component.RIGHT_ALIGNMENT);
        parEmailTextField.setPreferredSize(new Dimension(parPanel.getWidth()/2,30));
        parEmailTextField.setMinimumSize(parEmailTextField.getPreferredSize());
        parEmailTextField.setMaximumSize(parEmailTextField.getPreferredSize());
        parEmailTextField.setFont(new Font("Calibri",Font.PLAIN,18));
        parEmailTextField.setForeground(new Color(0,0,0));

        parPhoneTextField = new JTextField();
        parPhoneTextField.setAlignmentX(Component.RIGHT_ALIGNMENT);
        parPhoneTextField.setPreferredSize(new Dimension(parPanel.getWidth()/2,30));
        parPhoneTextField.setMinimumSize(parPhoneTextField.getPreferredSize());
        parPhoneTextField.setMaximumSize(parPhoneTextField.getPreferredSize());
        parPhoneTextField.setFont(new Font("Calibri",Font.PLAIN,18));
        parPhoneTextField.setForeground(new Color(0,0,0));

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        parDoBTextField = new JFormattedTextField(df);
        parDoBTextField.setValue(new Date());
        parDoBTextField.setColumns(10);
        parDoBTextField.setAlignmentX(Component.RIGHT_ALIGNMENT);
        parDoBTextField.setPreferredSize(new Dimension(parPanel.getWidth()/2,30));
        parDoBTextField.setMinimumSize(parDoBTextField.getPreferredSize());
        parDoBTextField.setMaximumSize(parDoBTextField.getPreferredSize());
        parDoBTextField.setFont(new Font("Calibri",Font.PLAIN,18));
        parDoBTextField.setForeground(new Color(0,0,0));

        parPasswordField = new JPasswordField();
        parPasswordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        parPasswordField.setPreferredSize(new Dimension(parPanel.getWidth()/2,30));
        parPasswordField.setMinimumSize(parPasswordField.getPreferredSize());
        parPasswordField.setMaximumSize(parPasswordField.getPreferredSize());
        parPasswordField.setFont(new Font("Calibri",Font.PLAIN,18));
        parPasswordField.setForeground(new Color(0,0,0));

        parConfirmPasswordField = new JPasswordField();
        parConfirmPasswordField.setAlignmentX(Component.RIGHT_ALIGNMENT);
        parConfirmPasswordField.setPreferredSize(new Dimension(parPanel.getWidth()/2,30));
        parConfirmPasswordField.setMinimumSize(parConfirmPasswordField.getPreferredSize());
        parConfirmPasswordField.setMaximumSize(parConfirmPasswordField.getPreferredSize());
        parConfirmPasswordField.setFont(new Font("Calibri",Font.PLAIN,18));
        parConfirmPasswordField.setForeground(new Color(0,0,0));

        parBackButton = new JButton("Back");
        parBackButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        parBackButton.setPreferredSize(new Dimension(parPanel.getWidth()/6,50));
        parBackButton.setMinimumSize(parBackButton.getPreferredSize());
        parBackButton.setMaximumSize(parBackButton.getPreferredSize());
        parBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parUsernameTextField.setText("");
                parPasswordField.setText("");
                parConfirmPasswordField.setText("");
                parFirstNameTextField.setText("");
                parLastNameTextField.setText("");
                parEmailTextField.setText("");
                parPhoneTextField.setText("");
                parFrame.setVisible(false);
                optionFrame.setVisible(true);
            }
        });

        parEnterButton = new JButton("Enter");
        parEnterButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        parEnterButton.setPreferredSize(new Dimension(parPanel.getWidth()/6,50));
        parEnterButton.setMinimumSize(parEnterButton.getPreferredSize());
        parEnterButton.setMaximumSize(parEnterButton.getPreferredSize());
        parEnterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = "";
                for(int i=0;i<parPasswordField.getPassword().length;i++)
                    password += parPasswordField.getPassword()[i];
                String confirmPassword = "";
                for(int i=0;i<parConfirmPasswordField.getPassword().length;i++)
                    confirmPassword += parConfirmPasswordField.getPassword()[i];
                if(password.equals(confirmPassword))
                {
                    try
                    {
                        ParticipantSystemManager psm = new ParticipantSystemManager();
                        if(parPhoneTextField.getText().length() == 10)
                        {
                            int phone = Integer.parseInt(parPhoneTextField.getText());
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate ld = LocalDate.parse(parDoBTextField.getText(),dtf);
                            if(!psm.isValidUsername(parUsernameTextField.getText()))
                            {
                                psm.storePartDetails(parUsernameTextField.getText(),password,parFirstNameTextField.getText(),parLastNameTextField.getText(),parEmailTextField.getText(),parPhoneTextField.getText(),ld);
                                ParticipantMainPage pmp = new ParticipantMainPage(parUsernameTextField.getText());
                                parFrame.setVisible(false);
                            }

                            else
                            {
                                JOptionPane.showMessageDialog(parFrame,"Username has been taken","Error",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else if(parPhoneTextField.getText().length() == 0)
                        {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate ld = LocalDate.parse(parDoBTextField.getText(),dtf);
                            if(!psm.isValidUsername(parUsernameTextField.getText()))
                            {
                                psm.storePartDetails(parUsernameTextField.getText(),password,parFirstNameTextField.getText(),parLastNameTextField.getText(),parEmailTextField.getText(),"",ld);
                                ParticipantMainPage pmp = new ParticipantMainPage(parUsernameTextField.getText());
                                parFrame.setVisible(false);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(parFrame,"Username has been taken","Error",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(parFrame,"Invalid Phone number","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch(NumberFormatException nfe)
                    {
                        JOptionPane.showMessageDialog(parFrame,"Invalid Phone number","Error",JOptionPane.ERROR_MESSAGE);
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(orgFrame,"Password and Confirm Password don't match","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        parGL.setHorizontalGroup(parGL.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(parUsernameLabel)
                .addComponent(parUsernameTextField)
                .addGroup(parGL.createSequentialGroup()
                        .addComponent(parPasswordLabel)
                        .addComponent(parConfirmPasswordLabel)
                )
                .addGroup(parGL.createSequentialGroup()
                        .addComponent(parPasswordField)
                        .addComponent(parConfirmPasswordField)
                )
                .addGroup(parGL.createSequentialGroup()
                        .addComponent(parFirstNameLabel)
                        .addComponent(parLastNameLabel)
                )
                .addGroup(parGL.createSequentialGroup()
                        .addComponent(parFirstNameTextField)
                        .addComponent(parLastNameTextField)
                )
                .addGroup(parGL.createSequentialGroup()
                        .addComponent(parEmailLabel)
                        .addComponent(parPhoneLabel)
                )
                .addGroup(parGL.createSequentialGroup()
                        .addComponent(parEmailTextField)
                        .addComponent(parPhoneTextField)
                )
                .addComponent(parDoBLabel)
                .addComponent(parDoBTextField)
                .addGroup(parGL.createSequentialGroup()
                        .addComponent(parBackButton)
                        .addComponent(parEnterButton)
                )
        );

        parGL.setVerticalGroup(parGL.createSequentialGroup()
                .addComponent(parUsernameLabel)
                .addComponent(parUsernameTextField)
                .addGroup(parGL.createParallelGroup()
                        .addComponent(parPasswordLabel)
                        .addComponent(parConfirmPasswordLabel)
                )
                .addGroup(parGL.createParallelGroup()
                        .addComponent(parPasswordField)
                        .addComponent(parConfirmPasswordField)
                )
                .addGroup(parGL.createParallelGroup()
                        .addComponent(parFirstNameLabel)
                        .addComponent(parLastNameLabel)
                )
                .addGroup(parGL.createParallelGroup()
                        .addComponent(parFirstNameTextField)
                        .addComponent(parLastNameTextField)
                )
                .addGroup(parGL.createParallelGroup()
                        .addComponent(parEmailLabel)
                        .addComponent(parPhoneLabel)
                )
                .addGroup(parGL.createParallelGroup()
                        .addComponent(parEmailTextField)
                        .addComponent(parPhoneTextField)
                )
                .addComponent(parDoBLabel)
                .addComponent(parDoBTextField)
                .addGroup(parGL.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(parBackButton)
                        .addComponent(parEnterButton)
                )
        );
        parFrame.getContentPane().add(parPanel);
        parFrame.getContentPane().add(bigParPanel);
        parFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        orgFrame = new JFrame("Organizer Sign up");
        orgFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orgFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        orgBigPanel = new JPanel();
        orgBigPanel.setBounds(0,0,parFrame.getWidth(),parFrame.getHeight());
        orgBigPanel.setBackground(new Color(200,100,50));

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
        orgBackButton.setPreferredSize(new Dimension(parPanel.getWidth()/6,50));
        orgBackButton.setMinimumSize(orgBackButton.getPreferredSize());
        orgBackButton.setMaximumSize(orgBackButton.getPreferredSize());
        orgBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orgUsernameTextField.setText("");
                orgPasswordTextField.setText("");
                orgConfirmPasswordTextField.setText("");
                orgEmailTextField.setText("");
                orgPhoneTextField.setText("");
                orgFrame.setVisible(false);
                optionFrame.setVisible(true);
            }
        });

        orgEnterButton = new JButton("Enter");
        orgEnterButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        orgEnterButton.setPreferredSize(new Dimension(parPanel.getWidth()/6,50));
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
                                //System.out.println("Hello");

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
                )
        );

        orgFrame.getContentPane().add(orgPanel);
        orgFrame.getContentPane().add(orgBigPanel);
        orgFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

}
