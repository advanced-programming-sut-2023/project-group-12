import Commands.RegisterMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class CheckShit {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String newLine;
        if(input.charAt(0) == '\"'){
            newLine = input.substring(1, input.length() - 1);
        }
        else {
            newLine = input;
        }
        System.out.println(newLine);

    }
}
