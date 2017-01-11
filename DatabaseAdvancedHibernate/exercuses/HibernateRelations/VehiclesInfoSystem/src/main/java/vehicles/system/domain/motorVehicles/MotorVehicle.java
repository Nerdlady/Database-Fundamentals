package vehicles.system.domain.motorVehicles;

import vehicles.system.domain.BasicVehicle;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "motor_vehicles")
@PrimaryKeyJoinColumn(name = "id")
public abstract class MotorVehicle extends BasicVehicle {
    private Integer numberOfEngines;
    private String engneType;
    private Double tankCapacity;

    public MotorVehicle() {
    }

    @Basic
    public Integer getNumberOfEngines() {
        return numberOfEngines;
    }

    public void setNumberOfEngines(Integer numberOfEngines) {
        this.numberOfEngines = numberOfEngines;
    }

    @Basic
    public String getEngneType() {
        return engneType;
    }

    public void setEngneType(String engneType) {
        this.engneType = engneType;
    }

    @Basic
    public Double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(Double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }
}
