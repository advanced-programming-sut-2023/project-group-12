import model.Captcha;
import model.User;
import model.UserDatabase;
import view.EnterMenu;
import view.MainMenu;

import java.util.Scanner;
/* TODO list :
    handle empty fields
    1. Register menu :
    save user info in json file
    2. Login menu :

*/

public class Main {
    public static void main(String[] args) {
        if (UserDatabase.getUsers().size() > 0) {
            UserDatabase.loadUsers();
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