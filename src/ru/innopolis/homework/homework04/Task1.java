/*
Задача 1.
Для введенной с клавиатуры буквы английского алфавита нужно вывести слева стоящую букву на
стандартной клавиатуре. При этом клавиатура замкнута, т.е. справа от буквы «p» стоит буква «a»,
а слева от "а" буква "р", также соседними считаются буквы «l» и буква «z», а буква «m» с буквой «q».
 */

package ru.innopolis.homework.homework04;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        String keyboard = "qwertyuiopasdfghjklzxcvbnm";
        System.out.print("Введите букву английского алфавита: ");
        Scanner scanner = new Scanner(System.in);
        String ch = scanner.next().toLowerCase();
        int indexOfCh = keyboard.indexOf(ch);
        indexOfCh = indexOfCh == 0 ? keyboard.length() : indexOfCh;
        char leftCh = keyboard.charAt(indexOfCh - 1);
        System.out.println("Буква слева: " + leftCh);
    }
}
