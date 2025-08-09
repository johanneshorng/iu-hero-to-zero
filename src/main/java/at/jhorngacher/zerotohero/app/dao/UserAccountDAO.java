package at.jhorngacher.zerotohero.app.dao;


import at.jhorngacher.zerotohero.app.models.UserAccount;
import at.jhorngacher.zerotohero.app.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


/**
 *
 * UserAccount DataObject regelt die Kommunikation zw. Bean u. DB
 *
 */
public class UserAccountDAO {

        public UserAccount loadAccount(String accountEmail){

            EntityManager em = JPAUtil.getEntityManager();

            try{

                TypedQuery<UserAccount> query = em.createQuery("select u from UserAccount u where u.accountEmail = :accountEmail", UserAccount.class);
                query.setParameter("accountEmail", accountEmail);
                query.setMaxResults(1);

                return query.getSingleResult();

            } catch(Exception e){

               return null;

            }
            finally{
                em.close();
            }

        }

        public void createAccount(UserAccount userAccount){
            EntityManager em = JPAUtil.getEntityManager();
            try{
                em.getTransaction().begin();
                em.persist(userAccount);
                em.getTransaction().commit();
            }  catch(Exception e){
                throw  new RuntimeException(e);
            }
            finally{
                em.close();
            }
        }


}
