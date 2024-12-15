package ru.innopolis.homework.homework05;


import java.util.Objects;

public class Tv {
    private String displayType;
    private int screenSize;
    private int refreshRate;
    private int channel;
    private int volume;
    private boolean isOn;

    public Tv(String displayType, int screenSize, int refreshRate, int channel, int volume, boolean isOn) {
        this.displayType = displayType;
        this.screenSize = screenSize;
        this.refreshRate = refreshRate;
        this.channel = channel;
        this.volume = volume;
        this.isOn = isOn;
    }

    public Tv() {
        this("LED", 50, 120, 1, 20, true);
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

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setIsOn(boolean on) {
        isOn = on;
    }

    @Override
    public String toString() {
        return "Tv{" +
                "displayType='" + displayType + '\'' +
                ", screenSize=" + screenSize +
                ", refreshRate=" + refreshRate +
                ", channel=" + channel +
                ", volume=" + volume +
                ", isOn=" + isOn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tv tv)) return false;
        return getScreenSize() == tv.getScreenSize() && getRefreshRate() == tv.getRefreshRate() && getChannel() == tv.getChannel() && getVolume() == tv.getVolume() && isOn() == tv.isOn() && Objects.equals(getDisplayType(), tv.getDisplayType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDisplayType(), getScreenSize(), getRefreshRate(), getChannel(), getVolume(), isOn());
    }
}
