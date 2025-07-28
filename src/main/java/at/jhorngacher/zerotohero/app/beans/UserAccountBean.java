package at.jhorngacher.zerotohero.app.beans;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.jhorngacher.zerotohero.app.utils.UserMessages;
import at.jhorngacher.zerotohero.app.utils.FacesContextHelper;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import at.jhorngacher.zerotohero.app.models.UserAccount;

import java.io.IOException;
import java.io.Serializable;
import at.jhorngacher.zerotohero.app.dao.UserAccountDAO;

@Named
@SessionScoped
public class UserAccountBean extends UserMessages implements Serializable {

    private UserAccount account;
    private String accountEmail;
    private String accountName;
    private String accountPassword;
    private Boolean accountLoggedIn;

    public UserAccount getAccount(){
        return account;
    }

    public void setAccount(UserAccount account){
        this.account = account;
    }

    public String getAccountEmail(){
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail){
        this.accountEmail = accountEmail;
    }

    public String getAccountName(){
        return accountName;
    }

    public void setAccountName(String accountName){
        this.accountName = accountName;
    }

    public String getAccountPassword(){
        return null;
    }

    public void setAccountPassword(String accountPassword){
        this.accountPassword = accountPassword;
    }

    public Boolean getAccountLoggedIn(){
        return accountLoggedIn;
    }

    public void setAccountLoggedIn(Boolean accountLoggedIn){
        this.accountLoggedIn = accountLoggedIn;
    }



    public void login() {

        UserAccount temporaryAccount = new UserAccountDAO().loadAccount(accountEmail);

        // Wenn kein userAccount zurückgegeben wird, Fehlermeldung schmeißen und auf Login-Seite bleiben //
        if(temporaryAccount == null){
            setAccountLoggedIn(false);
            addErrorMessage("Fehler beim Login! Benutzerdaten prüfen!", "");
            return;
        }

        // Passwort prüfen mit Bcrypt
        // @https://github.com/patrickfav/bcrypt?tab=readme-ov-file
        BCrypt.Result result = BCrypt.verifyer().verify(accountPassword.toCharArray(), temporaryAccount.getAccountPassword().toCharArray());

        if(!result.verified){

            setAccountLoggedIn(false);
            addErrorMessage("Fehler beim Login! Benutzerdaten prüfen!", "");
            return;

        }

        setAccountLoggedIn(true);
        setAccountName(temporaryAccount.getAccountName());
        setAccountEmail(temporaryAccount.getAccountEMail());
        setAccount(temporaryAccount);

        redirect("index.xhtml");


    }

    public String logout() {

        if(getAccount() != null){

            invalidateSession("index.xhtml");
        }

        return null;

    }

    public String hashPassword(String password){

        return  BCrypt.withDefaults().hashToString(14, password.toCharArray());

    }

}