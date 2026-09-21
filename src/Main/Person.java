/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;


/**
 *
 * @author zeyad
 */
abstract class Person {
    
    // Person attributes 
      String firstName;
      String lastName;
            
    //Constructor to initialize attributes
           public Person(String firstName, String lastName){
               this.firstName = firstName;
               this.lastName  = lastName;
           }

    //   setters and getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
           
           
           
}
