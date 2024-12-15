/*
Задача 1.
Напишите Java-программу для преобразования температуры изФаренгейта в градусы Цельсия.
*/

package ru.innopolis.homework.homework05.homework02;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите степень в градусах Фаренгейта: ");
        double fahrenheit = scanner.nextFloat();
        double celsius = (fahrenheit - 32) * 5 / 9;
        // в примере точность после запятой равна одному знаку
        // по-хорошему нужно выводить ввод пользователя as-is
        // но в условиях задачи об этом не написано
        System.out.printf("%.1f градусов по Фаренгейту равна %.1f по Цельсию", fahrenheit, celsius);
    }
}
