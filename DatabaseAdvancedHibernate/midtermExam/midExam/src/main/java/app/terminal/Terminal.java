package app.terminal;

import app.dtos.jsonDtos.inport.*;
import app.dtos.xmlDtos.inport.AnomaliesImportDto;
import app.dtos.xmlDtos.inport.AnomalyWithVictimImportDto;
import app.parsers.json.JsonParser;
import app.parsers.json.JsonParserImpl;
import app.parsers.xml.XmlParser;
import app.parsers.xml.XmlParserImpl;
import app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;

@Component
@Transactional
public class Terminal implements CommandLineRunner {
    private static final String RESOURCES_INPUT_JSON_PATH = "/files/input/json/";
    private static final String RESOURCES_INPUT_XML_PATH = "/files/input/xml/";

    private static final String RESOURCES_OUTPUT_JSON_PATH = "src/main/resources/files/output/json/";
    private static final String RESOURCES_OUTPUT_XML_PATH = "src/main/resources/files/output/xml/";

    private final SolarSystemService solarSystemService;
    private final StarService starService;
    private final PlanetService planetService;
    private final PersonService personService;
    private final AnomalyService anomalyService;
    private final JsonParser jsonParser;

    @Autowired
    public Terminal(SolarSystemService solarSystemService,
                    StarService starService,
                    PlanetService planetService,
                    PersonService personService,
                    AnomalyService anomalyService) {
        this.solarSystemService = solarSystemService;
        this.starService = starService;
        this.planetService = planetService;
        this.personService = personService;
        this.anomalyService = anomalyService;
        this.jsonParser = new JsonParserImpl();
    }

    @Override
    public void run(String... strings) throws Exception {
        this.solarSystemsFromJson();
        this.starsFromJson();
        this.planetsFromJson();
        this.peopleFromJson();
        this.anomaliesFromJson();
        this.anomalyVictimsFromJson();
        this.anomalyVictimsFromXml();
        this.planetService.exportToJsonPlanetsWithNoOriginalAnomaly(RESOURCES_OUTPUT_JSON_PATH + "planets-with-no-original-anomaly.json");
        this.personService.exportToJsonPeopleNotBeenVictims(RESOURCES_OUTPUT_JSON_PATH + "person-not-been-victims.json");
        this.anomalyService.exportToJsonAnomalyWithMostVictims(RESOURCES_OUTPUT_JSON_PATH + "anomaly-with-most-victims.json");
        this.anomalyService.exportToXmlAllAnomaliesWithVictims(RESOURCES_OUTPUT_XML_PATH + "all-anomalies.xml");

    }

    private void solarSystemsFromJson() {
        String path = RESOURCES_INPUT_JSON_PATH + "solar-systems.json";

        SolarSystemImportDto[] solarSystemImportDtos = null;
        solarSystemImportDtos = this.jsonParser.readFromJson(SolarSystemImportDto[].class, path);

        for (SolarSystemImportDto solarSystemImportDto : solarSystemImportDtos) {
            try {
                this.solarSystemService.persist(solarSystemImportDto);
                this.printSuccessDataInsert("Solar System", solarSystemImportDto.getName());
            } catch (IllegalArgumentException e) {
                this.printError();
            }
        }
    }

    private void starsFromJson() {
        String path = RESOURCES_INPUT_JSON_PATH + "stars.json";

        StarImportDto[] starImportDtos = null;
        starImportDtos = this.jsonParser.readFromJson(StarImportDto[].class, path);

        for (StarImportDto starImportDto : starImportDtos) {
            try {
                this.starService.persist(starImportDto);
                this.printSuccessDataInsert("Star", starImportDto.getName());
            } catch (IllegalArgumentException e) {
                this.printError();
            }
        }
    }

    private void planetsFromJson() {
        String path = RESOURCES_INPUT_JSON_PATH + "planets.json";

        PlanetImportDto[] planetImportDtos = null;
        planetImportDtos = this.jsonParser.readFromJson(PlanetImportDto[].class, path);

        for (PlanetImportDto planetImportDto : planetImportDtos) {
            try {
                this.planetService.persist(planetImportDto);
                this.printSuccessDataInsert("Planet", planetImportDto.getName());
            } catch (IllegalArgumentException e) {
                this.printError();
            }
        }
    }

    private void peopleFromJson() {
        String path = RESOURCES_INPUT_JSON_PATH + "persons.json";

        PersonImportDto[] personImportDtos = null;
        personImportDtos = this.jsonParser.readFromJson(PersonImportDto[].class, path);

        for (PersonImportDto personImportDto : personImportDtos) {
            try {
                this.personService.persist(personImportDto);
                this.printSuccessDataInsert("Person", personImportDto.getName());
            } catch (IllegalArgumentException e) {
                this.printError();
            }
        }
    }

    private void anomaliesFromJson() {
        String path = RESOURCES_INPUT_JSON_PATH + "anomalies.json";

        AnomalyImportDto[] anomalyImportDtos = null;
        anomalyImportDtos = this.jsonParser.readFromJson(AnomalyImportDto[].class, path);

        for (AnomalyImportDto anomalyImportDto : anomalyImportDtos) {
            try {
                this.anomalyService.persist(anomalyImportDto);
                System.out.println("Successfully imported anomaly");
            } catch (IllegalArgumentException e) {
                this.printError();
            }
        }
    }

    private void anomalyVictimsFromJson() {
        String path = RESOURCES_INPUT_JSON_PATH + "anomaly-victims.json";

        AnomalyVictimsImportDto[] anomalyVictimsImportDtos = null;
        anomalyVictimsImportDtos = this.jsonParser.readFromJson(AnomalyVictimsImportDto[].class, path);

        for (AnomalyVictimsImportDto anomalyVictimsImportDto : anomalyVictimsImportDtos) {
            try {
                this.anomalyService.addVictimToAnomaly(anomalyVictimsImportDto);
            } catch (IllegalArgumentException e) {
                this.printError();
            }
        }
    }

    private void anomalyVictimsFromXml() throws JAXBException {
        String path = RESOURCES_INPUT_XML_PATH + "new-anomalies.xml";

        XmlParser xmlParser = new XmlParserImpl();

        AnomaliesImportDto anomaliesImportDtos = null;
        anomaliesImportDtos = xmlParser.readFromXml(AnomaliesImportDto.class, path);

        for (AnomalyWithVictimImportDto anomalyWithVictimImportDto : anomaliesImportDtos.getAnomalies()) {
            try {
                this.anomalyService.persist(anomalyWithVictimImportDto);

                System.out.println("Successfully imported data");
            } catch (IllegalArgumentException e) {
                this.printError();
            }
        }
    }

    private void printError() {
        System.out.println("Error: Invalid data.");
    }

    private void printSuccessDataInsert(String type, String name) {
        System.out.printf("Successfully imported %s %s.%n", type, name);
    }
}
