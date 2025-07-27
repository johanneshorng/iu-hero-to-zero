package at.jhorngacher.zerotohero.app.utils;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.IOException;

public class FacesContextHelper {

   public void invalidateSession(String redirectUri){
       FacesContext context = FacesContext.getCurrentInstance();
       context.getExternalContext().invalidateSession();

       redirect(redirectUri);
   }

   public void redirect(String redirectUri){

       if(redirectUri.isEmpty()){
           redirectUri = "/";
       }

       FacesContext context = FacesContext.getCurrentInstance();
       try{
            context.getExternalContext().redirect(redirectUri);
       }  catch (IOException e) {
           throw new RuntimeException(e);
       }

   }

}
