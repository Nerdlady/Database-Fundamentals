package app.servicesImpl;

import app.domain.models.Person;
import app.domain.models.Planet;
import app.dtos.jsonDtos.export.PersonExportDto;
import app.dtos.jsonDtos.export.PlanetExportDto;
import app.dtos.jsonDtos.inport.PersonImportDto;
import app.parsers.json.JsonParser;
import app.parsers.json.JsonParserImpl;
import app.repositories.PersonRepository;
import app.services.PersonService;
import app.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PlanetService planetService;
    private final JsonParser jsonParser;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PlanetService planetService) {
        this.personRepository = personRepository;
        this.planetService = planetService;
        this.jsonParser = new JsonParserImpl();
    }

    @Override
    public void persist(PersonImportDto personImportDto) {
        if (personImportDto.getName() == null) {
            throw new IllegalArgumentException("Person name cannot be null");
        }
        Planet homePlanet = this.planetService.getByName(personImportDto.getHomePlanet());
        if (homePlanet == null) {
            throw new IllegalArgumentException("Person must have home planet");
        }

        Person person = new Person();
        person.setName(personImportDto.getName());
        person.setHomePlanet(homePlanet);

        this.personRepository.saveAndFlush(person);
    }

    @Override
    public List<Person> getAll() {
        return this.personRepository.findAll();
    }

    @Override
    public Person getById(Long id) {
        return this.personRepository.findOne(id);
    }

    @Override
    public Person getByName(String name) {
        return this.personRepository.findByName(name);
    }

    @Override
    public void exportToJsonPeopleNotBeenVictims(String filePath) {
        List<Person> people = this.personRepository.findByAnomaliesIsNull();
        List<PersonExportDto> personDtos = new ArrayList<>();
        for (Person person : people) {
            PersonExportDto personDto = new PersonExportDto();
            personDto.setName(person.getName());
            PlanetExportDto planetExportDto = new PlanetExportDto();
            planetExportDto.setName(person.getHomePlanet().getName());
            personDto.setHomePlanet(planetExportDto);
            personDtos.add(personDto);
        }

        this.jsonParser.writeToJson(personDtos,filePath);
    }
}
