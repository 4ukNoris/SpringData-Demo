package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "volcanologists")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistsImportXMLDto {

    @XmlElement(name = "volcanologist")
    private List<VolcanologistImportDto> volcanologists;

    public VolcanologistsImportXMLDto() {
    }

    public List<VolcanologistImportDto> getVolcanologists() {
        return volcanologists;
    }

    public void setVolcanologists(List<VolcanologistImportDto> volcanologists) {
        this.volcanologists = volcanologists;
    }
}
