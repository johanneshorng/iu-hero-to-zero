package at.jhorngacher.jsftest.jsftest.models;

import java.time.Year;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;


/**
 *
 * CarbonData Model
 * -> speichert Daten zu CO2-Emissionen
 * -> für jede Nation gibt es seperate Einträge in der Datenbank
 * -> die Werte sind pro Jahr aggregiert
 * ->
 *
 */

@Entity
public class CarbonData{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dataId;

    private Year emissionYear;
    private String countryName;
    private String countryCode;
    private Integer countryPopulation;
    private Float carbonEmission;
    private Float carbonEmissionPerInhabitant;
    private Float carbonEmissionCumulated;
    private Integer author;
    private Date creationDate;

    public Integer getDataId(){
        return dataId;
    }

    // kein Setter da dies ein AI aus der DB ist!

    public Year getEmissionYear() {
        return emissionYear;
    }

    public void setEmissionYear(Year emissionYear) {
        this.emissionYear = emissionYear;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(Integer countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    public Float getCarbonEmission() {
        return carbonEmission;
    }

    public void setCarbonEmission(Float carbonEmission) {
        this.carbonEmission = carbonEmission;
    }

    public Float getCarbonEmissionPerInhabitant() {
        return carbonEmissionPerInhabitant;
    }

    public void setCarbonEmissionPerInhabitant(Float carbonEmission) {
        this.carbonEmissionPerInhabitant = carbonEmission / this.carbonEmission;
    }

    public Float getCarbonEmissionCumulated() {
        return carbonEmissionCumulated;
    }

    public void setCarbonEmissionCumulated(Float carbonEmissionCumulated) {
        this.carbonEmissionCumulated = carbonEmissionCumulated;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
