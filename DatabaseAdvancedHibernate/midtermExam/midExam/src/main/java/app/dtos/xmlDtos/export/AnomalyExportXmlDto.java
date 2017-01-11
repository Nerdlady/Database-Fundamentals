package app.dtos.xmlDtos.export;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "anomaly")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomalyExportXmlDto {

    @XmlAttribute(name = "id")
    private Long id;

    @XmlAttribute(name = "origin-planet")
    private String originPlanet;

    @XmlAttribute(name = "teleport-planet")
    private String teleportPlanet;

    @XmlElementWrapper(name = "victims")
    @XmlElement(name = "victim")
    private List<VictimsExportDto> victimsExportDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(String originPlanet) {
        this.originPlanet = originPlanet;
    }

    public String getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(String teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public List<VictimsExportDto> getVictimsExportDtos() {
        return victimsExportDtos;
    }

    public void setVictimsExportDtos(List<VictimsExportDto> victimsExportDtos) {
        this.victimsExportDtos = victimsExportDtos;
    }
}
