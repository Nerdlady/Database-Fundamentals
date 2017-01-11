package vehicles.system.domain.motorVehicles;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "cruise_ships")
@PrimaryKeyJoinColumn(name = "id")
public class CruiseShip extends Ship {
    private Integer passengerCapacity;

    public CruiseShip() {
    }

    @Basic
    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}
