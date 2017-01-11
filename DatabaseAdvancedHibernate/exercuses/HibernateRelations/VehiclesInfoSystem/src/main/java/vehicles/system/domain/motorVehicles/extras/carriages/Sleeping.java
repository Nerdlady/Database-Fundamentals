package vehicles.system.domain.motorVehicles.extras.carriages;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Sleeping extends Carriage {
    private Integer bedsCount;

    public Sleeping() {
    }

    @Basic
    public Integer getBedsCount() {
        return bedsCount;
    }

    public void setBedsCount(Integer bedsCount) {
        this.bedsCount = bedsCount;
    }
}
