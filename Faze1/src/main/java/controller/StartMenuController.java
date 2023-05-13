package controller;

import controller.GameController.GameMenuController;
import model.Game;
import model.User;
import model.UserDatabase;

import java.util.ArrayList;

public class StartMenuController {
    public String addPlayer (String username) {
        if (!doesPlayerExist(username)) {
            return "No player with this username exists";
        }
        for (User user : UserDatabase.getPlayers()) {
            if (user.getUsername().equals(username)) {
                return "Player is already added";
            }
        }
        UserDatabase.getPlayers().add(UserDatabase.getUserByUsername(username));
        return "Player successfully added";
    }
    public String removePlayer (String username) {
        if (!doesPlayerExist(username)) {
            return "No player with this username exists";
        }
        for (User user: UserDatabase.getPlayers()) {
            if (user.getUsername().equals(username)) {
                UserDatabase.getPlayers().remove(user);
                return "Player removed successfully";
            }
        }
        return "The player with this username isn't added";
    }
    public String removeAllPlayers () {
        UserDatabase.getPlayers().clear();
        return "All Players removed successfully";
    }
    public String addRandomPlayers (String number) {
        int numberOfPLayersToAdd;
        if (number.equals("random")) {
            int maximumPossibleToAdd = 8 - UserDatabase.getPlayers().size();
            numberOfPLayersToAdd = (int)(Math.random() * maximumPossibleToAdd);
        }
        else if (number.length() == 1 && number.toCharArray()[0] >= '0' && number.toCharArray()[0] <= '9'){
            numberOfPLayersToAdd = Integer.parseInt(number);
        }
        else {
            return "Incorrect number of players";
        }
        if (numberOfPLayersToAdd > 8 - UserDatabase.getPlayers().size()) {
            return "Incorrect number of players";
        }
        int numberOfUsers = UserDatabase.getPlayers().size();
        ArrayList <Integer> indexesOfPlayersToAdd = new ArrayList<>();
        for (int i = 0; i < numberOfPLayersToAdd; i++) {
            int newNumber = (int) (Math.random() * numberOfUsers);
            if (indexesOfPlayersToAdd.get(newNumber) != null) {
                indexesOfPlayersToAdd.add(newNumber);
            }
        }
        int cnt = 0;
        for (int i = 0; i < numberOfUsers; i++) {
            if (i == indexesOfPlayersToAdd.get(cnt)) {
                UserDatabase.getPlayers().add(UserDatabase.getUsers().get(i));
            }
            cnt ++;
        }
        return "Players successfully added";
    }
    public String chooseMap (String number) {
        if (number.equals("random")) {
            // TODO: add a random map
        }
        else {
            int num = Integer.parseInt(number);
        }
        return "";
    }
    private boolean doesPlayerExist (String username) {
        if (UserDatabase.getUserByUsername(username) == null) {
            return false;
        }
        return true;
    }

    public void startNewGame (){
        Game newGame = new Game();

    }
}
