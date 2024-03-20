package softuni.exam.models.entity;

import softuni.exam.models.enums.StarType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stars")
public class Star extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "light_years", nullable = false)
    private Double lightYears;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "star_type", nullable = false)
    private StarType starType;

    @ManyToOne
    private Constellation constellation;

    @OneToMany(mappedBy = "observingStar", fetch = FetchType.EAGER)
    private Set<Astronomer> observers = new HashSet<>();

    public Star() {}

    public Star(String name, Double lightYears, String description, StarType starType, Constellation constellation) {
        this.name = name;
        this.lightYears = lightYears;
        this.description = description;
        this.starType = starType;
        this.constellation = constellation;
    }

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

    public StarType getStarType() {
        return starType;
    }

    public void setStarType(StarType starType) {
        this.starType = starType;
    }

    public Constellation getConstellation() {
        return constellation;
    }

    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }

    public Set<Astronomer> getObservers() {
        return observers;
    }

    public void setObservers(Set<Astronomer> observers) {
        this.observers = observers;
    }
}
