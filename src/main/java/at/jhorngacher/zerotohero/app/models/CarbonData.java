package at.jhorngacher.zerotohero.app.models;

import java.time.LocalDate;
import java.time.Year;
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
    private Long countryPopulation;
    private Double carbonEmission;
    private Double carbonEmissionPerInhabitant;
    private Integer author;
    private LocalDate creationDate;

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

    public Long getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(Long countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    public Double getCarbonEmission() {
        return carbonEmission;
    }

    public void setCarbonEmission(Double carbonEmission) {
        this.carbonEmission = carbonEmission;
    }

    public Double getCarbonEmissionPerInhabitant() {
        return carbonEmissionPerInhabitant;
    }

    public void setCarbonEmissionPerInhabitant(Double carbonEmission) {
        this.carbonEmissionPerInhabitant = carbonEmission / this.carbonEmission;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
