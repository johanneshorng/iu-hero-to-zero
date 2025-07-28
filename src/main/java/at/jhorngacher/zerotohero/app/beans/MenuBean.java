package at.jhorngacher.zerotohero.app.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.util.Map;

@Named
@RequestScoped
public class MenuBean {

    public boolean isActive(String viewId){

        String currentViewId = FacesContext.getCurrentInstance()
                .getViewRoot().getViewId();

        return currentViewId != null && currentViewId.contains(viewId);

    }

    public String linkClass(String viewId){

        boolean active = isActive(viewId);

        if(active){
            return "text-slate-900 font-semibold border-b-2 border-slate-500";
        }

        return "text-slate-700 hover:text-slate-900 font-medium hover:border-b-2 hover:border-slate-500";

    }

}
