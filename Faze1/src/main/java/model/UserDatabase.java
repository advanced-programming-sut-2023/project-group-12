package model;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

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

    private static final String DATA_BASE = "database.json";
    private static final Gson gson = new Gson();
    private static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getPlayers() {
        return players;
    }

    private static ArrayList<User> players = new ArrayList<>();// should be cleared after each game

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static int numberOfUsers() {
        return users.size();
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static boolean isEmailExists(String email) {
        for (User user : users) {
            if (user.getEmail().toUpperCase().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static void addUser(User userRegister) {
        users.add(userRegister);
    }
    public static void loadUsers() {
        try {
            File file = new File(DATA_BASE);
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                String fileContent = fileScanner.useDelimiter("\\Z").next();
                fileScanner.close();
                User[] userArray = gson.fromJson(fileContent, User[].class);
                users = new ArrayList<User>();
                for (User user : userArray) {
                    users.add(user);
                }
            } else {
                users = new ArrayList<User>();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveUsers() {
        try {
            FileWriter writer = new FileWriter(DATA_BASE);
            String json = gson.toJson(users.toArray());
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean existsEmail (String email) {
        for (User user:users) {
            if (user.getEmail().toUpperCase().equals(email.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}

