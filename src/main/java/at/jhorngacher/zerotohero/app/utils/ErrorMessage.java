package at.jhorngacher.zerotohero.app.utils;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public class ErrorMessage extends FacesContextHelper {
    public void addErrorMessage(String title, String message){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message));
    }
}
