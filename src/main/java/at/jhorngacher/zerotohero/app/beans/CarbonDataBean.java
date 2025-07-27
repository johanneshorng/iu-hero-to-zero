package at.jhorngacher.zerotohero.app.beans;

import at.jhorngacher.zerotohero.app.models.CarbonData;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import at.jhorngacher.zerotohero.app.dao.CarbonDataDAO;

@Named
@ViewScoped
public class CarbonDataBean implements Serializable {

   private  CarbonData selectedCountry;
   private List<String> countries;
   private String selectedCountryName;
   private CarbonData carbonData;

    /**
     * Eingabefelder zur Erstellung eines neuen ModelEintrags ( andere Felder sind nicht manuell zu pflegen )
     */
    private Integer emissionYear;
    private String countryName;
    private String countryCode;
    private Long countryPopulation;
    private Double carbonEmission;
    private Double carbonEmisisonPerInhabitant;
    private Integer authorId;

   public List<String> getCountries() {

        countries = new CarbonDataDAO().getCountryNames();
        return countries;

    }

    public void setCountries(List<String> countries) {

        this.countries = countries;

    }

    public CarbonData getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(CarbonData selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public void loadCountry(){

        selectedCountry = new CarbonDataDAO().getSelectedCountry(selectedCountryName);

    }

    public String getSelectedCountryName() {
       return selectedCountryName;
    }

    public void setSelectedCountryName(String selectedCountryName) {
       this.selectedCountryName = selectedCountryName;
    }

    public CarbonData getCarbonData() {
       return carbonData;
    }

    public void setCarbonData(CarbonData carbonData) {
       this.carbonData = carbonData;
    }

    /**
     *
     * Allgemeine Speicher-Methode zum Erstellen eines neues carbonData Models
     * -> hier erfolgen außerdem die Berechnung der kumulierten CO2 Emissionen sowie die CO2 Emissionen pro Einwohner
     * @return
     */
    public String save(){
        CarbonData carbonData = new CarbonData();
        carbonData.setCountryName(countryName);
        carbonData.setCountryCode(countryCode);
        carbonData.setCountryPopulation(countryPopulation);
        carbonData.setCarbonEmission(carbonEmission);
        carbonData.setAuthor(authorId);

        LocalDate creationDate = LocalDate.now();
        carbonData.setCreationDate(creationDate);

        /**
         * Emission pro Einwohner
         */
        carbonEmisisonPerInhabitant = carbonEmission / countryPopulation;
        carbonData.setCarbonEmissionPerInhabitant(carbonEmisisonPerInhabitant);

        /**
         *
         * Prüfe ob bereits ein Eintrag mit dem gewählten Jahr existiert
         */


        //Boolean result = new CarbonDataDAO().save(carbonData);

       return null;

    }

    public String getCountryName(){
       return countryName;
    }

    public void setCountryName(String countryName) {
       this.countryName = countryName;

       // Wir setzten sobald der countryName geupdated wurde automatisch den Code ( = laden aus der DB )
       String cc = new CarbonDataDAO().getCountryCode(countryName);
       if(!cc.isEmpty()){
           setCountryCode(cc);
       }
    }

    public void setEmissionYear(Integer emissionYear) {
       this.emissionYear = emissionYear;
    }

    public Year getEmissionYear() {
       return emissionYear != null ? Year.of(emissionYear) : null;
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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    // Getter & Setter für carbonEmissionCumulated & carbonEmissionPerInhabitant sind nicht notwendig - diese werden in der Backendlogik kalkuliert

}
