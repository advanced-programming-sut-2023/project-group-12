package controller;

import model.UserDatabase;

public class EnterMenuController {
    public void resetStayLoggedIn() {
        UserDatabase.getCurrentUser().setStayLoggedIn(false);
    }
    public void saveUsers () {
        UserDatabase.saveUsers();
    }
}
