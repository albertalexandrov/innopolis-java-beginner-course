package ru.innopolis.attestations.attestation01;

import java.util.Random;

public final class Utils {

    private Utils() {}

    public static boolean containsDigits(String string) {
        for (char chr : string.toCharArray()) {
            if (Character.isDigit(chr)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsSymbol(String string, char symbol) {
        for (char chr : string.toCharArray()) {
            if (chr == symbol) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsLetters(String string) {
        for (char chr : string.toCharArray()) {
            if (Character.isLetter(chr)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsLettersOnly(String string) {
        for (char chr : string.toCharArray()) {
            if (!Character.isLetter(chr)) {
                return false;
            }
        }
        return true;
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        var random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

}
