package view;

import java.util.Scanner;

public class EnterMenu {
    public void run(Scanner scanner) {
        // if stay logged in login that user
        String input;
        System.out.println("Do you already have an account?\n(Please type in exit if you want to end the program or answer with \"register now\" or \"login now\")");
        while (true) {
            input = scanner.nextLine();
            if (input.equals("login now")) {
                LoginMenu menu = new LoginMenu();
                System.out.println("Please enter your username and password to login");
                menu.run(scanner);
                System.out.println("Welcome back to enter menu!");
            }
            else if (input.equals("register now")) {
                RegisterMenu menu = new RegisterMenu();
                System.out.println("Please enter your information to register");
                menu.run(scanner);
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
