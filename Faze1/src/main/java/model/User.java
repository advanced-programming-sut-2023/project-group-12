package model;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String question;

    public void setQuestion(int number) {
        this.question = UserDatabase.getQuestions().get(number - 1);
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

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setCurrentScore(int currentScore) {
        if (currentScore > highScore)
            setHighScore(currentScore);
        this.currentScore = currentScore;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public User(String username, String password, String nickname, String email, String slogan) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.slogan = slogan;
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

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    private byte[] salt;

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getSalt() {
        return salt;
    }
}
