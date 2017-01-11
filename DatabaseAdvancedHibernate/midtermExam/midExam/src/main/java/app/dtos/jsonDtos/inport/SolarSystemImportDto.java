package app.dtos.jsonDtos.inport;

import java.io.Serializable;

public class SolarSystemImportDto implements Serializable {

    private String name;

    public SolarSystemImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
