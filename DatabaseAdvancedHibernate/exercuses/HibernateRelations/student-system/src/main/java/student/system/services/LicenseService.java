package student.system.services;

import student.system.domain.License;

import java.util.List;

public interface LicenseService {
    void persist(License license);

    List<License> getAll();
}
