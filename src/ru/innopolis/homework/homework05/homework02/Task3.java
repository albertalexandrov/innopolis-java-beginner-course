/*
Задача 3.
Напишите Java-программу для объединения данной строки ссамим собой заданное количество раз.
 */

package ru.innopolis.homework.homework05.homework02;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Исходная строка: ");
        String string = scanner.nextLine();
        System.out.print("Сколько раз вывести строку? ");
        int count = scanner.nextInt();
        String result = string.repeat(count);
        System.out.printf("После повторения 8 раз: %s", result);
    }
}
