package cars;

public class ShowCar extends Car {
    private int stars;

    public ShowCar(String brand, String model, int year, int power, int acceleration, int pendant, int durability, int stars) {
        super(brand, model, year, power, acceleration, pendant, durability);
        this.stars = stars;
    }

    public ShowCar(String brand, String model, int year, int power, int acceleration, int pendant, int durability) {
        this(brand, model, year, power, acceleration, pendant, durability, 0);
    }

    public ShowCar() {
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "ShowCar{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", year=" + getYear() +
                ", power=" + getPower() +
                ", acceleration=" + getAcceleration() +
                ", pendant=" + getPendant() +
                ", durability=" + getDurability() +
                ", stars=" + stars +
                '}';
    }

}
