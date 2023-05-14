import controller.RegisterMenuController;
import model.User;
import controller.ProfileController;
import model.UserDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.w3c.dom.UserDataHandler;

import javax.xml.crypto.Data;

public class ProfileTest {
    static User user;
    static ProfileController profileController;

    @BeforeAll
    public static void starter() throws Exception {
        user = new User("Mohammad", "Davood", "Mammad", "h1309973@gmail.com", "goomba goomba");
        User user2 = new User("Matin", "Mohammad", "Hj.Matin", "matin.mohammadi@gamil.com", "koomba koomba");
        UserDatabase.addUser(user);
        UserDatabase.addUser(user2);
        profileController = new ProfileController(user);
    }

    @Test
    public void changeUsernameTest() throws Exception {
        Assertions.assertEquals("Username can't be empty", profileController.changeUsername(""));
        Assertions.assertEquals("Username <M@mmad> has incorrect format", profileController.changeUsername("M@mmad"));
        Assertions.assertEquals("Player with username <Matin> already exists", profileController.changeUsername("Matin"));
        Assertions.assertEquals("Your username from <Mohammad> changed to <MohammadDF>", profileController.changeUsername("MohammadDF"));
        Assertions.assertEquals("MohammadDF", user.getUsername());
        Assertions.assertEquals("Your username from <MohammadDF> changed to <Mohammad>", profileController.changeUsername("Mohammad"));
    }

    @Test
    public void changeNicknameTest() throws Exception {
        Assertions.assertEquals("Your nickname <Mammad> changed to <MahoneDF> successfully", profileController.changeNickname("MahoneDF"));
        Assertions.assertEquals("MahoneDF", user.getNickname());
        Assertions.assertEquals("Your nickname <MahoneDF> changed to <Mammad> successfully", profileController.changeNickname("Mammad"));
    }

    @Test
    public void changePasswordTest() throws Exception {
        Assertions.assertEquals("Password can't be empty", profileController.changePassword("", "Davood"));
        Assertions.assertEquals("New password can't be empty", profileController.changePassword("Davood", ""));
//        Assertions.assertEquals("Your password <Mahone> is wrong", profileController.changePassword("Mahone", "salam"));
//        Assertions.assertEquals("You should choose a new password", profileController.changePassword("Davood", "Davood"));
//        Assertions.assertEquals(RegisterMenuController.isPasswordWeak("sa"), profileController.changePassword("Davood", "sa"));
//        Assertions.assertEquals("Your password <Davood> changed to <pAssw0r!> successfully", profileController.changePassword("Davood", "pAssw0r!"));

    }


    @Test
    public void changeEmailTest() throws Exception {
        Assertions.assertEquals("Email can't be empty", profileController.changeEmail(""));
        Assertions.assertEquals("Your email <\"h1309973@gmail.com\"> changed to <h1337408davo@gmail.com> successfully", profileController.changeEmail("h1337408davo@gmail.com"));
    }
}
