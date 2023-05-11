import model.Captcha;
<<<<<<< HEAD
import model.User;
import model.UserDatabase;
import view.EnterMenu;
import view.MainMenu;
=======
import view.EnterMenu;
>>>>>>> 1be4b8ce9178cdfc65e07f37e9b0d38423afa56a

import java.util.Scanner;
/* TODO list :
    handle empty fields
    1. Register menu :
    save user info in json file
    2. Login menu :

*/

public class Main {
    public static void main(String[] args) {
<<<<<<< HEAD
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
=======
//        while (true) {
//            Captcha captcha = new Captcha();
//            for (int i = 0; i < 8; i++) {
//                for (int j = 0; j < captcha.getCreatedCaptcha().length; j++) {
//                    System.out.print(captcha.getCreatedCaptcha()[j][i]);
//                }
//                System.out.println();
//            }
//            System.out.println("type in the captcha");
//            String string = scanner.nextLine();
//            int A = 0;
//            for (int i = 0; i < captcha.getCreatedCaptcha().length; i++) {
//                if (string.charAt(i) - '0' != captcha.getNumbers()[i]) {
//                    System.out.println("incorrect captcha");
//                    A++;
//                    break;
//                }
//            }
//            if (A == 0) {
//                System.out.println("correct you can continue");
//            }
//        }
        EnterMenu menu = new EnterMenu();
        menu.run(scanner);
>>>>>>> 1be4b8ce9178cdfc65e07f37e9b0d38423afa56a
    }
    public static Scanner scanner = new Scanner(System.in);
}