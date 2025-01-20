package ru.innopolis.homework.homework03;

import java.util.Scanner;

class App {
    public static void main(String[] args) {
        Tv tv1 = new Tv();
        System.out.println("Автоматически создан телелевизор: " + tv1);
        System.out.println("Меняем значения для проверки работоспособности сеттеров и геттеров");
        tv1.setDisplayType("QLED");
        System.out.println("Новое значение DisplayType: " + tv1.getDisplayType());
        tv1.setRefreshRate(tv1.getRefreshRate() * 2);
        System.out.println("Новое значение RefreshRate: " + tv1.getRefreshRate());
        tv1.setScreenSize(tv1.getScreenSize() * 2);
        System.out.println("Новое значение ScreenSize: " + tv1.getScreenSize());
        System.out.println("Обновленный телевизор: " + tv1);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Теперь создайте телевизор на своих условиях.");
        System.out.print("Укажите тип экрана (LED, QLED, OLED и тд): ");
        String displayType = scanner.nextLine();
        System.out.print("Укажите диагональ экрана, дюймы: ");
        int screenSize = scanner.nextInt();
        System.out.print("Укажите частоту обновления экрана, Гц: ");
        int refreshRate = scanner.nextInt();
        Tv tv2 = new Tv(displayType, screenSize, refreshRate);
        System.out.println("По Вашим параметрам был создан телевизор:");
        System.out.println(tv2);
    }
}