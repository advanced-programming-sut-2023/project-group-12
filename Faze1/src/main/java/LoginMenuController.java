import java.util.regex.Matcher;

public class LoginMenuController {
    public String login (Matcher matcher) {
        if (true){
            //user doesn't exist
            return "Username and password didn’t match!";
        }
        if (true) {
            // wrong password
            //TODO: delay the program for n * 5 seconds
            return "Username and password didn’t match!";
        }
        return "now you must answer the captcha";
    }
    public String isCaptchaCorrect (Captcha captcha, String input) {
        for (int i = 0; i < captcha.getNumbers().length; i++) {
            if (input.charAt(i) - '0' != captcha.getNumbers()[i]) {
                return "incorrect captcha";
            }
        }
        return "user logged out successfully!";
    }
    public String forgotMyPassword (Matcher matcher) {
        System.out.println(/* the user's security question*/);
        String input = Main.scanner.nextLine();
        if (input.equals(""/* the user's answer*/)) {
            // now they can set their new password exactly as is done in profile menu
        }
        return "Your answer is incorrect, please try again";
    }

}
