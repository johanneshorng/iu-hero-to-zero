package at.jhorngacher.zerotohero.app.beans;

import at.jhorngacher.zerotohero.app.models.CarbonData;
import at.jhorngacher.zerotohero.app.utils.UserMessages;
import at.jhorngacher.zerotohero.app.dao.CarbonDataDAO;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class Index extends UserMessages implements Serializable {

    /**
     *  CarbonData selectedCountry = 1 Datensatz zu einem ausgewählten Land -> index.xhtml
     */
    private  CarbonData selectedCountry;

    /**
     *  List<countryNames> = Liste mit allen Ländernnamen die lt. Datenbank bereits verfügbar sind!
     */
    private List<String> countries;

    /**
     * List<CarbonData> = Liste mit Einträgen
     */

    /**
     * String selectedCountryName = Der Name des Landes von welchem ein CarbonData Datensatz geladen werden sol
     */
    private String selectedCountryName;

    /**
     *
     * Getter Methoden
     *
     */
    public List<String> getCountries() {
        countries = new CarbonDataDAO().getCountryNames();
        return countries;
    }

    public CarbonData getSelectedCountry(){
        return selectedCountry;
    }

    public String getSelectedCountryName(){
        return selectedCountryName;
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

    public void setCountries(List<String> countries) { this.countries = countries; }

    /**
     *
     * Utility Methoden
     * -> Aufrufe an Dataobject in /dao
     *
     */
    public void loadCountry(){

        selectedCountry = new CarbonDataDAO().getSelectedCountry(selectedCountryName);

    }

}
