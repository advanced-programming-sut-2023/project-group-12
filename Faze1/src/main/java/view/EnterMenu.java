package view;

import model.User;
import model.UserDatabase;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class EnterMenu {
    public void run(Scanner scanner) {
        String input;
        System.out.println("Do you already have an account?\n(Please type in exit if you want to end the program or answer with \"register now\" or \"login now\")");
        while (true) {
            input = scanner.nextLine();
            if (input.equals("login now")) {
                LoginMenu menu = new LoginMenu();
                System.out.println("Please enter your username and password to login");
                try {
                    menu.run(scanner);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Welcome back to enter menu!");
            }
            else if (input.equals("register now")) {
                RegisterMenu menu = new RegisterMenu();
                System.out.println("Please enter your information to register");
                menu.run(scanner);
                UserDatabase.saveUsers();
                System.out.println("Welcome back to enter menu!");
            }
            else if (input.equals("exit")) {
                System.out.println("Thanks for being with us!");
                return;
            }
            else {
                System.out.println("Please try again!");
            }
        }
    }
}
