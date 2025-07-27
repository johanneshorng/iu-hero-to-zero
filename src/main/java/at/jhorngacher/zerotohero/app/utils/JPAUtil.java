package at.jhorngacher.zerotohero.app.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * Globale Entity Manager Klasse
 *
 */


public class JPAUtil {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("carbonU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
