package at.jhorngacher.zerotohero.app.beans;

import at.jhorngacher.zerotohero.app.models.CarbonData;
import at.jhorngacher.zerotohero.app.utils.ErrorMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import at.jhorngacher.zerotohero.app.dao.CarbonDataDAO;

@Named
@ViewScoped
public class CarbonDataBean extends ErrorMessage implements Serializable {

    /**
     *  CarbonData selectedCountry = 1 Datensatz zu einem ausgewählten Land -> index.xhtml
     */
    private  CarbonData selectedCountry;

    /**
     *  List<countryNames> = Liste mit allen Ländernnamen die lt. Datenbank bereits verfügbar sind!
     */
    private List<String> countries;

    /**
     * String selectedCountryName = Der Name des Landes von welchem ein CarbonData Datensatz geladen werden sol
     */
    private String selectedCountryName;

    /**
     *
     * CarbonData Model Properties um einen neuen Eintrag in der Datenbank zu erstellen
     *
     */
    private Integer emissionYear; // emissionYear muss in diesem Kontext ein Integer sein obwohl es im Datenmodel als Year typisiert ist -> JSF/Primefaces Datepicker unterstützen Java Year nicht!
    private String countryName;
    private String countryCode;
    private Long countryPopulation;
    private Double carbonEmission;
    private Integer author;
    private CarbonData carbonDataModel;

    /**
     *
     * Getter Methoden
     *
     */
    public List<String> getCountries() {
        countries = new CarbonDataDAO().getCountryNames();
        return countries;
    }

    public CarbonData getSelectedCountr(){
        return selectedCountry;
    }

    public String getSelectedCountryName(){
        return selectedCountryName;
    }

    public String getCountryName(){
        return countryName;
    }

    public String getCountryCode(){
        return countryCode;
    }

    public Long getCountryPopulation(){
        return countryPopulation;
    }

    public Double getCarbonEmission(){
        return carbonEmission;
    }

    public Integer getAuthor(){
        return author;
    }

    /**
     *
     * Setter Methoden
     *
     */
    public void setSelectedCountry(CarbonData selectedCountry){
        this.selectedCountry = selectedCountry;
    }

    public void setSelectedCountryName(String selectedCountryName){
         this.selectedCountryName = selectedCountryName;
    }

    public void setEmissionYear(Integer emissionYear){
        this.emissionYear = emissionYear;
    }

    public void setCountryName(String countryName){
        this.countryName = countryName;

        String cc = new CarbonDataDAO().getCountryCode(countryName);

        this.setCountryCode(cc);

    }

    public void setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }

    public void setCarbonEmission(Double carbonEmission){
        this.carbonEmission = carbonEmission;
    }

    public void setCountries(List<String> countries) {

        this.countries = countries;

    }

    /**
     *
     * Utility Methoden
     * -> Aufrufe an Dataobject in /dao
     *
     */
    public void loadCountry(){

        selectedCountry = new CarbonDataDAO().getSelectedCountry(selectedCountryName);

    }

    public void saveData(){

        /**
         *
         * Erstellen eines neuen Basis-Datenmodells
         *
         */
        carbonDataModel = new CarbonData();
        carbonDataModel.setCountryName(countryName);
        carbonDataModel.setCountryCode(countryCode);
        carbonDataModel.setCountryPopulation(countryPopulation);
        carbonDataModel.setCarbonEmission(carbonEmission);
        carbonDataModel.setAuthor(author);
        carbonDataModel.setCreationDate(LocalDate.now());

        Boolean success = new CarbonDataDAO().save(carbonDataModel);

        if(!success){
            addErrorMessage("Speicherfehler", "Das Speichern der Emissionsdaten ist fehlgeschlagen!");
        }




    }

}
