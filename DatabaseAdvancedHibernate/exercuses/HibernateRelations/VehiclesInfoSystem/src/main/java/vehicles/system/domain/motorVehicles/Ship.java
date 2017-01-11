package vehicles.system.domain.motorVehicles;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "ships")
@PrimaryKeyJoinColumn(name = "id")
public abstract class Ship extends MotorVehicle {
    private String nationality;
    private String captainName;
    private Integer shipCrewSize;

    public Ship() {
    }

    @Basic
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Basic
    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    @Basic
    public Integer getShipCrewSize() {
        return shipCrewSize;
    }

    public void setShipCrewSize(Integer shipCrewSize) {
        this.shipCrewSize = shipCrewSize;
    }
}
