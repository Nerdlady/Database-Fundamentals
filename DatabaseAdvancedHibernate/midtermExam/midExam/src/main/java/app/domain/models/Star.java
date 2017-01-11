package app.domain.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "stars")
public class Star {
    private Long id;
    private String name;
    private SolarSystem solarSystem;
    private Set<Planet> planets;

    public Star() {
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
    @JoinColumn(name = "solar_system_id", referencedColumnName = "id", nullable = false)
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }


    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    @OneToMany(mappedBy = "sun")
    public Set<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(Set<Planet> planets) {
        this.planets = planets;
    }
}
