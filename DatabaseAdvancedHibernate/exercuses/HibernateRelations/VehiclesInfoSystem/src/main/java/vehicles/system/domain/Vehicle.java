package vehicles.system.domain;

import java.math.BigDecimal;

public interface Vehicle {
    Long getId();

    void setId(Long id);

    String getManufacturer();

    void setManufacturer(String manufacturer);

    String getModel();

    void setModel(String model);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Double getMaxSpeed();

    void setMaxSpeed(Double maxSpeed);
}
