package main.java.GUI;

import main.java.SystemManagers.OrganizerSystemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

//Create raffles
public class OrganizerCreateRafflePage extends JFrame {
    public JFrame frame;
    public JPanel bigPanel;
    public JPanel smallPanel;
    public JScrollPane scrollPane;
    public JLabel raffleNameLabel;
    public JTextField raffleNameField;
    public  JLabel raffleNumWinnersLabel;
    public JTextField raffleNumWinnersField;
    public JLabel raffleEndDateLabel;
    public JFormattedTextField raffleEndDateField;
    public ArrayList<JLabel> taskNameLabel;
    public ArrayList<JLabel> taskDescriptionLabel;
    public ArrayList<JLabel> taskLinkLabel;
    public ArrayList<JTextField> taskNameField;
    public ArrayList<JTextArea> taskDescriptionField;
    public ArrayList<JTextField> taskLinkField;
    public JButton addTask;
    public JButton deleteTask;
    public JComboBox<Integer> deleteTaskField;
    public JButton createRaffle;
    public String username;
    public JTextArea raffleRules;
    public JLabel raffleRulesLabel;
    public JButton backButton;
    public JButton exitButton;

    /**
     * Initializes username and calls initComponents()
     * @param username - username of the current organizer
     */

    public OrganizerCreateRafflePage(String username)
    {
        this.username = username;
        initcomponents();
    }

    /**
     * initComponents initializes and sets important attributes of all the components of the GUI
     */
    public void initcomponents()
    {
        frame = new JFrame("Create Raffle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        bigPanel = new JPanel();
        bigPanel.setBounds(0,0,frame.getWidth(),frame.getHeight());
        bigPanel.setBackground(new Color(0,240,100,100));

        smallPanel = new JPanel();
        GroupLayout gl = new GroupLayout(smallPanel);
        smallPanel.setLayout(gl);
        smallPanel.setBackground(new Color(255,255,255));

        scrollPane = new JScrollPane(smallPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0,frame.getHeight()/10,frame.getWidth(),(6*frame.getHeight()/8));
        scrollPane.setBackground(new Color(255,255,255));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        raffleNameLabel = new JLabel("Raffle Name:",SwingConstants.LEFT);
        raffleNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        raffleNameLabel.setPreferredSize(new Dimension(scrollPane.getWidth(),30));
        raffleNameLabel.setMinimumSize(raffleNameLabel.getPreferredSize());
        raffleNameLabel.setMaximumSize(raffleNameLabel.getPreferredSize());
        raffleNameLabel.setFont(new Font("Chalkboard",Font.PLAIN,18));
        raffleNameLabel.setForeground(new Color(0,0,0));

        raffleNameField = new JTextField("");
        raffleNameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        raffleNameField.setPreferredSize(new Dimension(scrollPane.getWidth(),40));
        raffleNameField.setMinimumSize(raffleNameField.getPreferredSize());
        raffleNameField.setMaximumSize(raffleNameField.getPreferredSize());
        raffleNameField.setFont(new Font("Chalkboard",Font.PLAIN,18));
        raffleNameField.setForeground(new Color(0,0,0));

        OrganizerSystemManager osm = new OrganizerSystemManager();

        raffleRulesLabel = new JLabel("Raffle Rules:",SwingConstants.LEFT);
        raffleRulesLabel.setPreferredSize(new Dimension(scrollPane.getWidth()/2,30));
        raffleRulesLabel.setMinimumSize(raffleRulesLabel.getPreferredSize());
        raffleRulesLabel.setMaximumSize(raffleRulesLabel.getPreferredSize());
        raffleRulesLabel.setFont(new Font("Chalkboard",Font.PLAIN,18));
        raffleRulesLabel.setForeground(new Color(0,0,0));

        raffleRules = new JTextArea();
        raffleRules.setEditable(true);
        raffleRules.setLineWrap(true);
        raffleRules.setPreferredSize(new Dimension(3*frame.getWidth()/4,100));
        raffleRules.setMinimumSize(raffleRules.getPreferredSize());
        raffleRules.setMaximumSize(raffleRules.getPreferredSize());
        raffleRules.setFont(new Font("Chalkboard",Font.PLAIN,20));

        raffleNumWinnersLabel = new JLabel("Number of Winners:",SwingConstants.LEFT);
        raffleNumWinnersLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        raffleNumWinnersLabel.setPreferredSize(new Dimension(scrollPane.getWidth()/2,30));
        raffleNumWinnersLabel.setMinimumSize(raffleNumWinnersLabel.getPreferredSize());
        raffleNumWinnersLabel.setMaximumSize(raffleNumWinnersLabel.getPreferredSize());
        raffleNumWinnersLabel.setFont(new Font("Chalkboard",Font.PLAIN,18));
        raffleNumWinnersLabel.setForeground(new Color(0,0,0));

        raffleNumWinnersField = new JTextField("1");
        raffleNumWinnersField.setAlignmentX(Component.LEFT_ALIGNMENT);
        raffleNumWinnersField.setPreferredSize(new Dimension(scrollPane.getWidth()/2,40));
        raffleNumWinnersField.setMinimumSize(raffleNumWinnersField.getPreferredSize());
        raffleNumWinnersField.setMaximumSize(raffleNumWinnersField.getPreferredSize());
        raffleNumWinnersField.setFont(new Font("Chalkboard",Font.PLAIN,18));
        raffleNumWinnersField.setForeground(new Color(0,0,0));

        raffleEndDateLabel = new JLabel("End Date:",SwingConstants.LEFT);
        raffleEndDateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        raffleEndDateLabel.setPreferredSize(new Dimension(scrollPane.getWidth()/2,30));
        raffleEndDateLabel.setMinimumSize(raffleEndDateLabel.getPreferredSize());
        raffleEndDateLabel.setMaximumSize(raffleEndDateLabel.getPreferredSize());
        raffleEndDateLabel.setFont(new Font("Chalkboard",Font.PLAIN,18));
        raffleEndDateLabel.setForeground(new Color(0,0,0));

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        raffleEndDateField = new JFormattedTextField(df);
        raffleEndDateField.setValue(new Date());
        raffleEndDateField.setColumns(10);
        raffleEndDateField.setAlignmentX(Component.LEFT_ALIGNMENT);
        raffleEndDateField.setPreferredSize(new Dimension(scrollPane.getWidth()/2,40));
        raffleEndDateField.setMinimumSize(raffleEndDateField.getPreferredSize());
        raffleEndDateField.setMaximumSize(raffleEndDateField.getPreferredSize());
        raffleEndDateField.setFont(new Font("Chalkboard",Font.PLAIN,18));
        raffleEndDateField.setForeground(new Color(0,0,0));

        taskNameLabel = new ArrayList<JLabel>(0);
        taskDescriptionLabel = new ArrayList<JLabel>(0);
        taskLinkLabel = new ArrayList<JLabel>(0);
        taskNameField = new ArrayList<JTextField>(0);
        taskDescriptionField = new ArrayList<JTextArea>(0);
        taskLinkField = new ArrayList<JTextField>(0);

        JLabel tNameLabel = new JLabel("Name for Task 1");
        tNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        tNameLabel.setPreferredSize(new Dimension(scrollPane.getWidth(),30));
        tNameLabel.setMinimumSize(tNameLabel.getPreferredSize());
        tNameLabel.setMaximumSize(tNameLabel.getPreferredSize());
        tNameLabel.setFont(new Font("Chalkboard",Font.PLAIN,18));
        tNameLabel.setForeground(new Color(0,0,0));
        taskNameLabel.add(tNameLabel);

        JLabel tDescriptionLabel = new JLabel("Description for Task 1");
        tDescriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        tDescriptionLabel.setPreferredSize(new Dimension(scrollPane.getWidth(),30));
        tDescriptionLabel.setMinimumSize(tDescriptionLabel.getPreferredSize());
        tDescriptionLabel.setMaximumSize(tDescriptionLabel.getPreferredSize());
        tDescriptionLabel.setFont(new Font("Chalkboard",Font.PLAIN,18));
        tDescriptionLabel.setForeground(new Color(0,0,0));
        taskDescriptionLabel.add(tDescriptionLabel);

        JLabel tLinkLabel = new JLabel("Link for Task 1");
        tLinkLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        tLinkLabel.setPreferredSize(new Dimension(scrollPane.getWidth(),30));
        tLinkLabel.setMinimumSize(tLinkLabel.getPreferredSize());
        tLinkLabel.setMaximumSize(tLinkLabel.getPreferredSize());
        tLinkLabel.setFont(new Font("Chalkboard",Font.PLAIN,18));
        tLinkLabel.setForeground(new Color(0,0,0));
        taskLinkLabel.add(tLinkLabel);

        JTextField tNameField = new JTextField();
        tNameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        tNameField.setPreferredSize(new Dimension(scrollPane.getWidth(),40));
        tNameField.setMinimumSize(tNameField.getPreferredSize());
        tNameField.setMaximumSize(tNameField.getPreferredSize());
        tNameField.setFont(new Font("Chalkboard",Font.PLAIN,18));
        tNameField.setForeground(new Color(0,0,0));
        taskNameField.add(tNameField);

        JTextField tLinkField = new JTextField();
        tLinkField.setAlignmentX(Component.LEFT_ALIGNMENT);
        tLinkField.setPreferredSize(new Dimension(scrollPane.getWidth(),40));
        tLinkField.setMinimumSize(tLinkField.getPreferredSize());
        tLinkField.setMaximumSize(tLinkField.getPreferredSize());
        tLinkField.setFont(new Font("Chalkboard",Font.PLAIN,18));
        tLinkField.setForeground(new Color(0,0,0));
        taskLinkField.add(tLinkField);

        JTextArea tArea = new JTextArea();
        tArea.setLineWrap(true);
        tArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        tArea.setPreferredSize(new Dimension(scrollPane.getWidth(),40));
        tArea.setMinimumSize(tArea.getPreferredSize());
        tArea.setMaximumSize(tArea.getPreferredSize());
        tArea.setFont(new Font("Chalkboard",Font.PLAIN,18));
        tArea.setForeground(new Color(0,0,0));
        taskDescriptionField.add(tArea);

        createRaffle = new JButton("Create Raffle");
        createRaffle.setAlignmentX(Component.LEFT_ALIGNMENT);
        createRaffle.setPreferredSize(new Dimension(scrollPane.getWidth()/6,40));
        createRaffle.setMinimumSize(createRaffle.getPreferredSize());
        createRaffle.setMaximumSize(createRaffle.getPreferredSize());
        createRaffle.setFont(new Font("Chalkboard",Font.PLAIN,14));
        createRaffle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(frame,"Are you sure you want to create the raffle?","Confirm Message",JOptionPane.OK_CANCEL_OPTION);
                if(option == JOptionPane.YES_OPTION)
                {
                    String[][] taskInfo = new String[taskNameField.size()][3];
                    for(int i=0;i<taskNameField.size();i++)
                    {
                        taskInfo[i][0] = taskNameField.get(i).getText();
                        taskInfo[i][1] = taskDescriptionField.get(i).getText();
                        taskInfo[i][2] = taskLinkField.get(i).getText();
                    }
                    OrganizerSystemManager osm = new OrganizerSystemManager();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate ld = LocalDate.parse(raffleEndDateField.getText(),dtf);
                    if(ld.isAfter(LocalDate.now())){
                        try {
                            osm.raffleCreator(raffleNameField.getText(), raffleRules.getText(), Integer.parseInt(raffleNumWinnersField.getText()), ld, username, taskInfo);
                            OrganizerMainPage o = new OrganizerMainPage(username);
                            frame.setVisible(false);
                        }catch(Exception io)
                        {
                            JOptionPane.showMessageDialog(frame,io.getStackTrace(),"Error",JOptionPane.ERROR_MESSAGE);
                        }}
                    else
                        JOptionPane.showMessageDialog(frame,"End date cannot be before today's date","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addTask = new JButton("Add Task");
        addTask.setAlignmentX(Component.LEFT_ALIGNMENT);
        addTask.setPreferredSize(new Dimension(scrollPane.getWidth()/6,40));
        addTask.setMinimumSize(addTask.getPreferredSize());
        addTask.setMaximumSize(addTask.getPreferredSize());

        exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        exitButton.setPreferredSize(new Dimension(scrollPane.getWidth()/6,40));
        exitButton.setMinimumSize(exitButton.getPreferredSize());
        exitButton.setMaximumSize(exitButton.getPreferredSize());
        exitButton.setFont(new Font("Chalkboard",Font.PLAIN,14));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(frame,"Are you sure you want to exit?","Confirm exit",JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });

        backButton = new JButton("Back");
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setPreferredSize(new Dimension(scrollPane.getWidth()/6,40));
        backButton.setMinimumSize(backButton.getPreferredSize());
        backButton.setMaximumSize(backButton.getPreferredSize());
        backButton.setFont(new Font("Chalkboard",Font.PLAIN,14));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(frame,"Are you sure you want to go back?","Confirm",JOptionPane.YES_NO_OPTION);
                if(option ==JOptionPane.YES_OPTION)
                {
                    OrganizerMainPage o = new OrganizerMainPage(username);
                    frame.setVisible(false);
                }
            }
        });

        deleteTaskField = new JComboBox<Integer>();
        deleteTaskField.setEditable(false);
        deleteTaskField.addItem(1);
        deleteTaskField.setPreferredSize(new Dimension(scrollPane.getWidth()/6,40));
        deleteTaskField.setMinimumSize(deleteTaskField.getPreferredSize());
        deleteTaskField.setMaximumSize(deleteTaskField.getPreferredSize());
        deleteTaskField.setFont(new Font("Chalkboard",Font.PLAIN,14));

        deleteTask = new JButton("Delete from Combo Box");
        deleteTask.setAlignmentX(Component.LEFT_ALIGNMENT);
        deleteTask.setPreferredSize(new Dimension(scrollPane.getWidth()/6,40));
        deleteTask.setMinimumSize(deleteTask.getPreferredSize());
        deleteTask.setMaximumSize(deleteTask.getPreferredSize());
        deleteTask.setFont(new Font("Chalkboard",Font.PLAIN,14));
        deleteTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(taskNameLabel.size()<=1))
                {
                    int index = Integer.parseInt(deleteTaskField.getSelectedItem().toString())-1;
                    smallPanel.remove(taskNameLabel.get(index));
                    smallPanel.remove(taskNameField.get(index));
                    smallPanel.remove(taskDescriptionLabel.get(index));
                    smallPanel.remove(taskDescriptionField.get(index));
                    smallPanel.remove(taskLinkLabel.get(index));
                    smallPanel.remove(taskLinkField.get(index));
                    taskNameLabel.remove(index);
                    taskNameField.remove(index);
                    taskDescriptionLabel.remove(index);
                    taskDescriptionField.remove(index);
                    taskLinkLabel.remove(index);
                    taskLinkField.remove(index);
                    for(int i=0;i<taskNameLabel.size();i++)
                    {
                        taskNameLabel.get(i).setText("Name for Task "+(i+1));
                        taskDescriptionLabel.get(i).setText("Description for Task "+(i+1));
                        taskLinkLabel.get(i).setText("Link for Task "+(i+1));
                    }
                    deleteTaskField.removeItemAt(taskNameLabel.size());
                }
            }
        });

        GroupLayout.ParallelGroup pg = gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(raffleNameLabel)
                .addComponent(raffleNameField)
                .addComponent(raffleRulesLabel)
                .addComponent(raffleRules)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(raffleNumWinnersLabel)
                        .addComponent(raffleEndDateLabel)
                )
                .addGroup(gl.createSequentialGroup()
                        .addComponent(raffleNumWinnersField)
                        .addComponent(raffleEndDateField)
                )

                .addGroup(gl.createSequentialGroup()
                        .addComponent(addTask)
                        .addComponent(deleteTaskField)
                        .addComponent(deleteTask)
                        .addComponent(createRaffle)
                        .addComponent(backButton)
                        .addComponent(exitButton)
                )

                .addComponent(taskNameLabel.get(0))
                .addComponent(taskNameField.get(0))
                .addComponent(taskDescriptionLabel.get(0))
                .addComponent(taskDescriptionField.get(0))
                .addComponent(taskLinkLabel.get(0))
                .addComponent(taskLinkField.get(0));

        GroupLayout.SequentialGroup sg = gl.createSequentialGroup()
                .addComponent(raffleNameLabel)
                .addComponent(raffleNameField)
                .addComponent(raffleRulesLabel)
                .addComponent(raffleRules)
                .addGroup(gl.createParallelGroup()
                        .addComponent(raffleNumWinnersLabel)
                        .addComponent(raffleEndDateLabel)
                )
                .addGroup(gl.createParallelGroup()
                        .addComponent(raffleNumWinnersField)
                        .addComponent(raffleEndDateField)
                )
                .addGap(30)
                .addGroup(gl.createParallelGroup()
                        .addComponent(addTask)
                        .addComponent(deleteTaskField)
                        .addComponent(deleteTask)
                        .addComponent(createRaffle)
                        .addComponent(backButton)
                        .addComponent(exitButton)
                )
                .addComponent(taskNameLabel.get(0))
                .addComponent(taskNameField.get(0))
                .addComponent(taskDescriptionLabel.get(0))
                .addComponent(taskDescriptionField.get(0))
                .addComponent(taskLinkLabel.get(0))
                .addComponent(taskLinkField.get(0))
                .addGap(30);

        addTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JLabel tNameLabel = new JLabel("Name for Task "+(taskNameLabel.size()+1));
                tNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                tNameLabel.setPreferredSize(new Dimension(scrollPane.getWidth(),30));
                tNameLabel.setMinimumSize(tNameLabel.getPreferredSize());
                tNameLabel.setMaximumSize(tNameLabel.getPreferredSize());
                tNameLabel.setFont(new Font("Chalkboard",Font.PLAIN,18));
                tNameLabel.setForeground(new Color(0,0,0));
                taskNameLabel.add(tNameLabel);

                JLabel tDescriptionLabel = new JLabel("Description for Task "+(taskDescriptionLabel.size()+1));
                tDescriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                tDescriptionLabel.setPreferredSize(new Dimension(scrollPane.getWidth(),30));
                tDescriptionLabel.setMinimumSize(tDescriptionLabel.getPreferredSize());
                tDescriptionLabel.setMaximumSize(tDescriptionLabel.getPreferredSize());
                tDescriptionLabel.setFont(new Font("Chalkboard",Font.PLAIN,18));
                tDescriptionLabel.setForeground(new Color(0,0,0));
                taskDescriptionLabel.add(tDescriptionLabel);

                JLabel tLinkLabel = new JLabel("Link for Task "+(taskLinkLabel.size()+1));
                tLinkLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                tLinkLabel.setPreferredSize(new Dimension(scrollPane.getWidth(),30));
                tLinkLabel.setMinimumSize(tLinkLabel.getPreferredSize());
                tLinkLabel.setMaximumSize(tLinkLabel.getPreferredSize());
                tLinkLabel.setFont(new Font("Chalkboard",Font.PLAIN,18));
                tLinkLabel.setForeground(new Color(0,0,0));
                taskLinkLabel.add(tLinkLabel);

                JTextField tNameField = new JTextField();
                tNameField.setAlignmentX(Component.LEFT_ALIGNMENT);
                tNameField.setPreferredSize(new Dimension(scrollPane.getWidth(),40));
                tNameField.setMinimumSize(tNameField.getPreferredSize());
                tNameField.setMaximumSize(tNameField.getPreferredSize());
                tNameField.setFont(new Font("Chalkboard",Font.PLAIN,18));
                tNameField.setForeground(new Color(0,0,0));
                taskNameField.add(tNameField);

                JTextField tLinkField = new JTextField();
                tLinkField.setAlignmentX(Component.LEFT_ALIGNMENT);
                tLinkField.setPreferredSize(new Dimension(scrollPane.getWidth(),40));
                tLinkField.setMinimumSize(tLinkField.getPreferredSize());
                tLinkField.setMaximumSize(tLinkField.getPreferredSize());
                tLinkField.setFont(new Font("Chalkboard",Font.PLAIN,18));
                tLinkField.setForeground(new Color(0,0,0));
                taskLinkField.add(tLinkField);

                JTextArea tArea = new JTextArea();
                tArea.setLineWrap(true);
                tArea.setAlignmentX(Component.LEFT_ALIGNMENT);
                tArea.setPreferredSize(new Dimension(scrollPane.getWidth(),40));
                tArea.setMinimumSize(tArea.getPreferredSize());
                tArea.setMaximumSize(tArea.getPreferredSize());
                tArea.setFont(new Font("Chalkboard",Font.PLAIN,18));
                tArea.setForeground(new Color(0,0,0));
                taskDescriptionField.add(tArea);

                pg.addComponent(taskNameLabel.get(taskNameLabel.size()-1))
                        .addComponent(taskNameField.get(taskNameField.size()-1))
                        .addComponent(taskDescriptionLabel.get(taskDescriptionLabel.size()-1))
                        .addComponent(taskDescriptionField.get(taskDescriptionField.size()-1))
                        .addComponent(taskLinkLabel.get(taskLinkLabel.size()-1))
                        .addComponent(taskLinkField.get(taskLinkField.size()-1));

                sg.addGap(30)
                        .addComponent(taskNameLabel.get(taskNameLabel.size()-1))
                        .addComponent(taskNameField.get(taskNameField.size()-1))
                        .addComponent(taskDescriptionLabel.get(taskDescriptionLabel.size()-1))
                        .addComponent(taskDescriptionField.get(taskDescriptionField.size()-1))
                        .addComponent(taskLinkLabel.get(taskLinkLabel.size()-1))
                        .addComponent(taskLinkField.get(taskLinkField.size()-1))
                        .addGap(30);
                gl.setHorizontalGroup(pg);
                gl.setVerticalGroup(sg);
                deleteTaskField.addItem(taskNameField.size());
            }
        });

        gl.setHorizontalGroup(pg);
        gl.setVerticalGroup(sg);

        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(bigPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}


