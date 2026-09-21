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

    
    // Instances of classes
    DBManager dbManager;
    Connection conn;

    Panel panel;
    
    
    
     // Constructor initializing the SearchStudent class 
    public SearchStudent(Panel panel, DBManager dbManager){
      
        this.panel = panel;
        this.conn = dbManager.getConnection();
  
    }
    
     // Method to search for a student based on various criteria and return an array of student with updated info
    public Object[][] searchStudents(String firstName, String lastName, String email, String major, char gender){
        
        
          // List to hold the resulting students
        ArrayList<Student> studentList = new ArrayList<>(); 
        
          // StringBuilder to construct the SQL query dynamically
       StringBuilder query = new StringBuilder("SELECT * FROM STUDENT WHERE 1=1");
         // List to hold the parameters for the prepared statement
       List<Object> parameters = new ArrayList<>();

       
       // Dynamically build the query based on non-blank input parameters
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
       if(gender =='m' || gender =='f'){
           query.append(" AND gender = ?");
           parameters.add(String.valueOf(gender));
       }
       
       
            // Execute the prepared statement and retrieve the results
       try { PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
        // Set the parameters for the prepared statement
        for (int i = 0; i < parameters.size(); i++) {
            preparedStatement.setObject(i + 1, parameters.get(i));
        }

           // Execute the query and process the ResultSet
        ResultSet rs = preparedStatement.executeQuery(); 
       while(rs.next()){
           
             // Retrieve data from the current row
           int id = rs.getInt("ID");
           String stFirstName = rs.getString("Firstname");
           String stLastName = rs.getString("lastname");
           String stEmail = rs.getString("email");
           String stMajor = rs.getString("major");
           char stGender = rs.getString("GENDER").charAt(0);
           
               // Create a new Student object and add it to the list
           Student student = new Student(id,stFirstName,stLastName,stEmail,stMajor,stGender);
           studentList.add(student);
       }
               
       }catch (SQLException ex){
              // Handle any SQL exceptions that occur
          System.err.println("An error occurred while searching for students: " + ex.getMessage());
    ex.printStackTrace();
       }
       
          // Convert the list of students to a 2D array to return
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
       
       return studentData;   // Return the resulting 2D array of student data
        
    
    }
      
    
    
    }
    

