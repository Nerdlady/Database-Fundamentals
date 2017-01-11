package app.services;

import app.domain.models.Anomaly;
import app.dtos.jsonDtos.inport.AnomalyImportDto;
import app.dtos.jsonDtos.inport.AnomalyVictimsImportDto;
import app.dtos.xmlDtos.inport.AnomalyWithVictimImportDto;

import javax.xml.bind.JAXBException;
import java.util.List;


public interface AnomalyService {
    void persist(AnomalyImportDto anomalyImportDto);

    void persist(AnomalyWithVictimImportDto anomalyWithVictimImportDto);

    List<Anomaly> getAll();

    Anomaly getById(Long id);

    void addVictimToAnomaly(AnomalyVictimsImportDto anomalyVictimsImportDto);

    void exportToJsonAnomalyWithMostVictims(String filePath);

    void exportToXmlAllAnomaliesWithVictims(String filePath) throws JAXBException;
}
