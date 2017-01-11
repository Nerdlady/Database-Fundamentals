package app.services;

import app.domain.models.Star;
import app.dtos.jsonDtos.inport.StarImportDto;

import java.util.List;

public interface StarService {
    void persist(StarImportDto starImportDto);

    List<Star> getAll();

    Star getById(Long id);

    Star getByName(String name);
}
