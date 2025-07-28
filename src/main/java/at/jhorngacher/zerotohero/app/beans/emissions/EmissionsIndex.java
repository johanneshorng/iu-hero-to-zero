package at.jhorngacher.zerotohero.app.beans.emissions;

import at.jhorngacher.zerotohero.app.dao.CarbonDataDAO;
import at.jhorngacher.zerotohero.app.models.CarbonData;
import at.jhorngacher.zerotohero.app.utils.UserMessages;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class EmissionsIndex extends UserMessages implements Serializable {

    private List<CarbonData> results;

    public List<CarbonData> getResults() {

        results = new CarbonDataDAO().findAll();
        return results;

    }

    public void setResults(List<CarbonData> results) {
        this.results = results;
    }

}
