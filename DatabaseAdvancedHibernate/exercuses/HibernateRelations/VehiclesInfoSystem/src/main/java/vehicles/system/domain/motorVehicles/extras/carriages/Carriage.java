package vehicles.system.domain.motorVehicles.extras.carriages;

import vehicles.system.domain.motorVehicles.Train;

import javax.persistence.*;

@Entity(name = "carriages")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Carriage {
    private Long id;
    private Train train;

    public Carriage() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "train_id")
    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
