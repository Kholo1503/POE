/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_part_3;

import javax.swing.JOptionPane;


/**
 *
 * @author Kholiswa
 */

public class KanbanApp {
    public static void main(String[] args) {
        // Initialize Login and TaskManager classes
        Login login = new Login();
        TaskManager taskManager = new TaskManager();

        // Handle user registration
        if (!handleRegistration(login)) {
            exitProgram(); // Exit program if registration fails or is canceled
        }

        // Handle user login
        if (!handleLogin(login)) {
            exitProgram(); // Exit program if login fails or is canceled
        }

        // Main menu loop
        while (true) {
            // Define main menu options
            String[] mainOptions = {"Add Task", "View Reports", "Exit"};
            int mainChoice = JOptionPane.showOptionDialog(null, "Choose an option:", "Main Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, mainOptions, mainOptions[0]);

            // Handle user's menu choice
            if (mainChoice == 0) {
                addTasks(taskManager); // Add tasks
            } else if (mainChoice == 1) {
                showReports(taskManager); // View reports
            } else {
                // Exit program
                JOptionPane.showMessageDialog(null, "Thank you for using EasyKanban!");
                System.exit(0);
            }
        }
    }

    // Handles user registration process
    private static boolean handleRegistration(Login login) {
        while (true) {
            // Prompt for user details
            String firstName = JOptionPane.showInputDialog("Enter your first name:");
            if (firstName == null) return false; // Exit if user cancels

            String lastName = JOptionPane.showInputDialog("Enter your last name:");
            if (lastName == null) return false; // Exit if user cancels

            String username = JOptionPane.showInputDialog("Enter a username (max 5 characters, with '_'):");
            if (username == null) return false; // Exit if user cancels

            String password = JOptionPane.showInputDialog("Enter a password (min 8 characters, with special characters):");
            if (password == null) return false; // Exit if user cancels

            // Attempt registration
            String result = login.registerUser(username, password, firstName, lastName);
            JOptionPane.showMessageDialog(null, result); // Display registration result
            if (result.contains("successfully")) return true; // Proceed if successful
        }
    }

    // Handles user login process
    private static boolean handleLogin(Login login) {
        while (true) {
            // Prompt for login credentials
            String username = JOptionPane.showInputDialog("Enter your username to log in:");
            if (username == null) return false; // Exit if user cancels

            String password = JOptionPane.showInputDialog("Enter your password:");
            if (password == null) return false; // Exit if user cancels

            // Attempt login
            String loginStatus = login.returnLoginStatus(username, password);
            JOptionPane.showMessageDialog(null, loginStatus); // Display login status
            if (login.loginUser(username, password)) return true; // Proceed if successful
        }
    }

    // Handles the addition of tasks
    private static void addTasks(TaskManager taskManager) {
        int numTasks;
        try {
            // Prompt user for number of tasks
            numTasks = Integer.parseInt(JOptionPane.showInputDialog("How many tasks would you like to add?"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number. Please try again.");
            return;
        }

        // Loop to add each task
        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter Task Name:");
            if (taskName == null) return; // Exit if user cancels

            String taskDescription = JOptionPane.showInputDialog("Enter Task Description (Max 50 characters):");
            if (taskDescription == null || taskDescription.length() > 50) {
                JOptionPane.showMessageDialog(null, "Invalid or too long task description. Try again.");
                continue;
            }

            String developerDetails = JOptionPane.showInputDialog("Enter Developer Details:");
            if (developerDetails == null) return; // Exit if user cancels

            // Select task status
            String[] statuses = {"To Do", "Doing", "Done"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, "Select Task Status:",
                    "Task Status", JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);
            if (taskStatus == null) return; // Exit if user cancels

            int taskDuration;
            try {
                // Prompt for task duration
                taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration (hours):"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid duration. Try again.");
                continue;
            }

            // Create and add the task
            Task task = new Task();
            task.setTaskDetails(taskName, taskDescription, developerDetails, taskDuration, taskStatus,
                    taskManager.displayAllTasks().split("\n").length); // Set task ID based on task count
            taskManager.addTask(task); // Add task to task manager
            JOptionPane.showMessageDialog(null, "Task added successfully!");
        }
    }

    // Displays various reports related to tasks
    private static void showReports(TaskManager taskManager) {
        String[] reportOptions = {"All Tasks", "Completed Tasks", "Longest Task", "Search by Name", 
                                  "Search by Developer", "Delete Task", "Back"};
        boolean back = false;

        // Loop to show reports until the user decides to go back
        while (!back) {
            int choice = JOptionPane.showOptionDialog(null, "Choose a report option:", "Reports Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, reportOptions, reportOptions[0]);

            // Handle user's report choice
            switch (choice) {
                case 0 -> JOptionPane.showMessageDialog(null, taskManager.displayAllTasks()); // Display all tasks
                case 1 -> JOptionPane.showMessageDialog(null, taskManager.displayCompletedTasks()); // Display completed tasks
                case 2 -> JOptionPane.showMessageDialog(null, taskManager.displayLongestTask()); // Display the longest task
                case 3 -> { // Search for a task by name
                    String taskName = JOptionPane.showInputDialog("Enter Task Name to search:");
                    JOptionPane.showMessageDialog(null, taskManager.searchTaskByName(taskName));
                }
                case 4 -> { // Search for tasks by developer
                    String developer = JOptionPane.showInputDialog("Enter Developer's Name to search:");
                    JOptionPane.showMessageDialog(null, taskManager.searchTasksByDeveloper(developer));
                }
                case 5 -> { // Delete a task by name
                    String taskName = JOptionPane.showInputDialog("Enter Task Name to delete:");
                    JOptionPane.showMessageDialog(null, taskManager.deleteTaskByName(taskName));
                }
                default -> back = true; // Go back to the main menu
            }
        }
    }

    // Exits the program with a goodbye message
    private static void exitProgram() {
        JOptionPane.showMessageDialog(null, "Exiting... Goodbye!");
        System.exit(0);
    }
}
