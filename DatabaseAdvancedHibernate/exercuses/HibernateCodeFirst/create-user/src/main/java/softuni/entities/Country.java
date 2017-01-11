package softuni.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "countries")
public class Country implements Serializable {
    private Long id;
    private String name;
    private Set<Town> towns;

    public Country() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "id")
    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }
}
