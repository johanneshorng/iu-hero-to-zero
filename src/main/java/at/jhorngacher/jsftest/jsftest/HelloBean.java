package at.jhorngacher.jsftest.jsftest;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("helloBean")
@SessionScoped
public class HelloBean implements Serializable {
    private String name;
    private String greeting;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreeting() {
        return greeting;
    }

    public String greet() {
        greeting = "Hallo, " + (name != null ? name : "unbekannter Besucher") + "!";
        return null; // Auf der gleichen Seite bleiben
    }
}
