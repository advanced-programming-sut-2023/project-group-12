package model;

public enum SecurityQuestions {
    QUESTION1("What is my father’s name?"),
    QUESTION2("What was my first pet’s name?"),
    QUESTION3("What is my mother’s last name?");
    private String question;

    public String getQuestion() {
        return question;
    }

    private SecurityQuestions(String question) {
        this.question = question;
    }
}
