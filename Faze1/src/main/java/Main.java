import model.User;
import model.UserDatabase;
import view.EnterMenu;
import view.MainMenu;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        UserDatabase.loadUsers();
        if (UserDatabase.getUsers().size() > 0) {
            for (User user : UserDatabase.getUsers()) {
                if (user.isStayLoggedIn()) {
                    UserDatabase.setCurrentUser(user);
                    user.setStayLoggedIn(false);// ?????
                    MainMenu menu = new MainMenu();
                    menu.run(scanner);
                }
            }
        }
        EnterMenu menu = new EnterMenu();
        menu.run(scanner);
        UserDatabase.saveUsers();
    }

    public static Scanner scanner = new Scanner(System.in);
}