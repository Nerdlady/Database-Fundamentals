package app.domain.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "solar_systems")
public class SolarSystem {

    private Long id;
    private String name;
    private Set<Star> stars;
    private Set<Planet> planets;

    public SolarSystem() {
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

    @OneToMany(mappedBy = "solarSystem")
    public Set<Star> getStars() {
        return stars;
    }

    public void setStars(Set<Star> stars) {
        this.stars = stars;
    }

    @OneToMany(mappedBy = "solarSystem")
    public Set<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(Set<Planet> planets) {
        this.planets = planets;
    }
}
