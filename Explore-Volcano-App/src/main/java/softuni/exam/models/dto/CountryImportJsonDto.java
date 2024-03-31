package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryImportJsonDto {

    @NotNull
    @Size(min = 3, max = 30)
    private String name;
    @Size(min = 3, max = 30)
    private String capital;

    public CountryImportJsonDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
