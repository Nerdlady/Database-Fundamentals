package app.dtos.jsonDtos.export;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class AnomalyExportDto implements Serializable {

    @Expose
    private Long id;

    @Expose
    private PlanetExportDto originPlanet;

    @Expose
    private PlanetExportDto teleportPlanet;

    @Expose
    private Integer victimsCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanetExportDto getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(PlanetExportDto originPlanet) {
        this.originPlanet = originPlanet;
    }

    public PlanetExportDto getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(PlanetExportDto teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public Integer getVictimsCount() {
        return victimsCount;
    }

    public void setVictimsCount(Integer victimsCount) {
        this.victimsCount = victimsCount;
    }
}
