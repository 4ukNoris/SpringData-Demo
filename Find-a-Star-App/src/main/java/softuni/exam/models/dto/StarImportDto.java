package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class StarImportDto {

    @Size(min = 2, max = 30)
    @NotNull
    private String name;

    @Positive
    @NotNull
    private Double lightYears;

    @Size(min = 6)
    @NotNull
    private String description;

    @NotNull
    private String starType;

    @NotNull
    private long constellation;

    public StarImportDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLightYears() {
        return lightYears;
    }

    public void setLightYears(Double lightYears) {
        this.lightYears = lightYears;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStarType() {
        return starType;
    }

    public void setStarType(String starType) {
        this.starType = starType;
    }

    public long getConstellation() {
        return constellation;
    }

    public void setConstellation(long constellation) {
        this.constellation = constellation;
    }
}
