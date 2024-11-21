/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_part_3;

/**
 *
 * @author south
 */

public class Task {
    // Private fields to encapsulate task properties
    private String taskName;           // Name of the task
    private int taskNumber;            // Unique number for the task
    private String taskDescription;    // Brief description of the task
    private String developerDetails;   // Details of the developer assigned to the task
    private int taskDuration;          // Duration of the task in hours
    private String taskID;             // Unique ID for the task
    private String taskStatus;         // Status of the task (e.g., "To Do", "Doing", "Done")

    // Validates that the task description is 50 characters or fewer
    public boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }

    // Generates a unique Task ID in the format: First 2 letters of task name + ":" + task number + ":" + Last 3 letters of developer name
    public String createTaskID(String taskName, int taskNumber, String developerDetails) {
        String taskNamePart = taskName.substring(0, 2).toUpperCase(); // First 2 letters of task name, uppercase
        String developerPart = developerDetails.substring(developerDetails.length() - 3).toUpperCase(); // Last 3 letters of developer's name, uppercase
        return taskNamePart + ":" + taskNumber + ":" + developerPart;
    }

    // Sets all task details and generates the Task ID
    public void setTaskDetails(String taskName, String taskDescription, String developerDetails,
                                int taskDuration, String taskStatus, int taskNumber) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskNumber = taskNumber;
        this.taskID = createTaskID(taskName, taskNumber, developerDetails); // Create and assign Task ID
    }

    // Prints all task details in a readable format
    public String printTaskDetails() {
        return "Task Status: " + taskStatus +
               "\nDeveloper Details: " + developerDetails +
               "\nTask Number: " + taskNumber +
               "\nTask Name: " + taskName +
               "\nTask Description: " + taskDescription +
               "\nTask ID: " + taskID +
               "\nDuration: " + taskDuration + " hours";
    }

    // Getter for task duration
    public int getTaskDuration() {
        return taskDuration;
    }

    // Getter for task status
    public String getTaskStatus() {
        return taskStatus;
    }

    // Getter for task name
    public String getTaskName() {
        return taskName;
    }

    // Getter for developer details
    public String getDeveloperDetails() {
        return developerDetails;
    }
}
