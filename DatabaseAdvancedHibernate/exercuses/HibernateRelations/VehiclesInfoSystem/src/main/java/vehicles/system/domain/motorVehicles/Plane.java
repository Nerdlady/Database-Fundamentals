package vehicles.system.domain.motorVehicles;

import vehicles.system.domain.enums.Color;

import javax.persistence.*;

@Entity(name = "planes")
@PrimaryKeyJoinColumn(name = "id")
public class Plane extends MotorVehicle {
    private String airlineOwner;
    private Color color;
    private Integer passengersCapacity;

    public Plane() {
    }

    @Basic
    public String getAirlineOwner() {
        return airlineOwner;
    }

    public void setAirlineOwner(String airlineOwner) {
        this.airlineOwner = airlineOwner;
    }

    @Enumerated(EnumType.STRING)
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Basic
    public Integer getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setPassengersCapacity(Integer passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }
}
