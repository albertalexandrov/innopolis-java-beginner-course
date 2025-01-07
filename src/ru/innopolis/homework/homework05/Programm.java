package ru.innopolis.homework.homework05;

import java.util.Objects;


public class Programm {
    private String name;
    private int rating;
    private int audience;

    public Programm(String name, int rating, int audience) {
        // программа создается с одним лишь с названием. рейтинги и аудитория появляется после
        this.name = name;
        this.rating = rating;
        this.audience = audience;
    }

    public Programm() {
        this(generateRandomName(), Utils.getRandomInt(), Utils.getRandomInt());
    }

    private static String generateRandomName() {
        return "Программа " + Utils.getRandomInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    @Override
    public String toString() {
        return "Programm{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", audience=" + audience +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Programm programm)) return false;
        return Objects.equals(getName(), programm.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

}
