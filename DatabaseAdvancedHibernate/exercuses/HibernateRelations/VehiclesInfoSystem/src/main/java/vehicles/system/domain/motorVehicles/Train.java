package vehicles.system.domain.motorVehicles;

import vehicles.system.domain.motorVehicles.extras.Locomotive;
import vehicles.system.domain.motorVehicles.extras.carriages.Carriage;

import javax.persistence.*;
import java.util.List;

@Entity(name = "trains")
@PrimaryKeyJoinColumn(name = "id")
public class Train extends MotorVehicle{
    private Locomotive locomotive;
    private Integer numberOfCarriages;
    private List<Carriage> carriages;

    public Train() {
    }

    @OneToOne
    @JoinColumn(name = "locomotive_id")
    public Locomotive getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    @Basic
    public Integer getNumberOfCarriages() {
        return numberOfCarriages;
    }

    public void setNumberOfCarriages(Integer numberOfCarriages) {
        this.numberOfCarriages = numberOfCarriages;
    }

    @OneToMany(mappedBy = "train")
    public List<Carriage> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<Carriage> carriages) {
        this.carriages = carriages;
    }
}
