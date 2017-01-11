package student.system.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.system.domain.Resource;
import student.system.repositories.ResourceRepository;
import student.system.services.ResourceService;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;

    @Autowired
    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public void persist(Resource resource) {
        this.resourceRepository.saveAndFlush(resource);
    }

    @Override
    public void delete(Resource resource) {
        this.resourceRepository.delete(resource);
    }

    @Override
    public List<Resource> getResources() {
        return this.resourceRepository.findAll();
    }
}
