/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import DataBase.DBManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages staff-related operations including login, registration, and password change.
 * @author zeyad
 */
public class StaffLogic{
    
 
    
    
    //instances classes
    Panel panel;
    DBManager dbManager;
    Connection conn;

    //constructor, intilizing panel and staff file manager
    public StaffLogic(Panel panel, DBManager dbManager){
   
      this.panel = panel;
      this.dbManager = dbManager;
      this.conn = dbManager.getConnection();

    }

    //Handles staff login by verifying credentials.
    public boolean ValidUserLogin(String username,String password){
        
        try {ResultSet rs = dbManager.queryDB("SELECT * FROM STAFF");
       
        
        while(rs.next()){
            String dbUsername = rs.getString("USERNAME");
            String dbPassword = rs.getString("PASSWORD");
            System.out.println(dbUsername +" "+ dbPassword);
            
            if (dbUsername.equalsIgnoreCase(username) && dbPassword.equals(password))
            {
                return true;
            }}
        }catch(SQLException ex){
            
        }
        
        return false;
  
    }
    
    public boolean validUsername(String username){
        
         String query = "SELECT Username FROM staff WHERE Username = ?";

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, username.toLowerCase()); // Set the username parameter

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                
                return false; // Username is already taken
            } else {
                
                return true; // Username is available
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace(); // Handle the exception
    }

    return false; // Return false in case of an exception

    }
    
    public boolean update(String password, String username){
        
         boolean updated =  false;
        String query = "UPDATE STAFF SET Password = ? WHERE Username = ?";
        
        try { PreparedStatement pstmt =conn.prepareStatement(query);
        
        pstmt.setString(1, password);
        pstmt.setString(2, username);
        int rowsAffected = pstmt.executeUpdate();
        
         if (rowsAffected > 0) {
            System.out.println("staff successfully added.");
            updated = true;
        } else {
            System.out.println("Failed to add staff.");
            updated = false;
        }
        
    }catch (SQLException ex){
        ex.printStackTrace();
        updated = false;
    }
        return updated;
    }
    
      public boolean register(String username,String password, String firstName, String lastName){
           boolean stAdded;
    
    try { String sql = "INSERT INTO Staff (username, password,firstName, lastName) VALUES(?,?,?,?)";
    
    PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql);
        pstmt.setString(1, username.toLowerCase());
        pstmt.setString(2, password);
        pstmt.setString(3, password.toLowerCase());
        pstmt.setString(4, lastName.toLowerCase());
        

        // Execute the update to insert the student record
        int rowsAffected = pstmt.executeUpdate();
    
        if (rowsAffected > 0) {
            System.out.println("Staff successfully added.");
            stAdded = true;
        } else {
            System.out.println("Failed to add staff.");
            stAdded = false;
        }
        
        pstmt.close();
    }catch (SQLException e){
         e.printStackTrace();
        System.out.println("Error adding staff to the database: " + e.getMessage());
        stAdded = false;
    }

    return stAdded;
    
          
      }
     //Validates the given password and return a boolean
    private boolean validPassowrd(String passwordInput){
        
      
        return false;
    }
    

  
    
  
    public boolean validPassword(String password,String username){
   
        ArrayList<Staff> staffList = new ArrayList<>(dbManager.getStaffList());
        for (Staff s: staffList){
            if (s.getUsername().equalsIgnoreCase(username)){
                if (s.getPassword().equals(password)){
                return true;
                }
            }
        }
        return false;
    }
    
   
        
     }
  







