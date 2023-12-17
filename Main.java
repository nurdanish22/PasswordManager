package PasswordManager;

import java.util.Scanner;

import PasswordManager.PasswordEntry.ValidationResult;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a master password: ");
        String masterpassword = input.nextLine(); // Correct the variable name and use nextLine()

        PasswordManager passwordManager = new PasswordManager(masterpassword);

        while (true) {
            System.out.println("1. Add a password");
            System.out.println("2. Retrieve a password");
            System.out.println("3. Update a password");
            System.out.println("4. Exit");

            int choice = input.nextInt();
            input.nextLine(); // Consume the newline character

           

            switch (choice) {
                 
                case 1:
                // Add a password
                String url, login, password, notes;
                PasswordEntry entry;
                System.out.print("Enter the URL of the website: ");
                url = input.nextLine(); 
                System.out.print("Enter the login or username for the website: ");
                login = input.nextLine(); 
                System.out.print("Enter the password for the website: ");
                password = input.nextLine(); 
                ValidationResult result = PasswordEntry.validatePassword(password); // Validate the password before adding it
                if (result.isValid()) {
                    // Add the password
                    System.out.print("Enter any notes for the website (optional): ");
                    notes = input.nextLine(); 
                    entry = new PasswordEntry(url, login, password, notes); 
                    passwordManager.addEntry(entry); 
                    System.out.println("Password entry added successfully."); 
                } else {
                    System.out.println("Invalid password: " + result.getMessage()); // Display the error message
                }
                break;

                case 2:
                // Retrieve a password //catto
                System.out.print("Enter the URL of the website: ");
                url = input.nextLine(); 
                entry = passwordManager.getEntry(url); 
                if (entry != null) {
                    // Check if the entry exists
                    System.out.println("The password entry for " + url + " is: ");
                    System.out.println(entry.display()); 
                } else {
                    System.out.println("No password entry found for " + url + ".");
                }
                break;

                case 3:
                // Update a password
                System.out.print("Enter the URL of the website: ");
                url = input.nextLine(); 
                entry = passwordManager.getEntry(url); 
                if (entry != null) {
                    // Check if the entry exists
                    System.out.println("The password entry for " + url + " is: ");
                    System.out.println(entry.display()); 
                    System.out.println("Do you want to update the password for this entry? (y/n)");
                    String answer = input.nextLine(); 
                    if (answer.equalsIgnoreCase("y")) {
                        // Update the password
                        System.out.print("Enter the new password for the website: ");
                        password = input.nextLine(); 
                        entry.setPassword(password); 
                        System.out.println("Password entry updated successfully.");
                    } else {
                        System.out.println("No changes made to the password entry.");
                    }
                } else {
                    System.out.println("No password entry found for " + url + ".");
                }
                break;
                
                case 4:
                System.out.println("Exiting...");
                System.exit(0);
                default:
                System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }
    }
}
