package controller;

import model.User;
import model.UserDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;


public class RegisterMenuController {
    private User userRegister;


    public String register(String username, String password, String passwordConfirmation, String email, String nickname, String slogan) {
        email = email.toUpperCase();
        if (!isCorrectUsername(username)) {
            return "invalid username format!";
        }
        if (UserDatabase.getUserByUsername(username) != null) {
            return "A player with this username already exist! but you might find these interesting:\n";
        }
        if (isPasswordWeak(password)) {
            return "password is weak!";
        }
        if (!password.equals(passwordConfirmation)) {
            return "password confirmation does not equal password!";
        }
        if (UserDatabase.isEmailExists(email)) {
            return "exist player with this email!";
        }
        if (!isEmailFormatCorrect(email)) {
            return "email format is incorrect!";
        }

        userRegister = new User(username, password, email, nickname, slogan);
        return "register successfully!";
    }

    public String showQuestions() {
        String output = "Please select your security question and answer it.\n";
        for (int i = 0; i < 3; i++) {
            output += (i + 1) + ". " + UserDatabase.getQuestions().get(i) + "\n";
        }
        return output;
    }

    public String securityQuestionErrors(int number, String answer, String answerConfirm) {
        if (number > 3) {
            return "incorrect number question!";
        }
        if (!answer.equals(answerConfirm)) {
            return "answer confirmation does not equal answer!";
        }
        return "question and answer selected!";
    }

    public void addUser(String answer, int questionNumber) {
        userRegister.setAnswer(answer);
        userRegister.setQuestion(questionNumber);
        UserDatabase.addUser(userRegister);
    }

    public static boolean isPasswordWeak(String password) {
        Pattern capitalLetterPattern = Pattern.compile("[A-Z]");
        Pattern smallLetterPattern = Pattern.compile("[a-z]");
        Pattern digitPattern = Pattern.compile("[0-9]");
        Pattern elseCharacterPattern = Pattern.compile("[^a-zA-Z0-9]");
        if (password.length() < 6 ||
                !capitalLetterPattern.matcher(password).find() ||
                !smallLetterPattern.matcher(password).find() ||
                !digitPattern.matcher(password).find() ||
                !elseCharacterPattern.matcher(password).find()) {
            return true;
        }
        return false;
    }

    public static boolean isCorrectUsername(String username) {
        String usernameValid = "^[a-zA-Z0-9|\\_]+$";
        return Pattern.matches(usernameValid, username);
    }

    private boolean isEmailFormatCorrect(String email) {
        String emailValid = "^[A-Z0-9_.]+@[A-Z0-9_]+\\.[A-Z0-9_.]+$";
        return Pattern.matches(emailValid, email);
    }

    public static String generateRandomPassword() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        Collections.shuffle(list);
        ArrayList<Character> characters = new ArrayList<>();
        String allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789!@#$%^&'()*+,-./:;<=>?@[]`~{}\'\"\\|";
        String allCapitalCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String allSmallCharacters = "abcdefghijklmnopqrstuvxyz";
        String allNumberCharacters = "0123456789";
        String allElseCharacter = "!@#$%^&'()*+,-./:;<=>?@[]`~{}\"\\|";
        StringBuilder sb = new StringBuilder(7);
        int ch;
        ch = (int) (allCapitalCharacters.length() * Math.random());
        characters.add(allCapitalCharacters.charAt(ch));
        ch = (int) (allSmallCharacters.length() * Math.random());
        characters.add(allSmallCharacters.charAt(ch));
        ch = (int) (allNumberCharacters.length() * Math.random());
        characters.add(allNumberCharacters.charAt(ch));
        ch = (int) (allElseCharacter.length() * Math.random());
        characters.add(allElseCharacter.charAt(ch));
        for (int i = 0; i < 3; i++) {
            ch = (int) (allCharacters.length() * Math.random());
            characters.add(allCharacters.charAt(ch));
        }
        for (Integer i : list) {
            sb.append(characters.get(i));
        }
        return sb.toString();
    }
}
