package model;

import java.util.ArrayList;

public class User {
    //private ArrayList<Map> maps;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String question;

    public void setQuestion(int number) {
        this.question = UserDatabase.getQuestions().get(number);
    }

    public String getQuestion() {
        return question;
    }

    private String answer;
    private String slogan;

    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
    }

    public boolean isStayLoggedIn() {
        return stayLoggedIn;
    }

    private boolean stayLoggedIn;
    private int highScore;
    private int currentScore;
    private int rank;
    // the part where we show the user's information
    //private Kingdom kingdom;

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

//    public Kingdom getKingdom() {
//        return kingdom;
//    }

    public void setRank(int rank) {
        this.rank = rank;
    }

//    public void setKingdom(Kingdom kingdom) {
//        this.kingdom = kingdom;
//    }

    public User(String username, String password, String nickname, String email, String slogan) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.slogan = slogan;
    }

//    public ArrayList<Map> getMaps() {
//        return maps;
//    }

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

    public String getAnswer() {
        return answer;
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

    private String generateRandomSlogan () {
        // choose a slogan from slogan enum
        return "";
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    // TODO: create a file for user info and encode it


}
