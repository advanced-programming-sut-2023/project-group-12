package model;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
    public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        // Concatenate the salt and password
        byte[] saltedPassword = new byte[salt.length + password.getBytes().length];
        System.arraycopy(salt, 0, saltedPassword, 0, salt.length);
        System.arraycopy(password.getBytes(), 0, saltedPassword, salt.length, password.getBytes().length);

        // Hash the salted password using SHA256
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(saltedPassword);
        byte[] hashedPassword = messageDigest.digest();

        // Convert the hashed password to a hexadecimal string and return it
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashedPassword) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
    public static boolean verifyPassword(String password, String storedHash, byte[] storedSalt) throws NoSuchAlgorithmException {
        // Hash the entered password using the stored salt
        String enteredHash = hashPassword(password, storedSalt);

        // Compare the entered hash to the stored hash
        return enteredHash.equals(storedHash);
    }
}

