/*
 * The programs are designed for PDC paper
 */
package DataBase;

import Main.Staff;
import Main.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class DBManager {

    private static final String USER_NAME = "zeyad"; //your DB username
    private static final String PASSWORD = "G00g1eaz"; //your DB password
    private static final String URL = "jdbc:derby:projectD_Ebd;create=true";
    Connection conn;
   
    // Constructor for DBManager
    public DBManager() {
    establishConnection(); // Establish a connection to the database
     CreateTable crateTable = new CreateTable(this);  // Create tables for Student and Staff if they do not already exist
     
       
      testTable("STAFF");
      testTable("STUDENT");
      getStaffList();
      getStudentsList();
    
    }
    
      // Main method to test the DBManager
     public static void main(String[] args) {
        DBManager dbManager = new DBManager();
    
    }


       // Method to retrieve the current database connection
    public Connection getConnection() {
        return this.conn;
    }

  // Method to establish a connection to the database
    public void establishConnection() {
        if (this.conn == null){ // Check if the connection is not already established
            
            try {
  
                conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
                
                
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
                
        }

    }

    // Method to close the database connection
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

      // Method to execute a SQL query and return a ResultSet
    public ResultSet queryDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
             // Create a Statement object
            statement = connection.createStatement();
            // Execute the SQL query
            resultSet = statement.executeQuery(sql);

        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return resultSet; // Return the ResultSet
    }

      // Method to update the database using a SQL statement
    public void updateDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement(); // Create a Statement object
            statement.executeUpdate(sql);  // Execute the update statement

           //handle exceptions 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
       // Method to test the structure of a given table in the database
    public void testTable(String table){
        
        
        try {Statement st = conn.createStatement();
            ResultSet resultSet = conn.getMetaData().getColumns(null, null,table, null);
            
              // Print the columns in the specified table
            System.out.println("Columns in "+table+" table:");
            while (resultSet.next()) {         
                String columnName = resultSet.getString("COLUMN_NAME");
                String columnType = resultSet.getString("TYPE_NAME");
                int columnSize = resultSet.getInt("COLUMN_SIZE");

                  // Print column details
                System.out.println("Column Name: " + columnName + ", Type: " + columnType + ", Size: " + columnSize);
            }
            
            //handle exceptions
        }catch(SQLException ex){
            System.out.println("exception");
        }
    }
     // Method to retrieve student data as an Object array
    public Object[][] getStudentsObject(){
        
        // Create an ArrayList to hold Student objects
        ArrayList<Student> studentList = new ArrayList<>();
        
        try { String query = "SELECT * FROM STUDENT";  // SQL query to select all students
            
            PreparedStatement pstmt = conn.prepareStatement(query); // Prepare the SQL statement
            
            ResultSet rs = pstmt.executeQuery(); // Execute the query
            
              // Loop through the result set and create Student objects
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                String major = rs.getString("major");
                char gender = rs.getString("gender").charAt(0);
                  // Create a new Student object and add it to the list
                 Student student = new Student(id,firstName,lastName,email,major,gender);
           studentList.add(student);
            }
        
        
            //handle exceptions
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
         // Prepare the student data for returning as an Object array
        Object[][] studentData = new Object[studentList.size()][6];
       for (int i = 0; i < studentList.size(); i++){
           
           Student student = studentList.get(i); // Get the Student object
           
        studentData[i][0] = student.getId();
        studentData[i][1] = student.getFirstName();
        studentData[i][2] = student.getLastName();
        studentData[i][3] = student.getEmail();
        studentData[i][4] = student.getMajor();
        studentData[i][5] = student.getGender();
       }


       return studentData; // Return the 2D Object array of student data
  }
    
      // Method to retrieve a list of students
    public ArrayList getStudentsList(){
        // Create an ArrayList to hold Student objects
          ArrayList<Student> studentList = new ArrayList<>();
        
        try { String query = "SELECT * FROM STUDENT";
            
            PreparedStatement pstmt = conn.prepareStatement(query);  // Prepare the SQL statement
            
            ResultSet rs = pstmt.executeQuery();  // Execute the query
            
             // Loop through the result set and create Student objects
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                String major = rs.getString("major");
                char gender = rs.getString("gender").charAt(0);
                
                 System.out.println("Student Details: ID: " + id +
                               ", First Name: " + firstName +
                               ", Last Name: " + lastName +
                               ", Email: " + email +
                               ", Major: " + major +
                               ", Gender: " + gender);
                
                 // Create a new Student object and add it to the list
                 Student student = new Student(id,firstName,lastName,email,major,gender);
           studentList.add(student);
            }
        
        
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        
          // Method to retrieve a list of staff members
        return studentList; // return array List
    }
    public ArrayList getStaffList(){
          ArrayList<Staff>  staffList= new ArrayList<>();  // Create an ArrayList to hold Staff objects
        
        try { String query = "SELECT * FROM STAFF"; // SQL query to select all staff members
            
            PreparedStatement pstmt = conn.prepareStatement(query); // Prepare the SQL statement
            
            ResultSet rs = pstmt.executeQuery();  // Execute the query
            
             // Loop through the result set and create Staff objects
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String username = rs.getString("username");
                String password = rs.getString("password");
              
                  // Print the staff details
                  System.out.println("Staff Details: ID: " + id + 
                       ", First Name: " + firstName + 
                       ", Last Name: " + lastName + 
                       ", Username: " + username);
                  
                  // Create a new staff object and add it to the list
                 Staff staff = new Staff(id,firstName,lastName,username,password);
           staffList.add(staff);
            }
        
        
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return staffList;
    }
}
