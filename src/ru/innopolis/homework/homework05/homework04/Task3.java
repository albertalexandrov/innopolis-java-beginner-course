/*
Задача 3*.
Задана строка, состоящая из букв английского алфавита, разделенных одним пробелом.
Необходимо каждую последовательность символов упорядочить по возрастанию и вывести слова в нижнем регистре.
 */

package ru.innopolis.homework.homework05.homework04;

import java.util.Arrays;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String string = scanner.nextLine();
        String[] words = string.toLowerCase().split(" ");
        for (String word : words) {
            char[] charsArray = word.toCharArray();
            Arrays.sort(charsArray);   // не было указаний касательно уникальности символов
            String sortedChars = new String(charsArray);
            System.out.println("Отсортированные символы: " + sortedChars);
        }
    }
}
