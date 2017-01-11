package app.servicesImpl;

import app.domain.models.SolarSystem;
import app.dtos.jsonDtos.inport.SolarSystemImportDto;
import app.repositories.SolarSystemRepository;
import app.services.SolarSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolarSystemServiceImpl implements SolarSystemService {
    private final SolarSystemRepository solarSystemRepository;

    @Autowired
    public SolarSystemServiceImpl(SolarSystemRepository solarSystemRepository) {
        this.solarSystemRepository = solarSystemRepository;
    }

    @Override
    public void persist(SolarSystemImportDto solarSystemImportDto) {
        SolarSystem solarSystem = new SolarSystem();
        if (solarSystemImportDto.getName() == null){
            throw new IllegalArgumentException("Solar system name cannot be null");
        }
        solarSystem.setName(solarSystemImportDto.getName());
        this.solarSystemRepository.saveAndFlush(solarSystem);
    }

    @Override
    public List<SolarSystem> getAll() {
        return this.solarSystemRepository.findAll();
    }

    @Override
    public SolarSystem getById(Long id) {
        return this.solarSystemRepository.findOne(id);
    }

    @Override
    public SolarSystem getByName(String name) {
        return this.solarSystemRepository.findByName(name);
    }
}
