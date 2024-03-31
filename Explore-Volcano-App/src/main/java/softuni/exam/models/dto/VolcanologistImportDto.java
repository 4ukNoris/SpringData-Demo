package softuni.exam.models.dto;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "volcanologist")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistImportDto {

    @XmlElement(name = "first_name")
    private String firstName;
    @XmlElement(name = "last_name")
    private String lastName;
    @XmlElement
    private double salary;
    @XmlElement
    private int age;
    @XmlElement(name = "exploring_from")
    private String exploringFrom;
    @XmlElement(name = "exploring_volcano_id")
    private long exploringVolcanoId;

    public VolcanologistImportDto() {
    }

    @NotNull
    @Size(min = 2, max = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotNull
    @Size(min = 2, max = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Positive
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @NotNull
    @Min(value = 18)
    @Max(value = 80)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getExploringFrom() {
        return exploringFrom;
    }

    public void setExploringFrom(String exploringFrom) {
        this.exploringFrom = exploringFrom;
    }

    public long getExploringVolcanoId() {
        return exploringVolcanoId;
    }

    public void setExploringVolcanoId(long exploringVolcanoId) {
        this.exploringVolcanoId = exploringVolcanoId;
    }
}
