/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part1poe;
import java.util.regex.Pattern;
/**
 *
 * @author RC_Student_lab
 */
class Login {
    
    //declaration 
    private String name;
    private String surname;
    private String Username;
    private String Password;
    
    //Check username whether correct/incorrect
    public boolean CheckUsername(String username){
    //temp variable for checking
    boolean succeeded = false;
    //checking if username is captured or not
    if(username.contains("_")&& username.length() <= 5){
        //prompt
        System.out.println("Username has been captured.");
        //then assign to true
        succeeded = true;
    }else{
        succeeded = false;
        //prompt
        System.out.println("Username has not been captured, please"
                + "ensure that your username contains an underscore "
                + "and is no more than 5 characters in length.");  
        //changes to false
        return  false;
    }
    return succeeded;
}

    //Checking Password whether correct/incorrect
    public boolean checkPasswordComplexity(String password) {
    //pattern regex
    Pattern check_num = Pattern.compile("[0123456789]");
    Pattern check_specials = Pattern.compile("[*-`~!@$%&_'^]");
    Pattern check_Upper = Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZ]");
    
    //temp variable Found
    boolean Found = false;
    
    // checking whether password is correct/incorrect
    if( check_num.matcher(password).find() && 
            check_specials.matcher(password).find() && 
            check_Upper.matcher(password).find() 
            && password.length() >= 8){
    //then its assigned to true
    Found = true;
    //message feeback for if Password captured or not
    System.out.println("Password successfully captured");
    return true;
    }else{
    //changed to false
    Found = false;
    //message
    System.out.println("Password is not correctly formatted, please ensure "
            + "that password contains at least 8 characters, a capital letter,"
            + " a number and a special character.");
   // System.out.println("Username or password incorrect, please try again"); 
       return false;
     }
    }
    
public String registerUser( String Username, String Password, String name, String surname){
        this.name = name;
        this.surname = surname;
        if(!CheckUsername(Username)){
          return "Username incorrectly formatted.";
        }
        else if(!checkPasswordComplexity(Password)){
           return "The password does not meet the complexity.";
        }else{
            this.Username = Username;
            this.Password = Password;
        return "The user has been registered!";
        }
      }

//Verify login details
public Boolean loginUser(String username, String password){
    //check if username and password match the stored
    return this.Username.equals(username) && this.Password.equals(password);
}

//Return login status
public String LoginStatus(String username, String password){
    if(loginUser(username, password)){
        return "Welcome " + name + " " + surname + ", its great to see you again.";
    }else{
        return "Username or password incorrect, please try again.";
    }
}
}