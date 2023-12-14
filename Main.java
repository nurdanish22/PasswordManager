package PasswordManager;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a password: ");
        String userPassword = scanner.nextLine;

        Validation result = validatePassword(userPassword);

        if (result.isValid){
            System.out.println("Valid password!");
        }else{
            System.out.println("Invalid password. " + result.getMessage());
        }

    }
}
