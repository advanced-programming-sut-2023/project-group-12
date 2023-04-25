package controller;

import model.User;
import model.UserDatabase;

public class ProfileController {

    private User currentUser;

    public ProfileController(User currentUser) {
        this.currentUser = currentUser;
    }

    public String changeUsername(String username){
        if (true//isCorrectName(username))
        ) {
            if (UserDatabase.getUserByUsername(username) == null) {
                String oldUsername = currentUser.getUsername();
                currentUser.setUsername(username);
                return "Your username from <" + oldUsername + "> changed to <" + username + ">";
            } else
                return "Player with username <" + username + "> exists";
        } else
            return "Username <" + username + "> hasn't correct format";
    }

    public String changeNickname(String nickname){
        String oldNickName = currentUser.getNickname();
        currentUser.setNickname(nickname);
        return "Your nickname <" + oldNickName + "> changed to <" + nickname + "> successfully";
    }

    public String changePassword(String oldPassword, String newPassword){
        if (oldPassword.equals(currentUser.getPassword())) {
            if (true //isWeakPassword(password))
            ) {
                if (!newPassword.equals(oldPassword)) {
                    currentUser.setPassword(newPassword);
                    return "Your password <" + oldPassword + "> changed to <" + newPassword + "> successfully";
                } else
                    return "This password was your password";
            } else
                return "This password is weak";
        } else
            return "Your password <" + oldPassword + "> is wrong";
    }

    public String changeEmail(String email){
        if (true //isCorrectEmail(email))
        ) {
            if (true //!existsEmail(email))
            ) {
                String oldEmail = currentUser.getEmail();
                currentUser.setEmail(email);
                return "Your email <" + oldEmail + "> changed to <" + email + "> successfully";
            } else
                return "This email was used";
        } else
            return "This email hasn't correct format";
    }

    public String changeSlogan(String slogan){
        String oldSlogan = currentUser.getSlogan();
        currentUser.setSlogan(slogan);
        return "Your slogan <" + oldSlogan + "> changed to <" + slogan + "> successfully";
    }

    public String removeSlogan() {
        currentUser.setSlogan(null);
        return "Your slogan removed successfully";
    }

    public String displayHighScore(){
        return "Your highScore is : " + currentUser.getHighScore();
    }

    public String displaySlogan(){
        if (currentUser.getSlogan() != null)
            return "Your slogan is : " + currentUser.getSlogan();
        else
            return "You haven't slogan";
    }

    public String displayRank() {
        return "Your rank is : " + currentUser.getRank();
    }

    public String displayAll(){
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
