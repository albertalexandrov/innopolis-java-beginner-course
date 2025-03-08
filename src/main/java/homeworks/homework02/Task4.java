/*
Задача 4*.
Напишите программу на Java для печати сетки из заданныхэлементов.
*/

package homeworks.homework02;

import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число строк и столбцов сетки: ");
        int size = scanner.nextInt();
        System.out.print("Введите повторяемый элемент сетки: ");
        String chr = scanner.next();
        System.out.printf("Сетка по запросу %d на %d\n", size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(chr);
            }
            System.out.print("\n");
        }
    }
}
