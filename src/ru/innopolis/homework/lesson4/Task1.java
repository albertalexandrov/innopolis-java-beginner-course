/*
Задача 1.
Составить программу вывода на экран в одну строку четырех любых чисел с тремя пробелами между ними
 */

package ru.innopolis.homework.lesson4;

import java.util.Random;

public class Task1 {
    public static void main(String[] args) {
        final int SIZE = 4;
        final int MAX_INT = 100;
        String[] nums = new String[SIZE];
        Random randomizer = new Random();
        for (int i = 0; i < SIZE; i++) {
            int num = randomizer.nextInt(MAX_INT);
            nums[i] = String.valueOf(num);
        }
        String delimiter = " ".repeat(3) ;
        System.out.println(String.join(delimiter, nums));
    }
}
