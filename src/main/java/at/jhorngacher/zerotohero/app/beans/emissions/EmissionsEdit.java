package at.jhorngacher.zerotohero.app.beans.emissions;

import at.jhorngacher.zerotohero.app.dao.CarbonDataDAO;
import at.jhorngacher.zerotohero.app.models.CarbonData;
import at.jhorngacher.zerotohero.app.utils.UserMessages;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Map;

@Named
@ViewScoped
public class EmissionsEdit extends UserMessages implements Serializable {

    private Long id;
    private CarbonData editable;
    private Integer author;
    private Double carbonEmission;
    private Long countryPopulation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarbonData getEditable() {
        return editable;
    }

    public void setEditable(CarbonData editable) {
        this.editable = editable;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Double getCarbonEmission() {
        return carbonEmission;
    }

    public void setCarbonEmission(Double carbonEmission) {
        this.carbonEmission = carbonEmission;
    }

    public Long getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(Long countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    @PostConstruct
    public void init() {

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        String paramId = params.get("id");
        if(paramId != null){
            id = Long.parseLong(paramId);
            editable = new CarbonDataDAO().findById(id);
        }

        if(editable == null){
            addErrorMessage("Kein Datensatz zur angegeben ID gefunden!", "");
            redirect("/emissions/index.xhtml");
        }

    }

    public void save(){

        editable.setAuthor(author);

        Boolean response = new  CarbonDataDAO().update(editable);

        if(!response){
            addErrorMessage("Fehler beim Speichern des Datensatzes", "");
        }
        addSuccessMessage("Datensatz erfolgreich geupdated!","");

    }
}
