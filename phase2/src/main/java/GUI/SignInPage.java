package main.java.GUI;

import main.java.SystemManagers.OrganizerSystemManager;
import main.java.SystemManagers.ParticipantSystemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class SignInPage extends JFrame {
    private JFrame jFrame1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JLabel signInLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JToggleButton showPassword;
    private JButton signUpButton;
    private JButton enterButton;
    private JComboBox comboBox;
    private JLabel comboBoxLabel;
    private JButton exitButton;

    /**
     * Calls initComponents()
     */

    public SignInPage()
    {
        initComponents();
    }

    /**
     * initComponents initializes and sets important attributes of all the components of the GUI
     */

    private void initComponents()
    {
        jFrame1 = new JFrame("Raffle");
        jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame1.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        jPanel1 = new JPanel();

        jPanel1.setBounds(0,0,jFrame1.getWidth(),jFrame1.getHeight());
        jPanel1.setBackground(new Color(0,240,100,100));

        jPanel2 = new JPanel();
        GroupLayout gl = new GroupLayout(jPanel2);
        jPanel2.setLayout(gl);
        jPanel2.setBounds(jFrame1.getWidth()/8,jFrame1.getHeight()/6,3*jFrame1.getWidth()/4,5*jFrame1.getHeight()/6);
        jPanel2.setBackground(new Color(160,160,160,150));

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        signInLabel = new JLabel("Sign In",JLabel.CENTER);
        signInLabel.setPreferredSize(new Dimension(jPanel2.getWidth(),100));
        signInLabel.setMinimumSize(signInLabel.getPreferredSize());
        signInLabel.setMaximumSize(signInLabel.getPreferredSize());
        signInLabel.setFont(new Font("Apple Chancery",Font.BOLD,50));
        signInLabel.setForeground(new Color(0,0,0));

        usernameLabel = new JLabel("Username:",SwingConstants.LEFT);
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameLabel.setPreferredSize(new Dimension(jPanel2.getWidth(),60));
        usernameLabel.setMinimumSize(usernameLabel.getPreferredSize());
        usernameLabel.setMaximumSize(usernameLabel.getPreferredSize());
        usernameLabel.setFont(new Font("Chalkboard",Font.BOLD,22));
        usernameLabel.setForeground(new Color(0,0,0));

        passwordLabel = new JLabel("Password:",SwingConstants.LEFT);
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordLabel.setPreferredSize(new Dimension(jPanel2.getWidth(),60));
        passwordLabel.setMinimumSize(passwordLabel.getPreferredSize());
        passwordLabel.setMaximumSize(passwordLabel.getPreferredSize());
        passwordLabel.setFont(new Font("Chalkboard",Font.BOLD,28));
        passwordLabel.setForeground(new Color(0,0,0));

        usernameTextField = new JTextField("Enter Username");
        usernameTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameTextField.setPreferredSize(new Dimension(jPanel2.getWidth()-20,50));
        usernameTextField.setMinimumSize(usernameTextField.getPreferredSize());
        usernameTextField.setMaximumSize(usernameTextField.getPreferredSize());
        usernameTextField.setFont(new Font("TimesRoman",Font.ITALIC,14));
        usernameTextField.setForeground(new Color(0,0,0,80));
        usernameTextField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                if (usernameTextField.getText().equals("Enter Username"))
                {
                    usernameTextField.setText("");
                    usernameTextField.setFont(new Font("Chalkboard",Font.PLAIN,18));
                    usernameTextField.setForeground(new Color(0,0,0));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (usernameTextField.getText().equals("")) {
                    usernameTextField.setText("Enter Username");
                    usernameTextField.setFont(new Font("TimesRoman",Font.ITALIC,14));
                    usernameTextField.setForeground(new Color(0,0,0,80));
                }
            }
        });
        showPassword = new JToggleButton("Show password");
        showPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        showPassword.setPreferredSize(new Dimension(jPanel2.getWidth()/8,50));
        showPassword.setMinimumSize(showPassword.getPreferredSize());
        showPassword.setMaximumSize(showPassword.getPreferredSize());

        passwordField = new JPasswordField("Enter Password");
        passwordField.setEchoChar((char)0);
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordField.setPreferredSize(new Dimension((7*jPanel2.getWidth()/8)-20,50));
        passwordField.setMinimumSize(passwordField.getPreferredSize());
        passwordField.setMaximumSize(passwordField.getPreferredSize());
        passwordField.setFont(new Font("TimesRoman",Font.ITALIC,14));
        passwordField.setForeground(new Color(0,0,0,80));

        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected())
                    passwordField.setEchoChar((char)0);
                else
                    passwordField.setEchoChar('*');

            }
        });

        passwordField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {

                if (Arrays.toString(passwordField.getPassword()).equals("[E, n, t, e, r,  , P, a, s, s, w, o, r, d]"))
                {
                    if(!showPassword.isSelected())
                        passwordField.setEchoChar('*');
                    passwordField.setText("");
                    passwordField.setForeground(new Color(0,0,0));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

                if (Arrays.toString(passwordField.getPassword()) == "[]")
                {
                    passwordField.setEchoChar((char)0);
                    passwordField.setText("Enter Password");
                    passwordField.setFont(new Font("TimesRoman",Font.ITALIC,14));
                    passwordField.setForeground(new Color(0,0,0,80));
                }

            }
        });

        signUpButton = new JButton("Don't have an account?");
        signUpButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        signUpButton.setPreferredSize(new Dimension(jPanel2.getWidth()/6,50));
        signUpButton.setMinimumSize(signUpButton.getPreferredSize());
        signUpButton.setMaximumSize(signUpButton.getPreferredSize());
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpPage sup = new SignUpPage();
                jFrame1.setVisible(false);
            }
        });

        String[] arr = {"Participant","Organizer"};
        comboBox = new JComboBox(arr);
        comboBox.setPreferredSize(new Dimension((7*jPanel2.getWidth()/8)-30,40));
        comboBox.setMinimumSize(comboBox.getPreferredSize());
        comboBox.setMaximumSize(comboBox.getPreferredSize());
        comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        comboBoxLabel = new JLabel("Role: ");
        comboBoxLabel.setPreferredSize(new Dimension(jPanel2.getWidth()/8,40));
        comboBoxLabel.setMinimumSize(comboBoxLabel.getPreferredSize());
        comboBoxLabel.setMaximumSize(comboBoxLabel.getPreferredSize());
        comboBoxLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboBoxLabel.setFont(new Font("Chalkboard",Font.BOLD,28));
        comboBoxLabel.setForeground(new Color(0,0,0));

        enterButton = new JButton("Enter");
        enterButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        enterButton.setPreferredSize(new Dimension(jPanel2.getWidth()/6,50));
        enterButton.setMinimumSize(enterButton.getPreferredSize());
        enterButton.setMaximumSize(enterButton.getPreferredSize());
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = "";
                char[] ch = passwordField.getPassword();
                for(int i = 0;i<ch.length;i++)
                    password+=ch[i];
                if(comboBox.getSelectedIndex() == 0)
                {
                    ParticipantSystemManager psm = new ParticipantSystemManager();
                    if(psm.checkLoginMatch(usernameTextField.getText(),password))
                    {
                        ParticipantMainPage pmp = new ParticipantMainPage(usernameTextField.getText());
                        jFrame1.setVisible(false);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(jFrame1,"Wrong username and/or password","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    OrganizerSystemManager osm = new OrganizerSystemManager();
                    if(osm.checkLoginMatch(usernameTextField.getText(),password))
                    {
                        OrganizerMainPage omp = new OrganizerMainPage(usernameTextField.getText());
                        jFrame1.setVisible(false);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(jFrame1,"Wrong username and/or password","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        exitButton.setPreferredSize(new Dimension(jPanel2.getWidth()/6,50));
        exitButton.setMinimumSize(exitButton.getPreferredSize());
        exitButton.setMaximumSize(exitButton.getPreferredSize());
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(jFrame1,"Are you sure you want to exit?","Confirm exit",JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });

        gl.setHorizontalGroup(
                gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(signInLabel)
                        .addComponent(usernameLabel)
                        .addComponent(usernameTextField)
                        .addComponent(passwordLabel)
                        .addGroup(gl.createSequentialGroup()
                                .addComponent(passwordField)
                                .addComponent(showPassword)
                        )
                        .addGroup(gl.createSequentialGroup()
                                .addComponent(comboBoxLabel)
                                .addComponent(comboBox)
                        )
                        .addGroup(gl.createSequentialGroup()
                                .addComponent(exitButton)
                                .addGap(100)
                                .addComponent(signUpButton)
                                .addGap(100)
                                .addComponent(enterButton)
                        )

        );

        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addComponent(signInLabel)
                        .addComponent(usernameLabel)
                        .addComponent(usernameTextField)
                        .addGap(20)
                        .addComponent(passwordLabel)
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(passwordField)
                                .addComponent(showPassword)

                        )
                        .addGap(50)
                        .addGroup(gl.createParallelGroup()
                                .addComponent(comboBoxLabel)
                                .addComponent(comboBox)
                        )
                        .addGap(50)
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(exitButton)
                                .addComponent(signUpButton)
                                .addComponent(enterButton)
                        )
        );

        jFrame1.getContentPane().add(jPanel2);
        jFrame1.getContentPane().add(jPanel1);
        jFrame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame1.setVisible(true);
    }
}
