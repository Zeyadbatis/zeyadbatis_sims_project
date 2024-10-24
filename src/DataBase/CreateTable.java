/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Zeyad
 */
public class CreateTable {
    
    
     //instances classes
    DBManager dbManager;
    Connection conn;
    Statement st;
    
      // Constructor that initializes the DBManager and creates the tables
    public CreateTable(DBManager dbManager){
        
        this.dbManager = dbManager;
        this.conn = dbManager.getConnection();
        createStudentsTable();  // Create the Students table if it doesn't exist
        createStaffTable(); // Create the Staff table if it doesn't exist
    }
    
    
     // Method to create the Students table
    private void createStudentsTable(){
         try (Statement statement = conn.createStatement()) {
            // check if table already exists
            ResultSet resultSet = conn.getMetaData().getTables(null, null, "Student", null);
            if (resultSet.next()) {
                
                return;  // Exit if the table already exists
            }

            // sql query to create the fruits table
            String createStudentsTable = "CREATE TABLE Student ("
                    + "ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
                    + "FirstName VARCHAR(100) NOT NULL,"
                    + "LastName VARCHAR(100) NOT NULL,"
                    + "Email VARCHAR(100) NOT NULL,"
                    + "Major VARCHAR(100) NOT NULL,"
                    + "Gender CHAR(1) NOT NULL)";
            statement.executeUpdate(createStudentsTable);
        } catch (SQLException ex) {
            // display an error message if an sql exception occurs
            System.out.println("table already exist");
        }
    }
    
    private void createStaffTable(){
         try (Statement statement = conn.createStatement()) {
            // check if table already exists
            ResultSet resultSet = conn.getMetaData().getTables(null, null, "staff", null);
            if (resultSet.next()) {
                
                return;
            }

            // sql query to create the fruits table
            String createStaffTable = "CREATE TABLE Staff ("
                    + "ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
                    + "Username VARCHAR(100) NOT NULL UNIQUE,"
                    + "Password VARCHAR(100) NOT NULL"
                    + "FirstName VARCHAR(100) NOT NULL"
                    + "LastName VARCHAR(100) NOT NULL)";

                  
            statement.executeUpdate(createStaffTable);
        } catch (SQLException ex) {
            // display an error message if an sql exception occurs
             System.out.println("table already exist");
        }
    }
     public void closeConnection() {
        this.dbManager.closeConnections();
    }
     
     
}
