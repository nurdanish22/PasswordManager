package PasswordManager;

import java.util.InputMismatchException; // This is to catch invalid input
import java.util.Scanner; // This is to get user input

import PasswordManager.PasswordEntry.ValidationResult; // This is to use the ValidationResult class

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter a master password: ");
        String masterpassword = input.nextLine(); // Correct the variable name and use nextLine()

        PasswordManager passwordManager = new PasswordManager(masterpassword);

        while (true) { // Main Menu that runs as long as the user does not exit the program. Nurdanish (2224875)
            System.out.println("1. Add a password");
            System.out.println("2. Retrieve a password");
            System.out.println("3. Update a password");
            System.out.println("4. Exit");

            int choice = 0;
            try {
                choice = input.nextInt(); // Consume the newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
                input.nextLine(); // Consume the invalid input
                continue;
            }
            input.nextLine();

            switch (choice) {
                case 1:
                    // Add a password. Afiq (2228775) and Nurdanish (2224875)
                    String url, login, password, notes;
                    PasswordEntry entry;
                    System.out.print("Enter the URL of the website: ");
                    url = input.nextLine();
                    System.out.print("Enter the login or username for the website: ");
                    login = input.nextLine();
                    System.out.print("Enter the password for the website: ");
                    password = input.nextLine();
                    ValidationResult result = PasswordEntry.validatePassword(password); // Initialize the result variable

                    while (!result.isValid()) {
                        // Validate the password before adding it
                        System.out.println("Invalid password: " + result.getMessage()); // Display the error message
                        System.out.print("Enter the password for the website: ");
                        password = input.nextLine();
                        try {
                            result = PasswordEntry.validatePassword(password); // Validate the password again
                        } catch (IllegalArgumentException e) {
                            // Catch the exception and display an error message
                            System.out.println("Invalid password: " + e.getMessage());
                        }
                    }

                    System.out.print("Enter any notes for the website (optional): ");
                    notes = input.nextLine();
                    entry = new PasswordEntry(url, login, password, notes);
                    passwordManager.addEntry(entry);
                    System.out.println("Password entry added successfully.");
                    break;

                case 2:
                    // Retrieve a password. Afiq (2228775) 
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
                    // Update an existing password. Afiq (2228775) and Nurdanish (2224875)
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
                            System.out.print("Would you like to add your own password or generate a random password? (add/generate): ");//Izzuddin (2226833) 
                            answer = input.nextLine();
                            if (answer.equalsIgnoreCase("add")) {
                                System.out.print("Enter the new password for the website: ");//Add the user's own password that the user wants to add. Izzuddin (2226833)
                                password = input.nextLine();
                                result = PasswordEntry.validatePassword(password); // Initialize the result variable

                                while (!result.isValid()) {
                                    // Validate the password before adding it
                                    System.out.println("Invalid password: " + result.getMessage()); // Display the error message
                                    System.out.print("Enter the password for the website: ");
                                    password = input.nextLine();
                                    try {
                                        result = PasswordEntry.validatePassword(password); // Validate the password again
                                    } catch (IllegalArgumentException e) {
                                        // Catch the exception and display an error message
                                        System.out.println("Invalid password: " + e.getMessage());
                                    }
                                }
                            } else if (answer.equalsIgnoreCase("generate")) { //Generate a random password. Izzuddin (2226833)
                                PasswordGenerator generator = new PasswordGenerator();
                                password = generator.generatePassword();
                                System.out.println("The generated password is: " + password);
                                System.out.println("Would you like to use this password? (y/n)");
                                answer = input.nextLine();
                                if (answer.equalsIgnoreCase("y")) {
                                    // Update the password
                                    entry.setPassword(password);
                                    
                                    System.out.println("Password updated successfully.");
                                    break;
                                } else if (answer.equalsIgnoreCase("n")) {
                                    System.out.println("No changes made to the password entry.");
                                    break;
                                } else {
                                    System.out.println("Invalid choice. Please enter y or n.");

                                    break;
                                }
                            } else if (answer.equalsIgnoreCase("n")) {
                                System.out.println("No changes made to the password entry.");
                                break;
                            } else {
                                System.out.println("Invalid choice. Please enter add or generate.");
                                break;
                            }
                            System.out.println("Password updated successfully.");
                        } else {
                            System.out.println("No changes made to the password entry.");
                        }
                    } else {
                        System.out.println("No password entry found for " + url + ".");
                    }
                    break;

                case 4: // Exit the program. Nurdanish (2224875)
                    System.out.println("Exiting...");
                    input.close();
                    System.exit(0);
                default: // If the user enters an invalid choice, display an error message. Nurdanish (2224875)
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }
    }
}