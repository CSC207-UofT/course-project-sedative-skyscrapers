package main.java.GUI;

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

public class ParticipantSignUpPage {
    public JPanel parPanel;
    public JFrame parFrame;
    public JPanel bigParPanel;
    public JLabel parUsernameLabel;
    public JLabel parPasswordLabel;
    public JLabel parConfirmPasswordLabel;
    public JLabel parFirstNameLabel;
    public JLabel parLastNameLabel;
    public JLabel parDoBLabel;
    public JFormattedTextField parDoBTextField;
    public JTextField parUsernameTextField;
    public JTextField parLastNameTextField;
    public JTextField parFirstNameTextField;
    public JPasswordField parPasswordField;
    public JPasswordField parConfirmPasswordField;
    public JLabel parEmailLabel;
    public JTextField parEmailTextField;
    public JLabel parPhoneLabel;
    public JTextField parPhoneTextField;
    public JButton parBackButton;
    public JButton parEnterButton;
    public JButton parExitButton;

    public ParticipantSignUpPage()
    {
        initComponents();
    }
    public void initComponents()
    {
        parFrame = new JFrame("Participant Login");
        parFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        bigParPanel = new JPanel();
        bigParPanel.setBounds(0,0,parFrame.getWidth(),parFrame.getHeight());
        bigParPanel.setBackground(new Color(0,240,100,100));

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
                SignUpPage sup = new SignUpPage();
                parFrame.setVisible(false);
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
                            long phone = Long.parseLong(parPhoneTextField.getText());
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
                            JOptionPane.showMessageDialog(parFrame,"Invalid Phone number","Error1",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch(NumberFormatException nfe)
                    {
                        JOptionPane.showMessageDialog(parFrame,"Invalid Phone number","Error2",JOptionPane.ERROR_MESSAGE);
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(parFrame,"Password and Confirm Password don't match","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        parExitButton = new JButton("Exit");
        parExitButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        parExitButton.setPreferredSize(new Dimension(parPanel.getWidth()/6,50));
        parExitButton.setMinimumSize(parExitButton.getPreferredSize());
        parExitButton.setMaximumSize(parExitButton.getPreferredSize());
        parExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(parFrame,"Are you sure you want to exit?","Confirm exit",JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION)
                    System.exit(0);
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
                        .addComponent(parExitButton)
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
                        .addComponent(parExitButton)
                )
        );
        parFrame.getContentPane().add(parPanel);
        parFrame.getContentPane().add(bigParPanel);
        parFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        parFrame.setVisible(true);
    }
}
