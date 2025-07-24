package at.jhorngacher.zerotohero.app.models;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    private String accountName;
    private String accountEmail;
    private String accountPassword;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountEMail() {
        return accountEmail;
    }

    public void setAccountEMail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    // Passwörter dürfen natürlich nicht exposed werden!
    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public Integer getAccountId() {
        return accountId;
    }

}
