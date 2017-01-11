package vehicles.system.domain.motorVehicles.extras;

import vehicles.system.domain.motorVehicles.Train;

import javax.persistence.*;

@Entity(name = "locomotives")
public class Locomotive {
    private Long id;
    private String model;
    private Double power;
    private Train train;

    public Locomotive() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    @OneToOne
    @JoinColumn(name = "train_id")
    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
