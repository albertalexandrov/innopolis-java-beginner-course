package ru.innopolis.homework.homework05.homework03;

import java.util.Scanner;


public class Tv {
    private String displayType;
    private int screenSize;
    private int refreshRate;

    public Tv(String displayType, int screenSize, int refreshRate) {
        this.displayType = displayType;
        this.screenSize = screenSize;
        this.refreshRate = refreshRate;
    }

    public Tv() {
        this("LED", 50, 120);
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    @Override
    public String toString() {
        return String.format("Телевизор c типом экрана %s, диагональю %s дюймов и частотой обновления %d Гц", displayType, screenSize, refreshRate);
    }
}


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
