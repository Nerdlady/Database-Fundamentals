package softuni.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(name = "patients")
public class Patient {
    private Long id;
    private String firstName;
    private String lastName;
    private String addres;
    private Date dateOfBirth;
    private String email;
    private byte[] picture;
    private Boolean haveMedicalInsurance;
    private Set<Medicament> medicaments;
    private Set<Diagnose> diagnoses;
    private Set<Visitation> visitations;

    public Patient() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    @Column(nullable = false)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null){
            throw new IllegalArgumentException("Email cannot be null");
        }
        Pattern pattern = Pattern.compile("^[A-Za-z0-9][A-Za-z0-9+_.-]+[A-Za-z0-9]@([A-Za-z0-9+_.-]+)\\.([A-Za-z0-9+]+)$");
        Matcher matcher= pattern.matcher(email);
        if (!matcher.find()){
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Boolean getHaveMedicalInsurance() {
        return haveMedicalInsurance;
    }

    public void setHaveMedicalInsurance(Boolean haveMedicalInsurance) {
        this.haveMedicalInsurance = haveMedicalInsurance;
    }

    @ManyToMany
    @JoinTable(name = "patients_medicaments",
    joinColumns = {@JoinColumn(name = "patient_id")},
    inverseJoinColumns = {@JoinColumn(name = "medicament_id")})
    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    @ManyToMany
    @JoinTable(name = "patients_diagnoses",
            joinColumns = {@JoinColumn(name = "patient_id")},
            inverseJoinColumns = {@JoinColumn(name = "diagnose_id")})
    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    @OneToMany(mappedBy = "id",fetch = FetchType.EAGER)
    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }
}
