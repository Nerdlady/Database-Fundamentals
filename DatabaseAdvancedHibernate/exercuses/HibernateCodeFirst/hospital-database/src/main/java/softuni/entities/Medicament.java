package softuni.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "medicaments")
public class Medicament {
    private Long id;
    private String name;
    private Set<Patient> patients;

    public Medicament() {
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

    @ManyToMany
    @JoinTable(name = "patients_medicaments",
            joinColumns = {@JoinColumn(name = "medicament_id")},
            inverseJoinColumns = {@JoinColumn(name = "patient_id")})
    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
