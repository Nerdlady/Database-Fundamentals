package app.dtos.jsonDtos.export;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class PersonExportDto implements Serializable {
    @Expose
    private String name;

    @Expose
    private PlanetExportDto homePlanet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlanetExportDto getHomePlanet() {
        return homePlanet;
    }

    public void setHomePlanet(PlanetExportDto homePlanet) {
        this.homePlanet = homePlanet;
    }
}
