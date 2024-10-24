/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import DataBase.DBManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.Scanner;

/**
 *
 * @author zeyad
 * 
 * This class is responsible for registring new students
 * 
 */
public class RegisterStudent {
   
     
    // Scanner for reading user input
    Scanner scan = new Scanner(System.in);
    
    // Class instances
   
    DBManager dbManager;
    Connection conn;
    Student student;
    Panel panel;
    
    //temp attributes to be passed as arguiment
    int id;
    String firstName;
    String lastName;
    String email;
    char gender;
    String major;


    
    // class contrsuctor 
public RegisterStudent(Panel panel , DBManager dbManager){
    this.panel = panel;
    this.dbManager = dbManager;
    conn = dbManager.getConnection();
   

    
    
   }


/**
     * Handles the process of adding a new student by collecting input
     * from the user and writing the new student's information to the file.
     */

public boolean newStudent(String firstName, String lastName,String email,String major,char gender){
    
    boolean stAdded;
    
    try { String sql = "INSERT INTO STUDENT (firstName, lastName, email, major,gender) VALUES(?,?,?,?,?)";
    
    PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql);
        pstmt.setString(1, firstName.toLowerCase());
        pstmt.setString(2, lastName.toLowerCase());
        pstmt.setString(3, email.toLowerCase());
        pstmt.setString(4, major.toLowerCase());
        pstmt.setString(5, String.valueOf(gender).toLowerCase());

        // Execute the update to insert the student record
        int rowsAffected = pstmt.executeUpdate();
    
        if (rowsAffected > 0) {
            System.out.println("Student successfully added.");
            stAdded = true;
        } else {
            System.out.println("Failed to add student.");
            stAdded = false;
        }
        
        pstmt.close();
    }catch (SQLException e){
         e.printStackTrace();
        System.out.println("Error adding student to the database: " + e.getMessage());
        stAdded = false;
    }

    return stAdded;
    
     }
  }


