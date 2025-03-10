package cars;

import exceptions.CarTypeNotSpecifiedException;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Car {
    private String id;
    private String brand;  // марка
    private String model;  // модель
    private Integer year;  // год выпуска - model year
    private Integer power;  // мощность
    private Integer acceleration;  // ускорение
    private Integer pendant;  // подвеска
    private Integer durability;  // долговечность

    public String getType() {
        throw new CarTypeNotSpecifiedException();
    }

}
