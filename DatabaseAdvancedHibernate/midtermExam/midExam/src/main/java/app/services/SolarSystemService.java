package app.services;

import app.domain.models.SolarSystem;
import app.dtos.jsonDtos.inport.SolarSystemImportDto;

import java.util.List;

public interface SolarSystemService {
    void persist(SolarSystemImportDto solarSystem);

    List<SolarSystem> getAll();

    SolarSystem getById(Long id);

    SolarSystem getByName(String name);

}
