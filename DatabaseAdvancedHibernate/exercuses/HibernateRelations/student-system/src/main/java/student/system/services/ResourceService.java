package student.system.services;

import student.system.domain.Resource;

import java.util.List;

public interface ResourceService {
    void persist(Resource resource);

    void delete(Resource resource);

    List<Resource> getResources();
}
