package at.jhorngacher.jsftest.jsftest.beans;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import at.jhorngacher.jsftest.jsftest.models.UserAccount;

import java.io.IOException;
import java.io.Serializable;
import at.jhorngacher.jsftest.jsftest.dao.UserAccountDAO;

@Named
@SessionScoped
public class UserAccountBean implements Serializable {

    private UserAccount account;
    private String accountEmail;
    private String accountName;
    private String accountPassword;
    private Boolean accountLoggedIn;

    public UserAccount getUserAccount(){
        return account;
    }

    public void setUserAccount(UserAccount account){
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
            addErrorMessage("Accountfehler", "Es existiert kein Account mit den angegeben Zugangsdaten");
            return;
        }

        // Passwort prüfen mit Bcrypt
        // @https://github.com/patrickfav/bcrypt?tab=readme-ov-file
        BCrypt.Result result = BCrypt.verifyer().verify(accountPassword.toCharArray(), temporaryAccount.getAccountPassword().toCharArray());

        if(!result.verified){

            setAccountLoggedIn(false);
            addErrorMessage("Accountfehler", "Das eingegebene Passwort ist nicht gültig!");
            return;

        }

        setAccountLoggedIn(true);
        setAccountName(temporaryAccount.getAccountName());
        setAccountEmail(temporaryAccount.getAccountEMail());
        setUserAccount(temporaryAccount);

        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("index.xhtml");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public String logout() {

        if(getUserAccount() != null){

            setAccountLoggedIn(false);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().invalidateSession();
            try{
                context.getExternalContext().redirect("index.xhtml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        return null;

    }

    private void addErrorMessage(String title, String message){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message));
    }

    public String hashPassword(String password){

        return  BCrypt.withDefaults().hashToString(14, password.toCharArray());

    }

}