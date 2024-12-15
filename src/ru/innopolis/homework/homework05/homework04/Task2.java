/*
Задача 2.
Задана последовательность, состоящая только из символов ‘>’, ‘<’ и ‘-‘. Требуется найти количество стрел,
которые спрятаны в этой последовательности. Стрелы – это под строки вида ‘>>-->’ и ‘<--<<’.
 */

package ru.innopolis.homework.homework05.homework04;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        final int MAX_SEQ_LENGTH = 106;
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Введите последовательность символов (не более %s символов): ", MAX_SEQ_LENGTH);
        String seq = scanner.nextLine();
        if (seq.length() > MAX_SEQ_LENGTH) {
            System.out.printf("Последовательность может содержать не более %s символов", MAX_SEQ_LENGTH);
            return;
        }
        int arrowsCount = 0;
        arrowsCount += countArrows(seq, ">>-->");
        arrowsCount += countArrows(seq, "<--<<");
        System.out.print("Найдено стрел: " + arrowsCount);
    }

    private static int countArrows(String seq, String arrow) {
        // решил, что нужно сделать самостоятельно, а не использовать готовые решения типа StringUtils.countMatches
        int arrowsCount = 0;
        for (int i = 0; i < seq.length() - arrow.length() + 1; i++) {
            String subseq = seq.substring(i, i + arrow.length());
            if (subseq.equals(arrow)) {
                arrowsCount++;
            }
        }
        return arrowsCount;
    }
}
