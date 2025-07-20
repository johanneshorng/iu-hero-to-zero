package at.jhorngacher.jsftest.jsftest.dao;

import at.jhorngacher.jsftest.jsftest.models.CarbonData;
import at.jhorngacher.jsftest.jsftest.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;


public class CarbonDataDAO {

    public void save(CarbonData carbonData) {
        EntityManager em = JPAUtil.getEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(carbonData);
            em.getTransaction().commit();
        }
        catch(Exception ex){
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }
    }

    public List<CarbonData> findAll(){

        EntityManager em = JPAUtil.getEntityManager();

        try{
            TypedQuery<CarbonData> query = em.createQuery("Select c from carbonData c ORDER BY c.emissionYEAR, c.countryName", CarbonData.class);
            return query.getResultList();
        }
        catch(Exception ex){
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }

    }

}
