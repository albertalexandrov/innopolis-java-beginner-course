package ru.innopolis.homework.homework05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // вопрос: как было правильно сделать чтение из консольного ввода-вывода при чтении в разных методах?
        Tv[] tvs = getTvs();
        int maxVolume = getMaxVolume();
        displayOnAndUnderMaxVolume(tvs, maxVolume);
        sortByChannel(tvs);
    }

    private static Tv[] getTvs() {
        // Scanner scanner = getScanner(); если так, то работает некорректно на второй итерации - выдает на одной строке "Укажите тип экрана (LED, QLED, OLED и тд): Укажите диагональ экрана, дюймы: "
        final int COUNT = 10;
        Tv[] tvs = new Tv[COUNT];
        System.out.printf("Создайте %d телевизора(ов)\n\n", COUNT);
        for (int i = 0; i < COUNT; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("Телевизор %d\n", (i + 1));
            System.out.print("Укажите тип экрана (LED, QLED, OLED и тд): ");
            String displayType = scanner.nextLine();
            System.out.print("Укажите диагональ экрана, дюймы: ");
            int screenSize = scanner.nextInt();
            System.out.print("Укажите частоту обновления экрана, Гц: ");
            int refreshRate = scanner.nextInt();
            System.out.print("Укажите номер канала: ");
            int channel = scanner.nextInt();
            System.out.print("Укажите громкость: ");
            int volume = scanner.nextInt();
            System.out.print("Включен ли телевизор (true/false): ");
            boolean isOn = scanner.nextBoolean();
            Tv tv = new Tv(displayType, screenSize, refreshRate, channel, volume, isOn);
            System.out.printf("По Вашим параметрам был создан телевизор: %s\n\n", tv);
            tvs[i] = tv;
        }
        return tvs;
    }

    private static int getMaxVolume() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите значением допустимого значения громкости звука: ");
        return scanner.nextInt();
    }

    private static void displayOnAndUnderMaxVolume(Tv[] tvs, int maxVolume) {
        System.out.printf("Включенные телевизоры (isOn=true) с громкостью меньше или равной максимальной (volume<=%d)\n", maxVolume);
        for (Tv tv: tvs) {
            if (tv.isOn() && (tv.getVolume() <= maxVolume)) {
                System.out.println(tv);
            }
        }
        System.out.println();
    }

    private static void sortByChannel(Tv[] tvs) {
        Arrays.sort(tvs, new Comparator<Tv>() {
            @Override
            public int compare(Tv o1, Tv o2) {
                return Integer.compare(o1.getChannel(), o2.getChannel());
            }
        });
        System.out.println("Отсорированные по channel:");
        for (Tv tv: tvs) {
            System.out.println(tv);
        }
    }
}
