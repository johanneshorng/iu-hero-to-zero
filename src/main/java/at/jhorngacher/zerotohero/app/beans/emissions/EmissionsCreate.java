package at.jhorngacher.zerotohero.app.beans.emissions;

import at.jhorngacher.zerotohero.app.dao.CarbonDataDAO;
import at.jhorngacher.zerotohero.app.utils.UserMessages;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import at.jhorngacher.zerotohero.app.models.CarbonData;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class EmissionsCreate extends UserMessages implements Serializable
{

    private String countryName;
    private String countryCode;
    private Integer emissionYear;
    private Double carbonEmission;
    private Long countryPopulation;
    private Integer author;
    private List<Integer> yearList;
    private List<String> countries;

    public void setEmissionYear(Integer emissionYear){
        this.emissionYear = emissionYear;
    }

    public void setCarbonEmission(Double carbonEmission){
        this.carbonEmission = carbonEmission;
    }

    public void setCountryPopulation(Long countryPopulation){
        this.countryPopulation = countryPopulation;
    }

    public void setAuthor(Integer author){
        this.author = author;
    }

    public void setYearList(List<Integer> yearList) {
        this.yearList = yearList;
    }

    public void setCountryName(String countryName){
        this.countryName = countryName;

        String cc = new CarbonDataDAO().getCountryCode(countryName);
        this.setCountryCode(cc);

    }

    public void setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }

    public void setCountries(List<String> countries) { this.countries = countries; }

    public Integer getEmissionYear(){
        return this.emissionYear;
    }

    public Double getCarbonEmission(){
        return this.carbonEmission;
    }

    public Long getCountryPopulation(){
        return this.countryPopulation;
    }

    public Integer getAuthor(){
        return this.author;
    }

    public List<Integer> getYearList() {
        return yearList;
    }

    public String getCountryName(){
        return this.countryName;
    }

    public String getCountryCode(){
        return this.countryCode;
    }

    public List<String> getCountries() {
        countries = new CarbonDataDAO().getCountryNames();
        return countries;
    }

    public void save(){

        /**
         *
         * Erstellen eines neuen Basis-Datenmodells
         *
         */
        CarbonData temp =  new CarbonData();
        temp.setCarbonEmission(this.carbonEmission);
        temp.setCountryPopulation(this.countryPopulation);
        temp.setAuthor(this.author);
        temp.setCountryName(this.countryName);
        temp.setCountryCode(this.countryCode);
        temp.setEmissionYear(this.emissionYear);
        temp.setCreationDate(LocalDate.now());


        Boolean success = new CarbonDataDAO().save(temp);

        if(!success){
            addErrorMessage("Speichern der Emissionsdaten fehlgeschlagen!", "");
        }

        // Response Message
        addSuccessMessage("Emissionsdaten wurden erfolgreich gespeichert", "");

    }

    @PostConstruct
    public void init(){

        yearList = new ArrayList<>();
        int currentYear = Year.now().getValue();
        for(int i = (currentYear-20); i <= currentYear; i++){
            yearList.add(i);
        }

    }

}
