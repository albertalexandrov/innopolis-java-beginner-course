package ru.innopolis.homework.homework05;

import java.util.Random;

public final class Utils {
    // класс-утилита

    public Utils() {}

    private static Random getRandom() {
        return new Random();
    }

    public static int getRandomInt(int from, int to) {
        return getRandom().nextInt(from, to);
    }

    public static int getRandomInt(int to) {
        return getRandom().nextInt(to);
    }

    public static int getRandomInt() {
        return getRandom().nextInt(1000);
    }

}
