package model;

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
            "   #####    ",
            "       #    ",
            "       #    ",
            " #######    ",
            " #          ",
            " #          ",
            " #####      ",
            "            "
    };
    public static String[] three = {
            "  #####     ",
            "       #    ",
            "       #    ",
            "  #####     ",
            "       #    ",
            "       #    ",
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
            " #####      ",
            " #          ",
            " #          ",
            " #######    ",
            "       #    ",
            "       #    ",
            "    ####    ",
            "            "
    };
    public static String[] six = {
            " #######    ",
            " #     #    ",
            " #          ",
            " #######    ",
            " #     #    ",
            " #     #    ",
            " #######    ",
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
            " #######    ",
            " #     #    ",
            " #     #    ",
            " #######    ",
            " #     #    ",
            " #     #    ",
            " #######    ",
            "            "
    };
    public static String[] nine = {
            "  #####     ",
            " #     #    ",
            " #     #    ",
            "  ######    ",
            "       #    ",
            "       #    ",
            "    ###     ",
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

    private final int minimum = 4;
    private final int maximum = 8;
    private final int captchaSize = (int) (Math.random() * (maximum - minimum + 1)) + minimum;
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
                    stringBuilder.setCharAt(j, charactersToCreateNoise.charAt(random.nextInt(charactersToCreateNoise.length())));
                }
            }
            digitPlusNoise[i] = stringBuilder.toString();
        }
        return digitPlusNoise;
    }

    private final String[][] createdCaptcha = new String[captchaSize][];
    private final int[] numbers = new int[captchaSize];

    public Captcha() {
        Random random = new Random();
        for (int i = 0; i < captchaSize; i++) {
            int index = random.nextInt(captchaSize);
            createdCaptcha[i] = createNoise(index);
            numbers[i] = index;
        }
    }

    public String[][] getCreatedCaptcha() {
        return createdCaptcha;
    }

    public int[] getNumbers() {
        return numbers;
    }
}
