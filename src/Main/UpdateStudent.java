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
    
   
    
    // Class instances
    Panel panel;
    private final Connection conn;
    private final DBManager dbManager;

    // Constructor initializing the UpdateStudent class with panel and student file manager
    public UpdateStudent(Panel panel,DBManager dbManager){
        
        this.panel = panel;
        this.dbManager = dbManager;
        this.conn = dbManager.getConnection();

    }
    
   //Updates the student information based on provided fields.
    public void update(String firstName, String lastName,String email, String major){
         // Retrieve the list of students from the database
        ArrayList<Student> studentData = new ArrayList<>(dbManager.getStudentsList());
          // Start building the SQL update query
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

        // Remove the last comma and space, then append the WHERE clause to the query
       query.setLength(query.length() - 2);
        query.append(" WHERE firstName = ? AND lastName = ?");

           // Add firstName and lastName to the parameters list for the WHERE clause
        parameters.add(firstName);
        parameters.add(lastName);
        try {PreparedStatement ps = conn.prepareStatement(query.toString());

               // Prepare the SQL statement with the dynamically built query
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }

           // Execute the update statement
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
    // Checks if a student with the given first name and last name exists in the database.
    public boolean validStudent(String firstName, String lastName){

          // Retrieve the list of students from the database
        ArrayList <Student> studentData = new ArrayList<>(dbManager.getStudentsList());
             // Loop through the list of students to check for a match
            for ( Student st: studentData){
                // Compare first name and last name ignoring case
                if (st.getFirstName().equalsIgnoreCase(firstName)&& st.getLastName().equalsIgnoreCase(lastName)){
                    
                  
                    return true;// student found
                }
            }
        
        return false;// Student not found
    }

    
    

        
        
    
}
