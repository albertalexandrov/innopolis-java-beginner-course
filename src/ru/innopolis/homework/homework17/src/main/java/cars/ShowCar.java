package cars;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)  // todo: так сработает для дочернего класса?
public class ShowCar extends Car {

    private int stars;

    public ShowCar(String id, String brand, String model, int year, int power, int acceleration, int pendant, int durability, int stars) {
        super(id, brand, model, year, power, acceleration, pendant, durability);
        this.stars = stars;
    }

    @Override
    public String toString() {  // todo: Lobmok не используется, тк поля базового класса им не подтягиваются
        return "ShowCar(" +
                "id=" + getId() +
                ", brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", year=" + getYear() +
                ", power=" + getPower() +
                ", acceleration=" + getAcceleration() +
                ", pendant=" + getPendant() +
                ", durability=" + getDurability() +
                ", stars=" + stars +
                ')';
    }

    @Override
    public String getType() {
        return "ShowCar";
    }

}
