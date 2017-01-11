package app.servicesImpl;

import app.domain.models.Anomaly;
import app.domain.models.Person;
import app.domain.models.Planet;
import app.dtos.jsonDtos.export.AnomalyExportDto;
import app.dtos.jsonDtos.export.PlanetExportDto;
import app.dtos.jsonDtos.inport.AnomalyImportDto;
import app.dtos.jsonDtos.inport.AnomalyVictimsImportDto;
import app.dtos.xmlDtos.export.AnomaliesExportDto;
import app.dtos.xmlDtos.export.AnomalyExportXmlDto;
import app.dtos.xmlDtos.export.VictimsExportDto;
import app.dtos.xmlDtos.inport.AnomalyWithVictimImportDto;
import app.dtos.xmlDtos.inport.VictimImportDto;
import app.parsers.json.JsonParser;
import app.parsers.json.JsonParserImpl;
import app.parsers.xml.XmlParser;
import app.parsers.xml.XmlParserImpl;
import app.repositories.AnomalyRepository;
import app.services.AnomalyService;
import app.services.PersonService;
import app.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AnomalyServiceImpl implements AnomalyService {

    private final AnomalyRepository anomalyRepository;
    private final PlanetService planetService;
    private final PersonService personService;
    private final JsonParser jsonParser;
    private final XmlParser xmlParser;

    @Autowired
    public AnomalyServiceImpl(AnomalyRepository anomalyRepository, PlanetService planetService, PersonService personService) {
        this.anomalyRepository = anomalyRepository;
        this.planetService = planetService;
        this.personService = personService;
        this.jsonParser = new JsonParserImpl();
        this.xmlParser = new XmlParserImpl();
    }

    @Override
    public void persist(AnomalyImportDto anomalyImportDto) {
        Planet originalPlanet = this.planetService.getByName(anomalyImportDto.getOriginPlanet());
        if (originalPlanet == null) {
            throw new IllegalArgumentException("Anomaly must have origin planet");
        }

        Planet teleportPlanet = this.planetService.getByName(anomalyImportDto.getTeleportPlanet());
        if (teleportPlanet == null) {
            throw new IllegalArgumentException("Anomaly must have teleprt planet");
        }

        Anomaly anomaly = new Anomaly();
        anomaly.setOriginPlanet(originalPlanet);
        anomaly.setTeleportPlanet(teleportPlanet);

        this.anomalyRepository.saveAndFlush(anomaly);
    }

    @Override
    public void persist(AnomalyWithVictimImportDto anomalyWithVictimImportDto) {

        Planet originPlanet = this.planetService.getByName(anomalyWithVictimImportDto.getOriginPlanet());
        if (originPlanet == null) {
            throw new IllegalArgumentException("Anomaly must have origin planet");
        }

        Planet teleportPlanet = this.planetService.getByName(anomalyWithVictimImportDto.getTeleportPlanet());
        if (teleportPlanet == null) {
            throw new IllegalArgumentException("Anomaly must have teleprt planet");
        }

        Set<Person> victims = new HashSet<>();
        for (VictimImportDto victimImportDto : anomalyWithVictimImportDto.getVictims()) {
            Person person = this.personService.getByName(victimImportDto.getName());
            if (person == null) {
                throw new IllegalArgumentException("No person with name: " + victimImportDto.getName());
            }
            victims.add(person);
        }

        Anomaly anomaly = new Anomaly();
        anomaly.setOriginPlanet(originPlanet);
        anomaly.setTeleportPlanet(teleportPlanet);
        anomaly.setVictims(victims);

        this.anomalyRepository.saveAndFlush(anomaly);

    }

    @Override
    public List<Anomaly> getAll() {
        return this.anomalyRepository.findAll();
    }

    @Override
    public Anomaly getById(Long id) {
        return this.anomalyRepository.findOne(id);
    }

    @Override
    public void addVictimToAnomaly(AnomalyVictimsImportDto anomalyVictimsImportDto) {
        Anomaly anomaly = this.getById(anomalyVictimsImportDto.getId());

        if (anomaly == null) {
            throw new IllegalArgumentException("No anomaly with id " + anomalyVictimsImportDto.getId());
        }

        Person person = this.personService.getByName(anomalyVictimsImportDto.getPerson());

        if (person == null) {
            throw new IllegalArgumentException("No person with name: " + anomalyVictimsImportDto.getPerson());
        }

        anomaly.addVictim(person);
        this.anomalyRepository.saveAndFlush(anomaly);
    }

    @Override
    public void exportToJsonAnomalyWithMostVictims(String filePath) {
        Anomaly anomaly = this.anomalyRepository.findByMaxVictims();
        AnomalyExportDto anomalyExportDto = new AnomalyExportDto();
        anomalyExportDto.setId(anomaly.getId());

        PlanetExportDto originalPlanet = new PlanetExportDto();
        originalPlanet.setName(anomaly.getOriginPlanet().getName());
        anomalyExportDto.setOriginPlanet(originalPlanet);

        PlanetExportDto teleportPlanet = new PlanetExportDto();
        teleportPlanet.setName(anomaly.getTeleportPlanet().getName());
        anomalyExportDto.setTeleportPlanet(teleportPlanet);

        anomalyExportDto.setVictimsCount(anomaly.getVictims().size());

        this.jsonParser.writeToJson(anomalyExportDto, filePath);

    }

    @Override
    public void exportToXmlAllAnomaliesWithVictims(String filePath) throws JAXBException {
        List<Anomaly> anomalies = this.anomalyRepository.findAll();

        List<AnomalyExportXmlDto> anomalyExportXmlDtos = new ArrayList<>();
        for (Anomaly anomaly : anomalies) {
            AnomalyExportXmlDto anomalyExportXmlDto = new AnomalyExportXmlDto();
            anomalyExportXmlDto.setId(anomaly.getId());
            anomalyExportXmlDto.setOriginPlanet(anomaly.getOriginPlanet().getName());
            anomalyExportXmlDto.setTeleportPlanet(anomaly.getTeleportPlanet().getName());

            List<VictimsExportDto> victimsExportDtos = new ArrayList<>();
            Set<Person> victims = anomaly.getVictims();

            if (victims == null) {
                continue;
            }

            for (Person person : anomaly.getVictims()) {
                VictimsExportDto victimsExportDto = new VictimsExportDto();
                victimsExportDto.setName(person.getName());
                victimsExportDtos.add(victimsExportDto);
            }

            anomalyExportXmlDto.setVictimsExportDtos(victimsExportDtos);
            anomalyExportXmlDtos.add(anomalyExportXmlDto);
        }

        AnomaliesExportDto anomaliesExportDto = new AnomaliesExportDto();
        anomaliesExportDto.setAnomalyExportDtos(anomalyExportXmlDtos);

        this.xmlParser.writeToXml(anomaliesExportDto, filePath);
    }
}
