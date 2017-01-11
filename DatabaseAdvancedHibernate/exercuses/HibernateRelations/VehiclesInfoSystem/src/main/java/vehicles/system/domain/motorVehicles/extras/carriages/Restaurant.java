package vehicles.system.domain.motorVehicles.extras.carriages;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Restaurant extends Carriage {
    private Integer tablesCount;

    public Restaurant() {
    }

    @Basic
    public Integer getTablesCount() {
        return tablesCount;
    }

    public void setTablesCount(Integer tablesCount) {
        this.tablesCount = tablesCount;
    }
}
