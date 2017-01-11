package app.services;

import app.domain.models.Person;
import app.dtos.jsonDtos.inport.PersonImportDto;

import java.util.List;

public interface PersonService {
    void persist(PersonImportDto personImportDto);

    List<Person> getAll();

    Person getById(Long id);

    Person getByName(String name);

    void exportToJsonPeopleNotBeenVictims(String filePath);
}
