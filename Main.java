package PasswordManager;

import java.util.Scanner;

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
                break;
                case 2:
                // Retrieve a password
                break;
                case 3:
                // Update a password
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
