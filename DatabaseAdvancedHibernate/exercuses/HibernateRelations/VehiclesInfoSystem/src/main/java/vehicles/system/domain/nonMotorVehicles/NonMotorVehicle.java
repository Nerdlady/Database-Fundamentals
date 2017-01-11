package vehicles.system.domain.nonMotorVehicles;

import vehicles.system.domain.BasicVehicle;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "non_moror_vahicles")
@PrimaryKeyJoinColumn(name = "id")
public abstract class NonMotorVehicle extends BasicVehicle {
    private String style;

    public NonMotorVehicle() {
    }

    @Basic
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
