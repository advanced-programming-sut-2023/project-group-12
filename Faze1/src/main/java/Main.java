import java.io.FilterOutputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            Captcha captcha = new Captcha();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < captcha.getCreatedCaptcha().length; j++) {
                    System.out.print(captcha.getCreatedCaptcha()[j][i]);
                }
                System.out.println();
            }
            System.out.println("type in the captcha");
            String string = scanner.nextLine();
            int A = 0;
            for (int i = 0; i < captcha.getCreatedCaptcha().length; i++) {
                if (string.charAt(i) - '0' != captcha.getNumbers()[i]) {
                    System.out.println("incorrect captcha");
                    A++;
                    break;
                }
            }
            if (A == 0) {
                System.out.println("correct you can continue");
            }
        }
    }
}