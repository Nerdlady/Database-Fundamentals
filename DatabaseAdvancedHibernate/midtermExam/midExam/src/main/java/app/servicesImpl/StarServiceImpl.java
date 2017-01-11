package app.servicesImpl;

import app.domain.models.SolarSystem;
import app.domain.models.Star;
import app.dtos.jsonDtos.inport.StarImportDto;
import app.repositories.StarRepository;
import app.services.SolarSystemService;
import app.services.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarServiceImpl implements StarService {
    private final StarRepository starRepository;
    private final SolarSystemService solarSystemService;

    @Autowired
    public StarServiceImpl(StarRepository starRepository, SolarSystemService solarSystemService) {
        this.starRepository = starRepository;
        this.solarSystemService = solarSystemService;
    }

    @Override
    public void persist(StarImportDto starImportDto) {
        if (starImportDto.getName() == null) {
            throw new IllegalArgumentException("Star name cannot be null");
        }
        SolarSystem solarSystem = this.solarSystemService.getByName(starImportDto.getSolarSystem());
        if (solarSystem == null) {
            throw new IllegalArgumentException("Star must have solar system");
        }

        Star star = new Star();
        star.setName(starImportDto.getName());
        star.setSolarSystem(solarSystem);

        this.starRepository.saveAndFlush(star);
    }

    @Override
    public List<Star> getAll() {
        return this.starRepository.findAll();
    }

    @Override
    public Star getById(Long id) {
        return this.starRepository.findOne(id);
    }

    @Override
    public Star getByName(String name) {
        return this.starRepository.findByName(name);
    }
}
