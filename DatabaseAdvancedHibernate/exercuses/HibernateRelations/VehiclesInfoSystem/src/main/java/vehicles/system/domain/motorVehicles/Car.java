package vehicles.system.domain.motorVehicles;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "cars")
@PrimaryKeyJoinColumn(name = "id")
public class Car extends MotorVehicle {
    private Integer numberOfDoors;
    private Boolean haveInsurance;

    public Car() {
    }

    @Basic
    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Basic
    public Boolean getHaveInsurance() {
        return haveInsurance;
    }

    public void setHaveInsurance(Boolean haveInsurance) {
        this.haveInsurance = haveInsurance;
    }
}
