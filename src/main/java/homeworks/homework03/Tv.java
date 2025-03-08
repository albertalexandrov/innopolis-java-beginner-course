package homeworks.homework03;

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



