/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import DataBase.DBManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author zeyad
 */
public class Panel extends JPanel{
    
   
  
    // Class instances for managing various functionalities
    StaffLogic staff;
    RegisterStudent register;
    SearchStudent search;
    UpdateStudent update;
   
    DBManager dbManager;
    Connection conn;
    Image backgroundImage;
    
    
     // Panels for different functionalities
    JPanel mainPanel;
    JPanel welcomePanel;
    JPanel registerPanel;
    JPanel searchPanel;
    JPanel updatePanel;
    JPanel staffRegister;
    JPanel changePassword;
    
     
    CardLayout cardLayout; 
    JPanel cardPanel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton testingButton;
    String AccountUsername = "";

     // Components for student registration
    JLabel stRegisterMsg; 
    JTextField registerStFirstName; 
    JLabel stFirstNameMsg; 
    JTextField registertStLastName;
    JLabel stLastNameMsg;
    JTextField registerStEmail;
    JLabel stEmailMsg;
    JComboBox<String> registerMajorBox;
    JComboBox<Character> registerGenderBox;
    JLabel stMajorMsg; 
    JLabel stGenderMsg;
    JButton enterRegisterButton;
    JButton backRegisterButon;
    
     // Components for student update
    JLabel stUpdateMsg; 
    JTextField updateStFirstName; 
    JLabel stFirstNameMsg3; 
    JTextField updateStLastName;
    JLabel stLastNameMsg3;
    JTextField updateStEmail;
    JLabel stEmailMsg3;
    JComboBox<String> updateMajorBox;
    JLabel stMajorMsg3;
    JButton enterUpdateButton;
    JButton backUpdateButton;
    JTable updateTable;
    JScrollPane updateScrollPane;
    
     // Components for student search
    JLabel stSearchMsg;
    JLabel stFirstNameMsg1;
    JLabel stLastNameMsg1;
    JLabel stEmailMsg1;
    JLabel stMajorMsg1;
    JLabel stGenderMsg1;
    JTextField searchStFirstName; 
    JTextField searchStLastName;
    JTextField searchStEmail;
    JComboBox <Character> searchGenderBox;
    JButton enterSearchButton;
    JTable searchTable;
    JScrollPane searchScrollPane;
    JButton backSearchButton;
    JComboBox <String> searchMajorBox;
    boolean searchPaneAdded = false;
    
    // Components for staff registration
    JLabel staffMsg1;;
    JLabel staffPasswordMsg1;
    JLabel staffFirstNameMsg1;
    JLabel staffLastNameMsg1;
    JLabel staffUsernameMsg1;
    JTextField staffUsernameField1;
    JPasswordField staffPasswordField1;
    JTextField staffFirstNameField;
    JTextField staffLastNameField;
    JButton staffRegisterEnterButton;
    JButton staffRegisterBackButton;
    
     // Components for changing password
    JLabel staffMsg2;
    JLabel staffPassword1Msg2;
    JLabel staffPassword2Msg2;
    JLabel staffPassword3Msg2;
    JPasswordField staffPassword1Feid2;
    JPasswordField staffPassword2Feid2;
    JPasswordField staffPassword3Feid2;
    JButton changePassBackButton;
    JButton changePassEnterButton;
    
    
    // Column names for tables
     String[] columnsNames = {"Id"," First Name","Last Name","Email", "Major","Gender"};
     // List of majors for dropdowns
     String[] majors = {
            "", "Computer Science","Electrical Engineering","Civil Engineering","Mechanical Engineering",
            "Biochemistry", "Biology", "Physics","Mathematics","Business Administration","Finance","Marketing","Psychology",
            "Sociology", "Political Science", "Economics","History", "English Literature","Art History","Graphic Design", "Music","Theater Arts",
            "Philosophy", "Environmental Science", "Chemistry","Nursing", "Education","Information Technology", "Data Science","Cybersecurity"
        };
      // List of genders for dropdowns
     Character[] genders = {' ','M','F'};
    
    
 //Constructor for initializing all needed classes before starting any operations
     public Panel() {
         
          // Initialize class instances
         dbManager = new DBManager();
         staff =  new StaffLogic(this,dbManager);
         register = new RegisterStudent(this, dbManager);
         update = new UpdateStudent(this,dbManager);
         search = new  SearchStudent(this,dbManager);
         
          // Load components  and start of the propgema thro wecloming  method
         loadComonents();
         welcoming();
         
     
     }
     
      // Method to load components onto the panel 
    public void loadComonents() {
        
   // Create main menu panel
         mainPanel = new JPanel();
       
      // Buttons for main menu option    
    JButton exitButton = new JButton("Exit");
    JButton searchButton = new JButton("Search Student");
    JButton updateButton = new JButton("Update Student");
    JButton newStudentButton = new JButton("Register New Student");
    JButton changePasswordButton = new JButton("Change Your Password");
    JButton newStaffButton = new JButton("Register New Staff");
    loginButton = new JButton("Log In"); // Button to log in
    JButton logOutButton = new JButton("Logout");
    logOutButton.setBackground(new Color(255,220,100));
    mainPanel.setLayout(null);
 
    // Set layout and bounds for main menu componentsn
    searchButton.setBounds(0,50,170,40);
    updateButton.setBounds(0,100,170,40);
    newStudentButton.setBounds(0,150,170,40);
    changePasswordButton.setBounds(0,200,170,40);
    newStaffButton.setBounds(0,250,170,40);
    exitButton.setBounds(0, 380, 70, 40);
    logOutButton.setBounds(690, 0, 80, 40);
    
      // Add buttons to the main panel
    mainPanel.add(exitButton);
    mainPanel.add(searchButton);
    mainPanel.add(updateButton);
    mainPanel.add(newStudentButton);
    mainPanel.add(changePasswordButton);
    mainPanel.add(newStaffButton);
    mainPanel.add(logOutButton);
 
    
     // Welcome menu panel
     welcomePanel = new JPanel();
     welcomePanel.setPreferredSize(new Dimension(200,200));
     welcomePanel.setLayout(null);
       
     // Welcome message and login fields
     JLabel welcomeMassage = new JLabel("Welcome to AUT SIMS");    
     usernameField = new JTextField(20); 
     JLabel usernameLabel = new JLabel("Enter Username:"); 
     passwordField = new JPasswordField(20); 
     JLabel passwordLabel = new JLabel("Enter Password:");
     loginButton = new JButton("Login");
     testingButton = new JButton("testing");
     exitButton.setBackground(new Color (255,150,150));
     JLabel examiners1 =  new JLabel ("For examiner");
     JLabel examiners2 =  new JLabel ("Username: examiner");
     JLabel examiners3 =  new JLabel ("Password :examiner1234");

      // Set layout and bounds for welcome menu componentsn
     welcomeMassage.setBounds(10, 0, 200, 30); 
     usernameLabel.setBounds(10, 60, 200, 30);
     usernameField.setBounds(10, 90, 200, 30);
     passwordLabel.setBounds(10, 130, 200, 30);
     passwordField.setBounds(10, 160, 200, 30);
     loginButton.setBounds(10,195,70,40);
     //testingButton.setBounds(380,50,70,40);
     examiners1.setBounds(380, 50, 200, 30);
     examiners2.setBounds(380, 80, 200, 30);
     examiners3.setBounds(380, 110, 200, 30);
  
 
     // Add components to the welcome panel
       welcomePanel.add(welcomeMassage);
       welcomePanel.add(loginButton);
       welcomePanel.add(usernameLabel);
       welcomePanel.add(usernameField);
       welcomePanel.add(passwordLabel);
       welcomePanel.add(passwordField);
       welcomePanel.add(testingButton);
       welcomePanel.add(examiners1);
       welcomePanel.add(examiners2);
       welcomePanel.add(examiners3);
   
   //register student panel 
    registerPanel = new JPanel();
    registerPanel.setLayout(null);
    
    // Createcomponents for student registration
    stRegisterMsg = new JLabel("Enter new student Info");
    registerStFirstName = new JTextField(100);
    stFirstNameMsg = new JLabel("Enter First Name:"); 
    registertStLastName = new JTextField(100);
    stLastNameMsg = new JLabel("Enter Last Name:"); 
    registerStEmail = new JTextField(100);
    stEmailMsg = new JLabel("Enter Email:"); 
    registerMajorBox  = new JComboBox(majors);
    stMajorMsg = new JLabel("Enter Major:"); 
    registerGenderBox = new JComboBox(genders);
    stGenderMsg = new JLabel("Enter Gender:"); 
    enterRegisterButton = new JButton("Enter");
    backRegisterButon = new JButton("Back");
    backRegisterButon.setBackground(new Color (255,150,150));
    
    // Set bounds for registration components
    stRegisterMsg.setBounds(0, 5, 200, 30);
    stFirstNameMsg.setBounds(0, 50, 200, 30);
    registerStFirstName.setBounds(0, 70, 200, 30);
    stLastNameMsg.setBounds(0, 100, 200, 30);
    registertStLastName.setBounds(0,120 , 200, 30);
    stEmailMsg.setBounds(0, 150, 200, 30);
    registerStEmail.setBounds(0, 170, 200, 30);
    stMajorMsg.setBounds(0, 200, 200, 30);
    registerMajorBox.setBounds(0, 220, 200, 30);
    stGenderMsg.setBounds(0, 250, 200, 30);
    registerGenderBox.setBounds(0, 270, 200, 30);
    enterRegisterButton.setBounds(0,300,70,40);
    backRegisterButon.setBounds(0, 380, 70, 40);
    
    // Add components to the registration panel
    registerPanel.add(stRegisterMsg);
    registerPanel.add(registerStFirstName);
    registerPanel.add(stFirstNameMsg);
    registerPanel.add(registertStLastName);
    registerPanel.add(stLastNameMsg);
    registerPanel.add(registerStEmail);
    registerPanel.add(stEmailMsg);
    registerPanel.add(registerMajorBox);
    registerPanel.add(stMajorMsg);
    registerPanel.add(registerGenderBox);
    registerPanel.add(stGenderMsg);
    registerPanel.add(enterRegisterButton);
    registerPanel.add(backRegisterButon);
 
    
    searchPanel = new JPanel(); // Create a panel for searching students
    searchPanel.setLayout(null); // Set layout to null for manual positioning
    
     // Create and position components for student search
    stSearchMsg = new JLabel("choose a an attribute to search by");
    searchStFirstName = new JTextField(100);
    searchStLastName = new JTextField(100);
    searchStEmail = new JTextField(100);
    searchMajorBox = new JComboBox(majors);
    searchGenderBox = new JComboBox(genders);
    stFirstNameMsg = new JLabel("First Name (Optional)");
    stLastNameMsg = new JLabel("Last Name (Optional)");
    stEmailMsg = new JLabel("Email (Optional)");
    stMajorMsg = new JLabel("Major (Optional)");
    stGenderMsg = new JLabel("Gender (Optional)");
    enterSearchButton = new JButton("Enter");
    backSearchButton = new JButton("Back");
    backSearchButton.setBackground(new Color (255,150,150));
    
    // Set bounds for search components
    searchTable = new JTable(dbManager.getStudentsObject(),columnsNames);
    searchScrollPane = new JScrollPane(searchTable);
    stSearchMsg.setBounds(0, 5, 400, 30);
    searchStFirstName.setBounds(0, 70, 200, 30);
    searchStLastName.setBounds(0, 120, 200, 30);
    searchStEmail.setBounds(0, 170, 200, 30);
    searchMajorBox.setBounds(0, 220, 200, 30);
    searchGenderBox.setBounds(0, 270, 200, 30);
    stFirstNameMsg.setBounds(0,50,200,30);
    stLastNameMsg.setBounds(0,100,200,30);
    stEmailMsg.setBounds(0,150,200,30);
    stMajorMsg.setBounds(0,200,200,30);
    stGenderMsg.setBounds(0,250,200,30);
    enterSearchButton.setBounds(0, 300, 70, 40);
    backSearchButton.setBounds(0, 380, 70, 40);
    searchScrollPane.setBounds(220, 0, 530, 350);
    
    // Add components to the search panel
    searchPanel.add(stSearchMsg);
    searchPanel.add(stFirstNameMsg);
    searchPanel.add(searchStFirstName);
    searchPanel.add(stLastNameMsg);
    searchPanel.add(searchStLastName);
    searchPanel.add(stEmailMsg);
    searchPanel.add(searchStEmail);
    searchPanel.add(stMajorMsg);
    //searchPanel.add(searchStMajor);
    searchPanel.add(searchMajorBox);
    searchPanel.add(stGenderMsg);
    searchPanel.add(searchGenderBox);
    searchPanel.add(enterSearchButton);
    searchPanel.add(searchScrollPane);
    searchPanel.add(backSearchButton);
   
    
    updatePanel = new JPanel(); // Create a panel for updating student info
    updatePanel.setLayout(null); // Set layout to null for manual positioning
    
    // Create and position components for updating student information
    stUpdateMsg = new JLabel ("Enter student First and Last Name");
    JLabel stUpdateMsg2 = new JLabel("Enter the new Major or Email");
    updateStFirstName =  new JTextField(100); 
    stFirstNameMsg3 = new JLabel ("Enter First Name");
    updateStLastName = new JTextField(100);
    stLastNameMsg3 = new JLabel("Enter Last Name");
    updateStEmail = new JTextField(100);
    stEmailMsg3 = new JLabel ("Enter Emai");
    updateMajorBox= new JComboBox(majors);
    stMajorMsg3 = new JLabel ("Enter Major");
    enterUpdateButton = new JButton ("Enter");
    backUpdateButton = new JButton("Back");
    updateTable = new JTable(dbManager.getStudentsObject(),columnsNames);
    updateScrollPane = new JScrollPane(updateTable);
    backUpdateButton.setBackground(new Color(255,150,150));
    
    // Set bounds for update components
    stUpdateMsg.setBounds(0, 5, 200, 30);
    updateStFirstName.setBounds(0, 70, 200, 30);
    stFirstNameMsg3.setBounds(0, 50, 200, 30);
    stLastNameMsg3.setBounds(0, 100, 200, 30);
    updateStLastName.setBounds(0, 120, 200, 30);
    stUpdateMsg2.setBounds(0, 175, 200, 30);
    stEmailMsg3.setBounds(0, 220, 200, 30);
    updateStEmail.setBounds(0, 240, 200, 30);
    stMajorMsg3.setBounds(0, 270, 200, 30);
    updateMajorBox.setBounds(0, 290, 200, 30);
    enterUpdateButton.setBounds(0, 320, 70,40);
    backUpdateButton.setBounds(0, 380, 70, 40);
    updateScrollPane.setBounds(220, 0, 530, 350);
    
    // Add components to the update panel
    updatePanel.add(stUpdateMsg);
    updatePanel.add(stUpdateMsg2);
    updatePanel.add(updateStFirstName);
    updatePanel.add(stFirstNameMsg3);
    updatePanel.add(updateStLastName);
    updatePanel.add(stLastNameMsg3);
    updatePanel.add(updateStEmail);
    updatePanel.add(stEmailMsg3);
    updatePanel.add(stMajorMsg3);
    updatePanel.add(updateMajorBox);
    updatePanel.add(enterUpdateButton);
    updatePanel.add(backUpdateButton);
    updatePanel.add(updateScrollPane);
    
    staffRegister = new JPanel();  // Create a new JPanel for staff registration
    staffRegister.setLayout(null);  // Set layout to null for absolute positioning
   
    // creating compoenets for staff registeration 
    staffMsg1 = new JLabel("Enter new staff info");
    staffPasswordMsg1 = new JLabel ("Password");
    staffUsernameMsg1 = new JLabel("Username");
    staffFirstNameMsg1 = new JLabel ("First Name");
    staffLastNameMsg1 = new JLabel ("Last Name");
    staffUsernameField1 = new JTextField(100);
    staffPasswordField1 = new JPasswordField(100);
    staffFirstNameField = new JTextField(100);
    staffLastNameField = new JTextField(100);
    staffRegisterEnterButton = new JButton("Enter");
    staffRegisterBackButton = new JButton("Back");
    staffRegisterBackButton.setBackground(new Color(255,150,150));
     
    // Set bounds for all components in the staff registration panel
    staffMsg1.setBounds(0, 5, 200, 30);
    staffFirstNameMsg1.setBounds(0, 50, 200, 30);
    staffFirstNameField.setBounds(0,70, 200, 30);
    staffLastNameMsg1.setBounds(0, 100, 200, 30);
    staffLastNameField.setBounds(0, 120, 200, 30);
    staffUsernameMsg1.setBounds(0, 150, 200, 30);
    staffUsernameField1.setBounds(0, 170, 200, 30);
    staffPasswordMsg1.setBounds(0, 200, 200, 30);
    staffPasswordField1.setBounds(0, 220, 200, 30);
    staffRegisterEnterButton.setBounds(0, 320, 70,40);
    staffRegisterBackButton.setBounds(0, 380, 70, 40);
    
    // Add all components to the staff register panel
    staffRegister.add(staffMsg1);
    staffRegister.add(staffFirstNameField);
    staffRegister.add(staffFirstNameMsg1);
    staffRegister.add(staffLastNameMsg1);
    staffRegister.add(staffLastNameField);
    staffRegister.add(staffUsernameMsg1);
    staffRegister.add(staffUsernameField1);
    staffRegister.add(staffPasswordMsg1);
    staffRegister.add(staffPasswordField1);
    staffRegister.add(staffRegisterEnterButton);
    staffRegister.add(staffRegisterBackButton);
     
     changePassword = new JPanel();; // Create a new JPanel for changing password
     changePassword.setLayout(null);   // Set layout to null for absolute positioning
     
     //creating components for change password panel
     staffMsg2 =  new JLabel("Change your password");
     staffPassword1Msg2 = new JLabel("Original password");
     staffPassword2Msg2 = new JLabel("New password");
     staffPassword3Msg2 = new JLabel("Confirm new password");
     staffPassword1Feid2 = new JPasswordField(100);
     staffPassword2Feid2 = new JPasswordField(100);
     staffPassword3Feid2 = new JPasswordField(100);
     changePassEnterButton = new JButton("Enter");
     changePassBackButton = new JButton("Back");
     changePassBackButton.setBackground(new Color(255,150,150));
     
     //set bounds for all components in the change password panel
     staffMsg2.setBounds(0, 5, 200, 30);
     staffPassword1Msg2.setBounds(0, 50, 200, 30);
     staffPassword1Feid2.setBounds(0, 70, 200, 30);
     staffPassword2Msg2.setBounds(0, 100, 200, 30);
     staffPassword2Feid2.setBounds(0, 120, 200, 30);
     staffPassword3Msg2.setBounds(0, 150, 200, 30);
     staffPassword3Feid2.setBounds(0, 170, 200, 30);
     changePassEnterButton.setBounds(0, 200, 70, 40);
     changePassBackButton.setBounds(0, 380, 70, 40);
     
     // add all components to the change password panel
     changePassword.add(staffMsg2);
     changePassword.add(staffPassword1Msg2);
     changePassword.add(staffPassword1Feid2);
     changePassword.add(staffPassword2Msg2);
     changePassword.add(staffPassword2Feid2);
     changePassword.add(staffPassword3Msg2);
     changePassword.add(staffPassword3Feid2);
     changePassword.add(changePassEnterButton);
     changePassword.add(changePassBackButton);
     
 
    // Set up the card layout for switching between different panels
     cardLayout = new CardLayout();
     cardPanel = new JPanel(cardLayout);
     cardPanel.setPreferredSize(new Dimension(800,420));
     // Add different panels to the card panel
     cardPanel.add(mainPanel,"main");
     cardPanel.add(welcomePanel,"welcome");
     cardPanel.add(registerPanel,"register");
     cardPanel.add(searchPanel,"search");
     cardPanel.add(updatePanel, "update");
     cardPanel.add(staffRegister,"staffRegister");
     cardPanel.add(changePassword,"changePassword");
     add(cardPanel);  // Add the card panel to the main frame

   
  // Action listeners for buttons
     testingButton.addActionListener(e ->newQuery());
     newStudentButton.addActionListener(e -> register());
     searchButton.addActionListener( e-> search());
     backSearchButton.addActionListener(e -> newQuery());
     backRegisterButon.addActionListener( k -> newQuery());
     updateButton.addActionListener( e->update());
     backUpdateButton.addActionListener(e -> newQuery());
     exitButton.addActionListener(e -> System.exit(0));
     newStaffButton.addActionListener( t -> newStaff());
     staffRegisterBackButton.addActionListener( e -> newQuery());
     changePasswordButton.addActionListener( d -> changePassword());
     changePassBackButton.addActionListener( d-> newQuery());
     logOutButton.addActionListener( e -> welcoming());
 
}
    // Method to display the main frame
    public void displayFrame() {
        // Set up the frame
        JFrame frame = new JFrame("Student Information Management System");
        frame.setSize(800, 470);    // Set frame size
        frame.setLayout((new FlowLayout(FlowLayout.LEFT)));  // Set layout for the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        frame.setLocationRelativeTo(null);  // Center the frame on the screen
        frame.add(this);   // Add the current panel to the frame
        frame.setVisible(true);      // Make the frame visible
    }

     // welcoming method for user login 
    public void welcoming(){
     cardLayout.show(cardPanel, "welcome");   // Show the welcome panel
     AccountUsername = null;  // Reset the account username
      
     // Remove existing action listeners from the login button
         for (ActionListener al : loginButton.getActionListeners()) {
        loginButton.removeActionListener(al); 
        }
      // Add a new action listener for the login button
        loginButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Get the input username and password
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
           
                 // Validate the input fields
                if (usernameField.getText().isBlank()|| passwordField.getText().isBlank()){
                     JOptionPane.showMessageDialog(null, "Fill in all fields please");
                }
                else if (!staff.ValidUserLogin(username, password)){
                     JOptionPane.showMessageDialog(null, "Wrong password or username");
                }
                 // If login is successful
                else if (staff.ValidUserLogin(username, password)){
                    AccountUsername = username.toLowerCase(); // Store the username
                     JOptionPane.showMessageDialog(null, "Welcome");
                    newQuery(); // Proceed to the query menu
                }
            }
        });
    }
    
    // a method to for queries menu
    public void newQuery(){
        cardLayout.show(cardPanel,"main");  // Show the main query pane
        clearFields(); // Clear input fields for fresh use
 

    }
    // Method to handle registration process
    private void register(){
       
        cardLayout.show(cardPanel,"register" );   // Show the registration panel

          // Remove existing action listeners from the register button
       for (ActionListener al : enterRegisterButton.getActionListeners()) {
        enterRegisterButton.removeActionListener(al); 
        }
        
          // Add a new action listener for the registration button
        enterRegisterButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
          
         // Variables for student registration information
        String stFirstName;
        String stLastName;
        String stEmail;
        String stMajor;
        char stGender = ' ';
            
          // Validate that all fields are filled in
          if (registerStFirstName.getText().isBlank() || registertStLastName.getText().isBlank() || registerStEmail.getText().isBlank() || registerMajorBox.getSelectedIndex()==0 ||registerGenderBox.getSelectedIndex()==0){
                JOptionPane.showMessageDialog(null, "Fill in all fields please");
          } else {     
         
          // Collect the entered data
         stFirstName = registerStFirstName.getText();
         stLastName = registertStLastName.getText();
         stEmail = registerStEmail.getText();
         stMajor =(String) registerMajorBox.getSelectedItem();
         stGender = (char) registerGenderBox.getSelectedItem();
          
               // Validate the email format
              if ( !stEmail.contains("@gmail.com")){
            JOptionPane.showMessageDialog(null, "Enter a valid email (example@gmail.com)");
        }
              else {
                     // Attempt to register the new student
                   if (register.newStudent(stFirstName, stLastName, stEmail, stMajor, stGender)){
                       JOptionPane.showMessageDialog(null, "New student registerd successfully", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);

                       newQuery();  // Show the main query menu
                   } 
                   else {
                       JOptionPane.showMessageDialog(null, "Failed to register new student. Please check the details and try again.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                   }
              }
          }
            }
        });
    }
    
    //mathod that clear text feil for next use
    private void clearFields(){
           // Reset all fields to empty or default states
        registerStFirstName.setText("");
        registertStLastName.setText("");
        registerStEmail.setText("");
        registerMajorBox.setSelectedIndex(0);
        registerGenderBox.setSelectedIndex(0);
        updateStLastName.setText("");
        updateStEmail.setText("");
        updateMajorBox.setSelectedIndex(0);
        updateStFirstName.setText("");
        searchStFirstName.setText("");
        searchStLastName.setText("");
        searchStEmail.setText("");
        searchMajorBox.setSelectedIndex(0);
        searchGenderBox.setSelectedIndex(0);
        staffUsernameField1.setText("");
        staffFirstNameField.setText("");
        staffPasswordField1.setText("");
        staffLastNameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        staffPassword1Feid2.setText("");
        staffPassword2Feid2.setText("");
        staffPassword3Feid2.setText("");
    }
    
    // Method to handle student search functionality
    private void search(){
        cardLayout.show(cardPanel, "search");  // Show the search panel
        
           // refresh search pane
            searchPanel.remove(searchScrollPane);    // Remove existing scroll pane if present
            searchTable = new JTable(dbManager.getStudentsObject(),columnsNames); // Create a new table with student data
            searchScrollPane = new JScrollPane(searchTable); // Add table to a scroll pane
            searchPanel.add(searchScrollPane = new JScrollPane(searchTable)); // Add scroll pane to the panel
            searchScrollPane.setBounds(220, 0, 530, 350);  // Set bounds for the scroll pane
            searchPanel.revalidate();// Revalidate the panel to reflect changes
            searchPanel.repaint();  // Repaint the panel to ensure visibility
        
      
             // Remove existing action listeners from the search button
        for (ActionListener al : enterSearchButton.getActionListeners()) {
        enterSearchButton.removeActionListener(al); 
        }
          // Add a new action listener for the search button
        enterSearchButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                
        //  // Variables for search criteria        
        String stFirstName = "";
        String stLastName = "";
        String stEmail = "";
        String stMajor = "";
        char stGender = ' ';
        try {
            
              // Collect user inputs for search accordingly 
            if (!searchStFirstName.getText().isBlank()){
                stFirstName  =  searchStFirstName.getText().trim();
            }
            if (!searchStLastName.getText().isBlank()){
               stLastName  =  searchStLastName.getText().trim();
            }
            if (!searchStEmail.getText().isBlank()){
                stEmail  =  searchStEmail.getText().trim();
            }
            if (!searchMajorBox.getSelectedItem().equals("")){
                
                stMajor  = (String) searchMajorBox.getSelectedItem();
            }
            if (!(searchGenderBox.getSelectedItem().equals(" "))){
               
                
                 stGender  = (char)  searchGenderBox.getSelectedItem();
                 
            }
            
            // Search for students based on the input criteria and store data on array
            Object[][]  studentData = search.searchStudents(stFirstName.toLowerCase(),stLastName.toLowerCase(),stEmail.toLowerCase(),stMajor.toLowerCase(),Character.toLowerCase(stGender));

             // Update the search table with new data
            searchPanel.remove(searchScrollPane);
            searchScrollPane.remove(searchTable);
            searchTable = new JTable(studentData,columnsNames);
            searchPanel.add(searchScrollPane = new JScrollPane(searchTable));
            searchScrollPane.setBounds(220, 0, 530, 350);
            searchPanel.revalidate();
            searchPanel.repaint();
            
          
        }catch (Exception ex){
            ex.printStackTrace();  // Print stack trace for any exceptions
        }
   
            }
        });
   
    }
    
    // Method to handle updates of student information
    private void update(){
        
        cardLayout.show(cardPanel, "update");// Show the update panel
        
        // refresh search pane
            updatePanel.remove(updateScrollPane);  // Remove existing scroll pane if present
            updateScrollPane.remove(updatePanel); // Remove the previous panel
            updateTable = new JTable(dbManager.getStudentsObject(),columnsNames);  // Create a new table with student data
            updatePanel.add(updateScrollPane = new JScrollPane(updateTable));   // Add table to a scroll pane
            updateScrollPane.setBounds(220, 0, 530, 350);  // Set bounds for the scroll pane
            updatePanel.revalidate();  // Revalidate the panel to reflect changes
            updatePanel.repaint(); // Repaint the panel to ensure visibility
            
            // Remove existing action listeners from the update button
        for (ActionListener al : enterUpdateButton.getActionListeners()) {
        enterUpdateButton.removeActionListener(al); 
        }
        
         // Add a new action listener for the update button
        enterUpdateButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                 // Variables for updated information
                String firstName;
                String lastName;
                String email = "";
                String major = "";
             // Validate input fields      
        if (updateStFirstName.getText().isBlank() || updateStLastName.getText().isBlank()){
             JOptionPane.showMessageDialog(null, "Fill in First Name and Last Name Please");
        }
        
        //valdiate one attribute at least was entered 
        else if (updateStEmail.getText().isBlank() && updateMajorBox.getSelectedItem().equals("")){
            
            JOptionPane.showMessageDialog(null, "Enter at least one Attribute to update");
        }
        // validate email
        else if (!updateStEmail.getText().isBlank() && !updateStEmail.getText().contains("@gmail.com")){
                    JOptionPane.showMessageDialog(null, "Enter a valid email (example@gmail.com)");
                    }
        
        else {
            // get variales from feilds
            firstName = updateStFirstName.getText();
            lastName = updateStLastName.getText();
            email = updateStEmail.getText();
            major =(String) updateMajorBox.getSelectedItem();
            
            // check if the student with given first name and last name exist 
             if (!update.validStudent(firstName,lastName)){
                 JOptionPane.showMessageDialog(null, "No student with such First Name and Last name");
             }
             else {
                 //call update method to update student information
                 update.update(firstName.toLowerCase(), lastName.toLowerCase(), email.toLowerCase(), major.toLowerCase());
                 JOptionPane.showMessageDialog(null, "Student have been updated");
                 ArrayList<Student> studentList = new ArrayList<>(dbManager.getStudentsList());
                 Student tempStudent = new Student();
                 for(Student st : studentList){
                     if (st.getFirstName().equalsIgnoreCase(firstName) && st.getLastName().equalsIgnoreCase(lastName)){
                         tempStudent = st;
                         break;
                     }
                     
                 }
                 
                 Object[][] studentData = search.searchStudents(tempStudent.getFirstName(), tempStudent.getLastName(), tempStudent.getEmail(), tempStudent.getMajor(), tempStudent.getGender());
     
             // update scroll pane to ddisplay the student after update 
            updatePanel.remove(updateScrollPane);
            updateScrollPane.remove(updatePanel);
            updateTable = new JTable(studentData,columnsNames);
            updatePanel.add(updateScrollPane = new JScrollPane(updateTable));
            updateScrollPane.setBounds(220, 0, 530, 350);
            updatePanel.revalidate();
            updatePanel.repaint();

             }

        }
         
            }
        });

    }
   
    private void newStaff(){
         // Display the staff registration panel
       cardLayout.show(cardPanel, "staffRegister");

         // Remove existing action listeners from the staff register button to avoid duplicate actions
       for (ActionListener al : staffRegisterEnterButton.getActionListeners()) {
        staffRegisterEnterButton.removeActionListener(al); 
        }
       
       // Add a new action listener for the staff registration button
        staffRegisterEnterButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                    // Declare variables to hold user input values
                String username;
                String password;
                String firstName;
                String lastName;
                
                    // Declare variables to hold user input values
                if (staffFirstNameField.getText().isBlank() ||
                      staffLastNameField.getText().isBlank()||
                     staffPasswordField1.getText().isBlank()||
                     staffUsernameField1.getText().isBlank()){
                   JOptionPane.showMessageDialog(null, "Fill in all fields please");  
            }
                // Check if the password meets the minimum length requirement
                else if (staffPasswordField1.getText().length() <8){
                    JOptionPane.showMessageDialog(null, "Password must contain at least 8 characters"); 
                }
                 // Verify if the username is available
                else if (!(staff.validUsername(staffUsernameField1.getText()))){
                    JOptionPane.showMessageDialog(null, "This username is not available");
                }
                // If all validations pass, proceed with registration
                else {
                      // Retrieve and store input values
                   username = staffUsernameField1.getText();
                   password = staffPasswordField1.getText();
                   firstName = staffFirstNameField.getText();
                   lastName = staffLastNameField.getText();
                     // call register method to add new staff to data base
                    if (staff.register(username, password, firstName, lastName))
                    {
                          JOptionPane.showMessageDialog(null, "Staff successfully added");
                           newQuery();   
                    }
                }
            }
        });
    } 
    
    private void changePassword() {
        
        cardLayout.show(cardPanel, "changePassword"); // Display the change password panel
        

      // Remove existing action listeners from the change password button to avoid duplicate actions
        for (ActionListener al : changePassEnterButton.getActionListeners()) {
        changePassEnterButton.removeActionListener(al); 
        }
        
          // Add a new action listener for the change password button
        changePassEnterButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                    // Declare variables to hold the password input values
                String password;
                String newPassword1;
                String newPassword2;
                     // Validate input fields to ensure all required fields are filled
                if (staffPassword1Feid2.getText().isBlank()||
                        staffPassword2Feid2.getText().isBlank()||
                        staffPassword3Feid2.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Fill in all fields");
                    
                  // Verify if the old password is correct
                }
                else if (!staff.validPassword(staffPassword1Feid2.getText(), AccountUsername)){
                    JOptionPane.showMessageDialog(null, "Wrong password, try again please");
                }
                   // Check if the new password meets the minimum length requirement
                else if (staffPassword2Feid2.getText().length()<8){
                    JOptionPane.showMessageDialog(null, "new password must contain at least 8 characters");
                }
               // Confirm that the new password matches the confirmation password
                else if(!staffPassword2Feid2.getText().equals(staffPassword3Feid2.getText())){
                    JOptionPane.showMessageDialog(null, "Passwords do not match, try again please");
                }
               // If all validations pass, proceed with updating the password
                else{
                       // Retrieve and store input values
                 password =  staffPassword1Feid2.getText();
                 newPassword1 = staffPassword2Feid2.getText();
                 newPassword2 = staffPassword3Feid2.getText();
                    
                    // Update the password for the given account username
                    if (staff.update(newPassword1, AccountUsername)){
                        JOptionPane.showMessageDialog(null, "Password was updated successfully");
                        newQuery(); 
                    }
                    
                }
                     
       
            }
        });
    }
  
    
  
}
