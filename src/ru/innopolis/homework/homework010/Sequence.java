package ru.innopolis.homework.homework010;

import java.util.Arrays;

public class Sequence {
    public static int[] filter(int[] array, ByCondition condition) {
        // todo:
        //  если я правильно понял, то для IntStream предикат должен быть либо IntPredicate,
        //  либо кастомный, но представляющий метод с аргументом int?
        //  без ::isOk IDE указывала ошибку
        return Arrays.stream(array).filter(condition::isOk).toArray();
    }
}
