/*
Задача 2.
Напишите программу на Java, которая принимает два целыхчисла от пользователя, а затем печатает сумму, разницу,
произведение, среднеезначение, расстояние (разница между целыми числами), максимум (большее издвух целых чисел),
минимум (меньшее из двух целых чисел).
*/

package homeworks.homework02;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите 1-е целое число: ");
        int num1 = scanner.nextInt();
        System.out.print("Введите второе целое число: ");
        int num2 = scanner.nextInt();
        System.out.printf("Сумма двух целых чисел: %d\n", num1 + num2);
        System.out.printf("Разница двух целых чисел: %d\n", num1 - num2);
        System.out.printf("Произведение из двух целых чисел: %d\n", num1 * num2);
        System.out.printf("Среднее из двух целых чисел: %.2f\n", (num1 + num2) / 2.0);
        System.out.printf("Расстояние двух целых чисел: %d\n", Math.abs(num1 - num2));
        System.out.printf("Максимальное целое число: %d\n", Math.max(num1, num2));
        System.out.printf("Минимальное целое число: %d\n", Math.min(num1, num2));
    }
}
