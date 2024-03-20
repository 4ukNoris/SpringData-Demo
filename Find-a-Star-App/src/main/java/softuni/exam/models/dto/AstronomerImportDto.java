package softuni.exam.models.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "astronomer")
@XmlAccessorType(XmlAccessType.FIELD)
public class AstronomerImportDto {

    @XmlElement(name = "average_observation_hours")
    private double averageObservationHours;

    @XmlElement
    private String birthday;

    @XmlElement(name = "first_name")
    private String firstName;

    @XmlElement(name = "last_name")
    private String lastName;

    @XmlElement
    private double salary;

    @XmlElement(name = "observing_star_id")
    private long starId;

    public AstronomerImportDto() {}

    public AstronomerImportDto(double averageObservationHours, String birthday, String firstName, String lastName, double salary, long starId) {
        this.averageObservationHours = averageObservationHours;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.starId = starId;
    }
    @DecimalMin(value = "500.00")
    @NotNull
    public double getAverageObservationHours() {
        return averageObservationHours;
    }

    public void setAverageObservationHours(double averageObservationHours) {
        this.averageObservationHours = averageObservationHours;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Size(min = 2, max = 30)
    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(min = 2, max = 30)
    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DecimalMin(value = "15000.00")
    @NotNull
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public long getStarId() {
        return starId;
    }

    public void setStarId(long starId) {
        this.starId = starId;
    }

}
