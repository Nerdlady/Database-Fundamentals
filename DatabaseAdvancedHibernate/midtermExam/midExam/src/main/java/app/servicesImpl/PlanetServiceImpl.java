package app.servicesImpl;

import app.domain.models.Planet;
import app.domain.models.SolarSystem;
import app.domain.models.Star;
import app.dtos.jsonDtos.export.PlanetExportDto;
import app.dtos.jsonDtos.inport.PlanetImportDto;
import app.parsers.json.JsonParser;
import app.parsers.json.JsonParserImpl;
import app.repositories.PlanetRepository;
import app.services.PlanetService;
import app.services.SolarSystemService;
import app.services.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanetServiceImpl implements PlanetService {
    private final PlanetRepository planetRepository;
    private final SolarSystemService solarSystemService;
    private final StarService starService;
    private final JsonParser jsonParser;
    @Autowired
    public PlanetServiceImpl(PlanetRepository planetRepository,
                             SolarSystemService solarSystemService,
                             StarService starService) {
        this.planetRepository = planetRepository;
        this.solarSystemService = solarSystemService;
        this.starService = starService;
        this.jsonParser = new JsonParserImpl();
    }

    @Override
    public void persist(PlanetImportDto planetImportDto) {
        if (planetImportDto.getName() == null){
            throw new IllegalArgumentException("Planet name cannot be null");
        }

        Star star = this.starService.getByName(planetImportDto.getSun());

        if(star == null){
            throw new IllegalArgumentException("Planet must have sun");
        }

        SolarSystem solarSystem = this.solarSystemService.getByName(planetImportDto.getSolarSystem());

        if (solarSystem == null){
            throw new IllegalArgumentException("Planet must have solar system");
        }

        Planet planet = new Planet();
        planet.setName(planetImportDto.getName());
        planet.setSun(star);
        planet.setSolarSystem(solarSystem);

        this.planetRepository.saveAndFlush(planet);
    }

    @Override
    public List<Planet> getAll() {
        return this.planetRepository.findAll();
    }

    @Override
    public Planet getById(Long id) {
        return this.planetRepository.findOne(id);
    }

    @Override
    public Planet getByName(String name) {
        return this.planetRepository.findByName(name);
    }

    @Override
    public void exportToJsonPlanetsWithNoOriginalAnomaly(String filePath) {
        List<Planet> planets = this.planetRepository.findAllByOrOriginalAnomalyIsNull();
        List<PlanetExportDto> planetDtos = new ArrayList<>();
        for (Planet planet : planets) {
            PlanetExportDto planetDto = new PlanetExportDto();
            planetDto.setName(planet.getName());
            planetDtos.add(planetDto);
        }
        this.jsonParser.writeToJson(planetDtos,filePath);
    }
}
