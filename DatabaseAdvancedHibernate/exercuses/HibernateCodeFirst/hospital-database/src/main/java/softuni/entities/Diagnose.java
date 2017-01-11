package softuni.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "diagnoses")
public class Diagnose {
    private Long id;
    private String name;
    private String comment;
    private Set<Patient> patients;

    public Diagnose() {
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

    @Column(columnDefinition = "TEXT")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToMany
    @JoinTable(name = "patients_diagnoses",
            joinColumns = {@JoinColumn(name = "diagnose_id")},
            inverseJoinColumns = {@JoinColumn(name = "patient_id")})
    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
