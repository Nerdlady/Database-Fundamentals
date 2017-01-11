package softuni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Doctor;
import softuni.repositories.DoctorDao;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDao doctorDao;

    @Autowired
    public DoctorServiceImpl(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public void persist(Doctor doctor) {
        this.doctorDao.saveAndFlush(doctor);
    }

    @Override
    public List<Doctor> getAll() {
        return this.doctorDao.findAll();
    }
}
