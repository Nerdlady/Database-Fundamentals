package softuni.services;

import softuni.entities.Doctor;

import java.util.List;

public interface DoctorService {
    void persist(Doctor doctor);
    List<Doctor> getAll();
}
