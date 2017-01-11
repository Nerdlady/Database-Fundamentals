package student.system.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.system.domain.License;
import student.system.repositories.LicenseRepository;
import student.system.services.LicenseService;

import java.util.List;

@Service
public class LicenseServiceImpl implements LicenseService {
    private final LicenseRepository licenseRepository;

    @Autowired
    public LicenseServiceImpl(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @Override
    public void persist(License license) {
        this.licenseRepository.saveAndFlush(license);
    }

    @Override
    public List<License> getAll() {
        return this.licenseRepository.findAll();
    }
}
