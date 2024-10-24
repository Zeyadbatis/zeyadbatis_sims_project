/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import DataBase.DBManager;

import java.util.Scanner;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.util.List;
import org.apache.derby.client.am.SqlException;

/**
 *
 * @author zeyad
 */
public class SearchStudent {
    
    // Scanner for reading user input
    Scanner scan = new Scanner(System.in);
    
    // Instances of classes
      DBManager dbManager;
    Connection conn;

    Panel panel;
    
    
    
     // Constructor initializing the SearchStudent class with panel and student file manager
    public SearchStudent(Panel panel, DBManager dbManager){
      
        this.panel = panel;
        this.conn = dbManager.getConnection();
  
    }
    
     // Method to search for a student based on various criteria
    public Object[][] searchStudents(String firstName, String lastName, String email, String major, char gender){
        
        
        ArrayList<Student> studentList = new ArrayList<>();    
       StringBuilder query = new StringBuilder("SELECT * FROM STUDENT WHERE 1=1");
       List<Object> parameters = new ArrayList<>();

       
       if(!firstName.isBlank()){
           
           query.append(" AND FIRSTNAME = ?");
           parameters.add(firstName);
           
       }
       
       if(!lastName.isBlank()){
           query.append(" AND lastName = ?");
           parameters.add(lastName);
       }
       if(!email.isBlank()){
           query.append(" AND email = ?");
           parameters.add(email);
       }
       if(!major.isBlank()){
           query.append(" AND major = ?");
           parameters.add(major);
       }
       if(gender =='M' || gender =='F'){
           query.append(" AND gender = ?");
           parameters.add(String.valueOf(gender));
       }
       
       
       try { PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
        // Set the parameters for the prepared statement
        for (int i = 0; i < parameters.size(); i++) {
            preparedStatement.setObject(i + 1, parameters.get(i));
        }

        // Execute the query
        ResultSet rs = preparedStatement.executeQuery(); 
       
       while(rs.next()){
           
           int id = rs.getInt("ID");
           String stFirstName = rs.getString("Firstname");
           String stLastName = rs.getString("lastname");
           String stEmail = rs.getString("email");
           String stMajor = rs.getString("major");
           char stGender = rs.getString("GENDER").charAt(0);
           
           Student student = new Student(id,stFirstName,stLastName,stEmail,stMajor,stGender);
           studentList.add(student);
       }
               
       }catch (SQLException ex){
          System.err.println("An error occurred while searching for students: " + ex.getMessage());
    ex.printStackTrace();
       }
       
       Object[][] studentData = new Object[studentList.size()][6];
       for (int i = 0; i < studentList.size(); i++){
           
           Student student = studentList.get(i);
           
        studentData[i][0] = student.getId();
        studentData[i][1] = student.getFirstName();
        studentData[i][2] = student.getLastName();
        studentData[i][3] = student.getEmail();
        studentData[i][4] = student.getMajor();
        studentData[i][5] = student.getGender();
       }
       
       return studentData;
        
        
        /* 
        boolean validInput = false;
        String input = "";
        
        System.out.println(helper.spliter);
        System.out.println("Search for a student");
        System.out.println(helper.spacer);
        
        // Prompt user to choose a search option
      int choice =  helper.inputInt("Choose from the following\n1) Search by Id \n2) Search by first name\n3) Search by last name\n"
                +"4) Search by Email\n5) Search by major\n6) Search by gender", 6);
      
      // Loop to scan user input for student searching
      while (!validInput){
         
      try {
          
          validInput = true;
          switch (choice){
              
              case 1:
                  System.out.println("Enter ID:");
                  int id = Integer.parseInt(scan.nextLine()); 
                  searchById(id);
                  break;
                  
              case 2:
                  System.out.println("Enter first name");
                  input = scan.nextLine();
                  searchByFirstName(input);
                  break;
                  
              case 3: 
                  System.out.println("Enter last name");
                  input = scan.next();
                  searchByLastName(input);
                  break;
              case 4:
                  System.out.println("Enter Email");
                  input = scan.next();
                  searchByEmail(input);
                  break;
                  
              case 5:
                  System.out.println("Enter Major");
                  input = scan.next();
                  searchByMajor(input);
                  break;
              case 6:
                  System.out.println("Enter Gender");
                  input = scan.next();
                  char gender = input.charAt(0);
                  searchByGender(gender);
                  break;      
          }
      }catch (Exception e){
          System.out.println(helper.spliter);
          System.out.println("Enter a valid input");
          validInput = false;
          }
      
      
      }
      helper.endOfQuery();
       
    }
    
    
    // Method to search for a student by ID
    private void searchById(int id){
        studentFile.getStudent();
        System.out.println(helper.spliter+helper.spliter+helper.spliter);
        System.out.println("Students found:");
        
        studentManager.selectAllStudents(id);
       /* for(Student i:studentFile.studentList){
            if (id == i.getId())
            printStudent(i);
        }

      */
    }
      
    
    
    }
    

