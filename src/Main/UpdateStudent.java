/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import DataBase.DBManager;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author zeyad
 */
public class UpdateStudent {
    
     // Scanner for reading user input
    Scanner scan =  new Scanner(System.in);
    
    // Class instances
    Panel panel;

    private final Connection conn;
    private final DBManager dbManager;
    
    int id;
    
    
    // Constructor initializing the UpdateStudent class with panel and student file manager
    public UpdateStudent(Panel panel,DBManager dbManager){
        this.panel = panel;
        this.dbManager = dbManager;
      
        this.conn = dbManager.getConnection();
      
  
    }
    
    // Method to start the update process
    public void update(String firstName, String lastName,String email, String major){
        
        ArrayList<Student> studentData = new ArrayList<>(dbManager.getStudentsList());
        StringBuilder query =  new StringBuilder("UPDATE Student SET ");
        ArrayList<Object> parameters = new ArrayList<>();

        // Dynamically build the query based on which fields are provided
        if (!email.isBlank()) {
            query.append("email = ?, ");
            parameters.add(email);
        }
        if (!major.equals("")) {
            query.append("major = ?, ");
            parameters.add(major);
        }

      
       query.setLength(query.length() - 2);
        query.append(" WHERE firstName = ? AND lastName = ?");

       
        parameters.add(firstName);
        parameters.add(lastName);
        try {PreparedStatement ps = conn.prepareStatement(query.toString());

            
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }

           
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student record updated successfully.");
            } else {
                System.out.println("No matching student found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle exceptions appropriately
        }

    }

    
    

        
        
    
}
