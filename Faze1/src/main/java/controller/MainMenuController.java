package controller;

import model.UserDatabase;

public class MainMenuController {
    public void removeUserLoggedIn() {
        UserDatabase.getCurrentUser().setStayLoggedIn(false);
    }
}
