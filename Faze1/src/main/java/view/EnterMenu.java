package view;

import controller.EnterMenuController;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class EnterMenu {
    public void run(Scanner scanner) {
        String input;
        EnterMenuController controller = new EnterMenuController();
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
                controller.resetStayLoggedIn();
                System.out.println("Welcome back to enter menu!");
            } else if (input.equals("register now")) {
                RegisterMenu menu = new RegisterMenu();
                System.out.println("Please enter your information to register");
                menu.run(scanner);
                controller.saveUsers();
                System.out.println("Welcome back to enter menu!");
            } else if (input.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for being with us!");
                controller.saveUsers();
                System.exit(0);
            } else {
                System.out.println("Please try again!");
            }
        }
    }
}
