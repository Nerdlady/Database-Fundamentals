package vehicles.system.domain.motorVehicles;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "cargo_ships")
@PrimaryKeyJoinColumn(name = "id")
public class CargoShip extends Ship {
    private Double maxKilograms;

    public CargoShip() {
    }

    @Basic
    public Double getMaxKilograms() {
        return maxKilograms;
    }

    public void setMaxKilograms(Double maxKilograms) {
        this.maxKilograms = maxKilograms;
    }
}
