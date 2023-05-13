package controller;

import model.User;
import model.UserDatabase;

public class ProfileController {

    private User currentUser;

    public ProfileController(User currentUser) {
        this.currentUser = currentUser;
    }
    public String changeUsername(String username) {
        if (username.isEmpty()) {
            return "Username can't be empty";
        }
        if (RegisterMenuController.isCorrectUsername(username)) {
            if (UserDatabase.getUserByUsername(username) == null) {
                String oldUsername = currentUser.getUsername();
                currentUser.setUsername(username);
                return "Your username from <" + oldUsername + "> changed to <" + username + ">";
            }
            return "Player with username <" + username + "> already exists";
        }
        return "Username <" + username + "> has incorrect format";
    }

    public String changeNickname(String nickname) {
        String oldNickName = currentUser.getNickname();
        currentUser.setNickname(nickname);
        return "Your nickname <" + oldNickName + "> changed to <" + nickname + "> successfully";
    }
    public String changePassword(String oldPassword, String newPassword) {
        if (oldPassword.isEmpty()) {
            return "Password can't be empty";
        }
        if (newPassword.isEmpty()) {
            return "New password can't be empty";
        }
        if (oldPassword.equals(currentUser.getPassword())) {
            if (!RegisterMenuController.isPasswordWeak(newPassword).equals("true")) {
                if (!newPassword.equals(oldPassword)) {
                    currentUser.setPassword(newPassword);
                    return "Your password <" + oldPassword + "> changed to <" + newPassword + "> successfully";
                }
                return "You should choose a new password";
            }
            return RegisterMenuController.isPasswordWeak(newPassword);
        }
        return "Your password <" + oldPassword + "> is wrong";
    }

    public String changeEmail(String email) {
        if (email.isEmpty()) {
            return "Email can't be empty";
        }
        if (RegisterMenuController.isEmailFormatCorrect(email.toUpperCase())) {
            if (!UserDatabase.existsEmail(email)) {
                String oldEmail = currentUser.getEmail();
                currentUser.setEmail(email);
                return "Your email <" + oldEmail + "> changed to <" + email + "> successfully";
            }
            return "This email is already used";
        }
        return "The email format is incorrect";
    }

    public String changeSlogan(String slogan) {
        if (slogan.isEmpty()) {
            return "Slogan can't be empty";
        }
        String oldSlogan = currentUser.getSlogan();
        currentUser.setSlogan(slogan);
        return "Your slogan <" + oldSlogan + "> changed to <" + slogan + "> successfully";
    }

    public String removeSlogan() {
        currentUser.setSlogan(null);// null or ""?
        return "Your slogan removed successfully";
    }

    public String displayHighScore() {
        return "Your highScore is : " + currentUser.getHighScore();
    }

    public String displaySlogan() {
        if (currentUser.getSlogan() != null)
            return "Your slogan is : " + currentUser.getSlogan();
        else
            return "You don't have a slogan";
    }

    public String displayRank() {
        return "Your rank is : " + currentUser.getRank();
    }
    public String displayAll() {
        return "User Information :" +
                "\n\tUsername = " + currentUser.getUsername() +
                "\n\tPassword = " + currentUser.getPassword() +
                "\n\tNickname = " + currentUser.getNickname() +
                "\n\tSlogan = " + currentUser.getSlogan() +
                "\n\tHighScore = " + currentUser.getHighScore() +
                "\n\tRank = " + currentUser.getRank() +
                "\n\tE-mail = " + currentUser.getEmail();
    }
}
