package model;

import java.util.ArrayList;

public class UserDatabase {
    private static User currentUser;

    private static ArrayList<User> users;
    private static ArrayList<User> players;// should be cleared after each game
    private static ArrayList<Trade> allTrades;

    public static User getUserByUsername (String username) {
        return null;
    }
    private static Trade getTradeById (String id) {
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}

