import Commands.RegisterMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class CheckShit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Matcher matcher = RegisterMenuCommands.getMatcher(input, RegisterMenuCommands.USER_CREATE);
        if (matcher.find()) {
            System.out.println(matcher.group("username"));
            System.out.println(matcher.group("password"));
            System.out.println(matcher.group("slogan"));
            System.out.println(matcher.group("nickname"));
            System.out.println(matcher.group("email"));
        }
    }
}
