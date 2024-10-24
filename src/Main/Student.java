/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zeyad
 */
public class Student extends Person {
    
    //intilize attributes
    private int id;
    private String email;    
    private char gender;
    private String major;
     
    //student constructor
    public Student(int id,String firstName,String lastName,String email,String major,char gender
    ){
        
              super(firstName,lastName);
              this.id = id;
              this.email = email;              
              this.gender = gender;              
              this.major = major;
              

    }
    public Student(){
        super("DefaultFirstName", "DefaultLastName"); 
    this.id = 0;
    this.email = "";
    this.gender = 'U'; 
    this.major = "";
    }

    
    
    //Getters and setters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

   

    public char getGender() {
        return gender;
    }

    

    public String getMajor() {
        return major;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

    public void setGender(char gender) {
        this.gender = gender;
    }

    

    public void setMajor(String major) {
        this.major = major;
    }

    
    
    // toString method
    @Override
    public String toString() {
        return "Student{\n" + "id=" + id + ",\n"
                + " firstName=" + firstName + ",\n"
                + " lastName=" + lastName + ",\n"
                + " email=" + email + ",\n"
                
                + " gender=" + gender + ",\n"
                
                + " major=" + major + ",\n";
                
    }

}
