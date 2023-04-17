package org.example;

import org.example.Main;

import java.util.Random;

//TODO: access modifiers
public class Captcha {
    public static String[] zero = {
            "   ###      ",
            "  #   #     ",
            " #     #    ",
            " #     #    ",
            " #     #    ",
            "  #   #     ",
            "   ###      ",
            "            "
    };
    public static String[] one = {
            "   #      ",
            "  ##      ",
            " # #      ",
            "   #      ",
            "   #      ",
            "   #      ",
            " #####    ",
            "          "
    };
    public static String[] two = {
            "  #####     ",
            " #     #    ",
            "       #    ",
            "  #####     ",
            " #          ",
            " #          ",
            " #######    ",
            "            "
    };
    public static String[] three = {
            "  #####     ",
            " #     #    ",
            "       #    ",
            "  #####     ",
            "       #    ",
            " #     #    ",
            "  #####     ",
            "            "
    };
    public static String[] four = {
            " #          ",
            " #    #     ",
            " #    #     ",
            " #    #     ",
            " #######    ",
            "      #     ",
            "      #     ",
            "            "
    };
    public static String[] five = {
            " #######    ",
            " #          ",
            " #          ",
            " ######     ",
            "       #    ",
            " #     #    ",
            "  #####     ",
            "            "
    };
    public static String[] six = {
            "  #####     ",
            " #     #    ",
            " #          ",
            " ######     ",
            " #     #    ",
            " #     #    ",
            "  #####     ",
            "            "
    };
    public static String[] seven = {
            " #######    ",
            " #    #     ",
            "     #      ",
            "    #       ",
            "   #        ",
            "   #        ",
            "   #        ",
            "            "
    };
    public static String[] eight = {
            "  #####     ",
            " #     #    ",
            " #     #    ",
            "  #####     ",
            " #     #    ",
            " #     #    ",
            "  #####     ",
            "            "
    };
    public static String[] nine = {
            "  #####     ",
            " #     #    ",
            " #     #    ",
            "  ######    ",
            "       #    ",
            " #     #    ",
            "  #####     ",
            "            "
    };
    public static String[][] digits = new String[10][];

    {
        digits[0] = zero;
        digits[1] = one;
        digits[2] = two;
        digits[3] = three;
        digits[4] = four;
        digits[5] = five;
        digits[6] = six;
        digits[7] = seven;
        digits[8] = eight;
        digits[9] = nine;
    }

    private int minimum = 4;
    private int maximum = 8;
    private int captchaSize = (int) (Math.random() * (maximum - minimum + 1)) + minimum;
    String charactersToCreateNoise = "!@#$%^&*()_-+='\":;.>,<?/|`~";
    private String[] createNoise(int number) {
        int size = digits[number].length;
        String[] digitPlusNoise = new String[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            String string = digits[number][i];
            StringBuilder stringBuilder = new StringBuilder(string);
            int length = stringBuilder.length();
            for (int j = 0; j < length; j++) {
                if (random.nextDouble() < 0.08) {
                    stringBuilder.setCharAt(j, (char) (charactersToCreateNoise.charAt(random.nextInt(charactersToCreateNoise.length()))));
                }
            }
            digitPlusNoise[i] = stringBuilder.toString();
        }
        return digitPlusNoise;
    }

    private String[][] createdCaptcha = new String[captchaSize][];

    public Captcha() {
        Random random = new Random();
        for (int i = 0; i < captchaSize; i++) {
            createdCaptcha[i] = createNoise(random.nextInt(captchaSize));
        }
    }

    public String[][] getCreatedCaptcha() {
        return createdCaptcha;
    }
}
