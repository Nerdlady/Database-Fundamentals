package app.services;

import app.domain.models.Planet;
import app.dtos.jsonDtos.inport.PlanetImportDto;

import java.util.List;

public interface PlanetService {
    void persist(PlanetImportDto planetImportDto);

    List<Planet> getAll();

    Planet getById(Long id);

    Planet getByName(String name);

    void exportToJsonPlanetsWithNoOriginalAnomaly(String filePath);


}
