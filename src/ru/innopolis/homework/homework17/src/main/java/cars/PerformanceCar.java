package cars;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)  // todo: так сработает для дочернего класса?
public class PerformanceCar extends Car {

    private List<String> addOns;

    public PerformanceCar(String id, String brand, String model, int year, int power, int acceleration, int pendant, int durability, List<String> addOns) {
        super(id, brand, model, year, (int)(power * 1.5), acceleration, (int)(pendant * 0.75), durability);
        this.addOns = addOns;
    }

    public List<String> getAddOns() {
        return Collections.unmodifiableList(addOns);
    }

    public void addAddOn(String addOn) {
        addOns.add(addOn);
    }

    @Override
    public String toString() {  // Lobmok не используется, тк поля базового класса им не подтягиваются
        return "PerformanceCar(" +
                "id=" + getId() +
                ", brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", year=" + getYear() +
                ", power=" + getPower() +
                ", acceleration=" + getAcceleration() +
                ", pendant=" + getPendant() +
                ", durability=" + getDurability() +
                ", addOns=" + addOns +
                ')';
    }

    @Override
    public String getType() {
        return "PerformanceCar";
    }

}
