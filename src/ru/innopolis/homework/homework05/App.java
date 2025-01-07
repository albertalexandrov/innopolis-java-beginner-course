package ru.innopolis.homework.homework05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // вопрос: как было правильно сделать чтение из консольного ввода-вывода при чтении в разных методах?
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество телевизоров: ");
        int count = scanner.nextInt();
        Tv[] tvs = getTvs(count);
        int maxVolume = getMaxVolume();
        displayOnAndUnderMaxVolume(tvs, maxVolume);
        sortByChannel(tvs);
    }

    private static Tv[] getTvs(int count) {
        // Scanner scanner = getScanner(); если так, то работает некорректно на второй итерации - выдает на одной строке "Укажите тип экрана (LED, QLED, OLED и тд): Укажите диагональ экрана, дюймы: "
        Tv[] tvs = new Tv[count];
        System.out.printf("Создайте %d телевизора(ов)\n\n", count);
        for (int i = 0; i < count; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("Телевизор %d\n", (i + 1));
            System.out.print("Укажите тип экрана (LED, QLED, OLED и тд): ");
            String displayType = scanner.nextLine();
            System.out.print("Укажите диагональ экрана, дюймы: ");
            int screenSize = scanner.nextInt();
            System.out.print("Укажите частоту обновления экрана, Гц: ");
            int refreshRate = scanner.nextInt();
            System.out.print("Укажите громкость: ");
            int volume = scanner.nextInt();
            Tv tv = new Tv(displayType, screenSize, refreshRate, volume);
            System.out.printf("По Вашим параметрам был создан телевизор: %s\n\n", tv);
            tvs[i] = tv;
            System.out.printf("Телевизор %s. Включаем\n",  tv.isOn() ? "включен" : "выключен");
            tv.switchOn();
            System.out.println("Каналы телевизора: " + tv.getChannels());
            System.out.printf("Текущий канал: %s. Переключаем.\n", tv.getChannel());
            tv.switchChannel();
            System.out.println("Новый текущий канал: " + tv.getChannel());
            System.out.println("Выключаем телевизор.");
            tv.switchOff();
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
                return Integer.compare(o1.getChannel().getNo(), o2.getChannel().getNo());
            }
        });
        System.out.println("Отсорированные по channel:");
        for (Tv tv: tvs) {
            System.out.println(tv);
        }
    }
}
