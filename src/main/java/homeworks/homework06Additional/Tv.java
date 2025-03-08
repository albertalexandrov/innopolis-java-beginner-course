package homeworks.homework06Additional;


import homeworks.homework05.Channel;
import homeworks.homework05.Utils;

import java.util.ArrayList;
import java.util.Objects;

public class Tv {
    private String displayType;
    private int screenSize;
    private int refreshRate;
    private int volume;
    private boolean isOn;
    private ArrayList<Channel> channels;
    private int channelIndex;
    private Channel channel;

    public Tv(String displayType, int screenSize, int refreshRate, int volume) {
        this.displayType = displayType;
        this.screenSize = screenSize;
        this.refreshRate = refreshRate;
        this.volume = volume;
        this.isOn = false;
        this.channels = new ArrayList<>();
    }

    public Tv() {
        this("LED", 50, 120, 20);
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

    public Channel getChannel() {
        return channel;
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

    public void switchOn() {
        isOn = true;
        addChannels(Utils.getRandomInt(3, 100));
        channelIndex = 0;
        setChannel(channelIndex);
        System.out.printf("Телевизор включен. Каналы настроены (%s).\n", getChannels().size());
    }

    public void switchOff() {
        isOn = false;
        System.out.println("Телевизор выключен");
    }

    public void switchChannel() {
        channelIndex = (channelIndex + 1) % channels.size();
        setChannel(channelIndex);
    }

    private void setChannel(int channelIndex) {
        channel = channels.get(channelIndex);
    }

    private void addChannels(int count) {
        for (int i = 0; i < count; i++) {
            channels.add(new Channel());
        }
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    @Override
    public String toString() {
        return "Tv{" +
                "displayType='" + displayType + '\'' +
                ", screenSize=" + screenSize +
                ", refreshRate=" + refreshRate +
                ", volume=" + volume +
                ", isOn=" + isOn +
                ", channels=" + channels +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tv tv)) return false;
        return getScreenSize() == tv.getScreenSize() && getRefreshRate() == tv.getRefreshRate() && getVolume() == tv.getVolume() && isOn() == tv.isOn() && Objects.equals(getDisplayType(), tv.getDisplayType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDisplayType(), getScreenSize(), getRefreshRate(), getVolume(), isOn());
    }
}
