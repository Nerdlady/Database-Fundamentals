package app.domain.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "anomalies")
public class Anomaly {
    private Long id;
    private Planet originPlanet;
    private Planet teleportPlanet;
    private Set<Person> victims;

    public Anomaly() {

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
    @JoinColumn(name = "origin_planet_id", referencedColumnName = "id", nullable = false)
    public Planet getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(Planet originPlanet) {
        this.originPlanet = originPlanet;
    }

    @ManyToOne
    @JoinColumn(name = "teleport_planet_id", referencedColumnName = "id", nullable = false)
    public Planet getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(Planet teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    @ManyToMany
    @JoinTable(name = "anomaly_victims",
            joinColumns = @JoinColumn(name = "anomaly_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    public Set<Person> getVictims() {
        return victims;
    }

    public void setVictims(Set<Person> victims) {
        this.victims = victims;
    }

    public void addVictim(Person person) {
        if (this.victims == null){
            this.victims = new HashSet<>();
        }
        this.victims.add(person);
    }
}
