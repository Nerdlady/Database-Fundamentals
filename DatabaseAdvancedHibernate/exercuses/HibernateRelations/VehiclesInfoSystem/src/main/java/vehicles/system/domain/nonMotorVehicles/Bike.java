package vehicles.system.domain.nonMotorVehicles;

import vehicles.system.domain.enums.Color;

import javax.persistence.*;

@Entity(name = "bikes")
@PrimaryKeyJoinColumn(name = "id")
public class Bike extends NonMotorVehicle{
    private Integer shiftCount;
    private Color color;

    public Bike() {
    }

    @Basic
    public Integer getShiftCount() {
        return shiftCount;
    }

    public void setShiftCount(Integer shiftCount) {
        this.shiftCount = shiftCount;
    }

    @Enumerated(EnumType.STRING)
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
