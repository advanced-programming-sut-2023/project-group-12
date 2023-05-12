import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestClass {
    @Test
    public void testAssertEquals () {
        User user1 = new User("Matin", "1234", "matin", "matin", "matin");
        User user2 = new User("Matin", "1234", "matin", "matin", "matin");
        Assertions.assertEquals(user1, user2);
    }
}
