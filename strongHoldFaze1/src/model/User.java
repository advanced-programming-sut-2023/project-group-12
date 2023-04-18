package model;

import java.util.ArrayList;

public class User {
    private ArrayList<Map> maps;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String answer1;
    private String answer2;
    private String answer3;
    private String slogan;
    //a boolean for staying logged in
    private int highScore;
    private int currentScore;
    private int rank;
    // the part where we show the user's information

    public User(String username, String password, String nickname, String email, int questionsNumber, String answer) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        if (questionsNumber == 1) {
            answer1 = answer;
        }
        if (questionsNumber == 2) {
            answer2= answer;
        }
        if (questionsNumber == 3) {
            answer3 = answer;
        }
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getRank() {
        return rank;
    }

    public String getSlogan() {
        return slogan;
    }
    // the part in profile menu where we change information
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
    // these methods maybe placed somewhere else
    /**
     this method generates a new password
     */
    private String generateRandomPass () {
        return "";
    }
    private String generateRandomSlogan () {
        // choose a slogan from slogan enum
        return "";
    }
    // TODO: create a file for user info and encode it


}
