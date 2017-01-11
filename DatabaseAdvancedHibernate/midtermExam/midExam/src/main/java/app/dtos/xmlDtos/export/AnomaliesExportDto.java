package app.dtos.xmlDtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "anomalies")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomaliesExportDto implements Serializable {

    @XmlElement(name = "anomaly")
    List<AnomalyExportXmlDto> anomalyExportDtos;

    public AnomaliesExportDto() {
        this.anomalyExportDtos = new ArrayList<>();
    }

    public List<AnomalyExportXmlDto> getAnomalyExportDtos() {
        return anomalyExportDtos;
    }

    public void setAnomalyExportDtos(List<AnomalyExportXmlDto> anomalyExportDtos) {
        this.anomalyExportDtos = anomalyExportDtos;
    }
}
