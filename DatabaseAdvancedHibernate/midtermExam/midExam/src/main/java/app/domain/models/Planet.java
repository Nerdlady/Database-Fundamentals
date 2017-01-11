package app.domain.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "planets")
public class Planet {
    private Long id;
    private String name;
    private Star sun;
    private SolarSystem solarSystem;
    private Set<Person> people;
    private Set<Anomaly> originalAnomaly;
    private Set<Anomaly> teleportAnomaly;

    public Planet() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "sun_id", referencedColumnName = "id", nullable = false)
    public Star getSun() {
        return sun;
    }

    public void setSun(Star sun) {
        this.sun = sun;
    }

    @ManyToOne
    @JoinColumn(name = "solar_system_id", referencedColumnName = "id", nullable = false)
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    @OneToMany(mappedBy = "homePlanet")
    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    @OneToMany(mappedBy = "originPlanet")
    public Set<Anomaly> getOriginalAnomaly() {
        return originalAnomaly;
    }

    public void setOriginalAnomaly(Set<Anomaly> originalAnomaly) {
        this.originalAnomaly = originalAnomaly;
    }

    @OneToMany(mappedBy = "teleportPlanet")
    public Set<Anomaly> getTeleportAnomaly() {
        return teleportAnomaly;
    }

    public void setTeleportAnomaly(Set<Anomaly> teleportAnomaly) {
        this.teleportAnomaly = teleportAnomaly;
    }
}
