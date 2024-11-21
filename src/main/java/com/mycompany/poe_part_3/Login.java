/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poe_part_3;

/**
 *
 * @author south
 */

public class Login {
    private String username;  // Stores the registered username
    private String password;  // Stores the registered password
    private String firstName; // Stores the first name of the user
    private String lastName;  // Stores the last name of the user

    // Validates if the username contains an underscore and is no more than 5 characters
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Validates password complexity: length, uppercase letter, number, and special character
    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&               // Minimum 8 characters
               password.matches(".*[A-Z].*") &&        // Contains at least one uppercase letter
               password.matches(".*[0-9].*") &&        // Contains at least one number
               password.matches(".*[!@#$%^&*()].*");   // Contains at least one special character
    }

    // Registers the user by validating the username and password
    public String registerUser(String username, String password, String firstName, String lastName) {
        if (!checkUserName(username)) {
            // Error message for invalid username format
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            // Error message for invalid password complexity
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        // Save user details if both username and password are valid
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        return "User successfully registered!";
    }

    // Verifies if the provided login credentials match the registered user's details
    public boolean loginUser(String username, String password) {
        return this.username != null &&               // Ensure a user is registered
               this.username.equals(username) &&      // Username matches
               this.password.equals(password);        // Password matches
    }

    // Returns an appropriate login message based on the login attempt
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            // Successful login message
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            // Login failed message
            return "Username or password incorrect, please try again.";
        }
    }
}
