package model;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDatabase {
    private static User currentUser;
    private static ArrayList<String> questions;

    public static ArrayList<String> getQuestions() {
        return questions;
    }

    static {
        questions = new ArrayList<>();
        questions.add("What is my father’s name?");
        questions.add("What was my first pet’s name?");
        questions.add("What is my mother’s last name?");
    }
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserDatabase.currentUser = currentUser;
    }

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<User> players = new ArrayList<>();// should be cleared after each game

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static int numberOfUsers() {
        return users.size();
    }

    public static User getUserByUsername (String username) {
        for (User user: users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public static boolean isEmailExists (String email) {
        for (User user: users){
            if(user.getUsername().equals(email)){
                return true;
            }
        }
        return false;
    }

    public static void addUser(User userRegister) {
        users.add(userRegister);
    }
}

