package at.jhorngacher.zerotohero.app.dao;

import at.jhorngacher.zerotohero.app.models.CarbonData;
import at.jhorngacher.zerotohero.app.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.Year;
import java.util.List;

/**
 *
 * CarbonData DataObject regelt die Kommunikation zw. Bean u. DB
 *
 */

public class CarbonDataDAO {

    public Boolean save(CarbonData carbonData) {
        EntityManager em = JPAUtil.getEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(carbonData);
            em.getTransaction().commit();
        }
        catch(Exception ex){
            em.getTransaction().rollback();
            return false;
        }
        finally{
            em.close();
        }

        return true;
    }

    public Boolean update(CarbonData carbonData) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(carbonData);
            em.getTransaction().commit();
        } catch (Exception ex){
            em.getTransaction().rollback();
            return false;
        }
        finally{
            em.close();
        }
        return true;
    }

    public List<CarbonData> findAll(){

        EntityManager em = JPAUtil.getEntityManager();

        try{
            TypedQuery<CarbonData> query = em.createQuery("Select c from CarbonData c ORDER BY c.emissionYear DESC, c.countryName ASC", CarbonData.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally{
            em.close();
        }

    }

    public List<CarbonData> findByCountryName(String countryName){
        EntityManager em = JPAUtil.getEntityManager();

        try{
            TypedQuery<CarbonData> query = em.createQuery("Select c from CarbonData c where c.countryName = :countryName", CarbonData.class);
            query.setParameter("countryName", countryName);
            return query.getResultList();
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
        finally{
            em.close();
        }
    }

    public List<CarbonData> findByYear(Year emissionYear){
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<CarbonData> query = em.createQuery("Select c from CarbonData c where c.emissionYear = :emissionYear", CarbonData.class);
            query.setParameter("emissionYear", emissionYear);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally{
            em.close();
        }
    }

    public String getCountryCode(String countryName){
        EntityManager em = JPAUtil.getEntityManager();

        try{
            TypedQuery<String> query = em.createQuery("Select DISTINCT c.countryCode from CarbonData c where c.countryName = :countryName", String.class);
            query.setParameter("countryName", countryName);
            query.setMaxResults(1);

            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally{
            em.close();
        }
    }

    /**
     *
     * Lädt eine Liste mit Ländernamen
     * @return List<CountryNames>
     */
    public List<String> getCountryNames(){

        EntityManager em = JPAUtil.getEntityManager();

        try{
            TypedQuery<String> query = em.createQuery("Select Distinct c.countryName from CarbonData c", String.class);
            return query.getResultList();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        finally{
            em.close();
        }

    }

    public CarbonData getSelectedCountry(String countryName){

        EntityManager em = JPAUtil.getEntityManager();
        CarbonData data = null;

        try{
            TypedQuery<CarbonData> query = em.createQuery("Select c from CarbonData c where c.countryName = :countryName ORDER BY c.emissionYear DESC", CarbonData.class);
            query.setParameter("countryName", countryName);
            query.setMaxResults(1);

            data =  query.getSingleResult();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        finally{
            em.close();
        }

        return data;

    }

    public CarbonData findById(Long id){

        EntityManager em = JPAUtil.getEntityManager();
        CarbonData data = null;

        try{
            TypedQuery<CarbonData> query = em.createQuery("Select c from CarbonData c where c.dataId = :dataId", CarbonData.class);
            query.setParameter("dataId", id);
            query.setMaxResults(1);

            data =  query.getSingleResult();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        finally{
            em.close();
        }

        return data;

    }



}
