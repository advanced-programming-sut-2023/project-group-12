package org.example;
public class Main {
    public static void main(String[] args) {
        Captcha captcha = new Captcha();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < captcha.getCreatedCaptcha().length; j++) {
                System.out.print(captcha.getCreatedCaptcha()[j][i]);
            }
            System.out.println();
        }
    }
}