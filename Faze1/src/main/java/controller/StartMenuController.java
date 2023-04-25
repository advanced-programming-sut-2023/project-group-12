package controller;

import model.User;

import java.util.ArrayList;

public class StartMenuController {
    public String addPlayer (String username) {
        return "";
    }
    public String removePlayer (String username) {
        return "";
    }
    public String removeAllPlayers () {
        return "";
    }
    public String addRandomPlayers (String number) {
        if (number.equals("random")) {
            // TODO: add a random number of random players
        }
        else {
            int count = Integer.parseInt(number);
        }
        return "";
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
}
