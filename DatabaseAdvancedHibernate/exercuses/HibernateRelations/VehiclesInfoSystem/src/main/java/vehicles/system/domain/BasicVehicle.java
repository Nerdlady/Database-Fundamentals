package vehicles.system.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "vehicles")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BasicVehicle implements Vehicle {
    private Long id;
    private String manufacturer;
    private String model;
    private BigDecimal price;
    private Double maxSpeed;

    public BasicVehicle() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Basic
    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Override
    public Double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
