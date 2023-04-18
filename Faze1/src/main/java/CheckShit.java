import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CheckShit {

        public static void main(String[] args) {
            String input = "user create -p mypassword -p2 mypassword -n John -s Hello --email john.smith@example.com -u johnsmith";

            // Define the regex pattern
            Pattern pattern = Pattern.compile("^user create (?:.*-u (?<username>\\S+))?(?:.*-p(?<password>\\S+))?(?:.*-p2 (?<password2>\\S+))?(?:.*-n (?<nickname>\\S+))?(?:.*--email (?<email>\\S+))?(?:.*-s (?<slogan>\\S+))?$");

            // Match the input string against the regex pattern
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                // Extract the values of the fields
                String username = matcher.group("username");
                String password = matcher.group("password");
                String nickname = matcher.group("nickname");
                String email = matcher.group("email");
                String slogan = matcher.group("slogan");

                // Print the extracted values
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                System.out.println("Nickname: " + nickname);
                System.out.println("Email: " + email);
                System.out.println("Slogan: " + slogan);
            } else {
                System.out.println("No match found.");
            }
        }
    }
