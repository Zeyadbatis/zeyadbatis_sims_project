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
   // private static final String URL = "jdbc:derby://localhost:1527/projectD;create=true";  //url of the DB host
    private static final String URL = "jdbc:derby:projectD_Ebd;create=true";
     

    Connection conn;
   
    

    public DBManager() {
    establishConnection();

     CreateTable crateTable = new CreateTable(this);
      
      testTable("STAFF");
      testColumn("staff");
    }
    
     public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        //System.out.println(dbManager.getConnection());
    }


    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection
    public void establishConnection() {
        if (this.conn == null){
            
            try {
  
                conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
                
                
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
                
        }

    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ResultSet queryDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return resultSet;
    }

    public void updateDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void testTable(String table){
        
        
       
  
        
        try {Statement st = conn.createStatement();
            ResultSet resultSet = conn.getMetaData().getColumns(null, null,table, null);
            
            System.out.println("Columns in "+table+" table:");
            while (resultSet.next()) {         
                String columnName = resultSet.getString("COLUMN_NAME");
                String columnType = resultSet.getString("TYPE_NAME");
                int columnSize = resultSet.getInt("COLUMN_SIZE");

                System.out.println("Column Name: " + columnName + ", Type: " + columnType + ", Size: " + columnSize);
            }
            
        }catch(SQLException ex){
            System.out.println("exception");
        }
    }
    public void testColumn (String table){
        
        try { Statement st = conn.createStatement();
            
        ResultSet resultSet = queryDB("SELECT * FROM " + table);{
            
            
            System.out.println("Columns in "+ table +" table");
            while(resultSet.next()){
                
                int id = resultSet.getInt("ID");
                String firstName = resultSet.getString("FIRSTNAME");
                String lastName = resultSet.getString("LASTNAME");
                String USERNAME = resultSet.getString("USERNAME");
               
                
                
                System.out.println("ID: "+ id + ", First Name :"+ firstName + ", Last Name :"+ lastName +", Major :"+ USERNAME);
            }
            
        }
            
        }catch(SQLException ex){
        
    }
               
    } public Object[][] getStudentsObject(){
        
        ArrayList<Student> studentList = new ArrayList<>();
        
        try { String query = "SELECT * FROM STUDENT";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                String major = rs.getString("major");
                char gender = rs.getString("gender").charAt(0);
                 Student student = new Student(id,firstName,lastName,email,major,gender);
           studentList.add(student);
            }
        
        
        }catch(SQLException ex){
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
  }
    public ArrayList getStudentsList(){
          ArrayList<Student> studentList = new ArrayList<>();
        
        try { String query = "SELECT * FROM STUDENT";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                String major = rs.getString("major");
                char gender = rs.getString("gender").charAt(0);
                 Student student = new Student(id,firstName,lastName,email,major,gender);
           studentList.add(student);
            }
        
        
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return studentList;
    }
    public ArrayList getStaffList(){
          ArrayList<Staff>  staffList= new ArrayList<>();
        
        try { String query = "SELECT * FROM STAFF";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String username = rs.getString("username");
                String password = rs.getString("password");
              
                  System.out.println("Staff Details: ID: " + id + 
                       ", First Name: " + firstName + 
                       ", Last Name: " + lastName + 
                       ", Username: " + username);
                 Staff staff = new Staff(id,firstName,lastName,username,password);
           staffList.add(staff);
            }
        
        
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return staffList;
    }
}
