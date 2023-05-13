package controller;

import model.User;
import model.UserDatabase;
import model.map.Map;

import java.util.ArrayList;

public class StartMenuController {
    public String addPlayer(String username) {
        if (!UserDatabase.getPlayers().contains(UserDatabase.getCurrentUser())) {
            UserDatabase.getPlayers().add(UserDatabase.getCurrentUser());
        }
        if (username.equals(UserDatabase.getCurrentUser().getUsername())) {
            return "You are already added";
        }
        if (!doesPlayerExist(username)) {
            return "No player with this username exists";
        }
        for (User user : UserDatabase.getPlayers()) {
            if (user.getUsername().equals(username)) {
                return "Player is already added";
            }
        }
        if (UserDatabase.getPlayers().size() == UserDatabase.getCurrentMap().getHeadSquares().size())
            return "The map is filled!";
        UserDatabase.getPlayers().add(UserDatabase.getUserByUsername(username));
        return "Player successfully added";
    }

    public String removePlayer(String username) {
        if (!doesPlayerExist(username)) {
            return "No player with this username exists";
        }
        for (User user : UserDatabase.getPlayers()) {
            if (user.getUsername().equals(username)) {
                if (user.getUsername().equals(UserDatabase.getCurrentUser().getUsername())) {
                    return "You can't remove yourself";
                }
                UserDatabase.getPlayers().remove(user);
                return "Player removed successfully";
            }
        }
        return "The player with this username isn't added";
    }

    public String removeAllPlayers() {
        if (UserDatabase.getPlayers().size() == 0) {
            return "There's no player to remove!";
        }
        if (UserDatabase.getPlayers().size() == 1) {
            return "You can't remove yourself";
        }
        UserDatabase.getPlayers().clear();
        UserDatabase.getPlayers().add(UserDatabase.getCurrentUser());
        return "All Players removed successfully";
    }
//    public String addRandomPlayers(String number) {
//        if (!UserDatabase.getPlayers().contains(UserDatabase.getCurrentUser())) {
//            UserDatabase.getPlayers().add(UserDatabase.getCurrentUser());
//        }
//        int numberOfPLayersToAdd;
//        if (number.equals("random")) {
//            int maximumPossibleToAdd = Math.min(7 - UserDatabase.getPlayers().size(), UserDatabase.getUsers().size()-1);
//            numberOfPLayersToAdd = (int) (Math.random() * maximumPossibleToAdd);
//        } else if (number.length() == 1 && number.toCharArray()[0] >= '0' && number.toCharArray()[0] <= '9') {
//            numberOfPLayersToAdd = Integer.parseInt(number);
//            if (numberOfPLayersToAdd >= 8) {
//                return "There must be at most 8 players";
//            }
//            if (numberOfPLayersToAdd > UserDatabase.getUsers().size()-1) {
//                return "there's not enough users!";
//            }
//        } else {
//            return "Incorrect number of players";
//        }
//        int numberOfUsers = UserDatabase.getUsers().size();
//        ArrayList<Integer> indexesOfPlayersToAdd = new ArrayList<>();
//        for (int i = 0; i < numberOfPLayersToAdd; i++) {
//            int newNumber = (int) (Math.random() * (numberOfUsers-1));
//            if (!indexesOfPlayersToAdd.contains(newNumber)) {
//                indexesOfPlayersToAdd.add(newNumber);
//            }
//        }
//        int cnt = 0;
//        for (int i = 0; i < numberOfUsers; i++) {
//            if (i == indexesOfPlayersToAdd.get(cnt)) {
//                UserDatabase.getPlayers().add(UserDatabase.getUsers().get(i));
//            }
//            cnt++;
//        }
//        return "Players successfully added";
//    }

    private boolean doesPlayerExist(String username) {
        if (UserDatabase.getUserByUsername(username) == null) {
            return false;
        }
        return true;
    }
    public String startGame () {
        if (UserDatabase.getPlayers().size() < 2) {
            return "There must be at least 2 players";
        }
        // if there's no map the game can't be started
        return "game started successfully";
    }
}
