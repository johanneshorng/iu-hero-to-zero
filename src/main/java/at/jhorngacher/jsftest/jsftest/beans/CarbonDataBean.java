package at.jhorngacher.jsftest.jsftest.beans;

import at.jhorngacher.jsftest.jsftest.models.CarbonData;
import jakarta.annotation.ManagedBean;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

import at.jhorngacher.jsftest.jsftest.dao.CarbonDataDAO;

@Named
@ViewScoped
public class CarbonDataBean implements Serializable {

   private  CarbonData selectedCountry;
   private List<String> countries;
   private String selectedCountryName;


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

        System.out.println(selectedCountryName);
        selectedCountry = new CarbonDataDAO().getSelectedCountry(selectedCountryName);
        System.out.println(selectedCountry);

    }

    public String getSelectedCountryName() {
       return selectedCountryName;
    }

    public void setSelectedCountryName(String selectedCountryName) {
       this.selectedCountryName = selectedCountryName;
    }

}
