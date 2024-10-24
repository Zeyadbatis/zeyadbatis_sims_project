/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author zeyad
 */
public class Staff extends Person{
    
   //atributes for staff
    private String username;
    private String password;
    private int id;
    
    //class constructor
    public Staff(int id,String firstName,String lastName,String username, String password){
        
        super(firstName,lastName);
        this.id = id;
        this.username = username;
        this.password = password;
    }

    //Geters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
  
}
