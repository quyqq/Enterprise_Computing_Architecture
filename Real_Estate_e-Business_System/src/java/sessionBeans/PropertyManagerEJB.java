/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package sessionBeans;

import entities.IBaseEntity;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.PropertyManager;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This EJB is to handle all data process come from business tier to database in general
 * It handle PropertyManager entity only
 */
@Stateless// it not going to save in server memeory
@LocalBean// it is using in the same java virtual machine
@TransactionManagement(TransactionManagementType.CONTAINER)// container will handle the transaction, can be useing custom by set is as BEAN
public class PropertyManagerEJB extends ABaseBean<PropertyManager,Long> {

    //dependency injection persistence
    @PersistenceContext(unitName = "Real_Estate_e-Business_SystemPU")
    EntityManager em;

    /**
     * default constructor
     */
    public PropertyManagerEJB() {
    }

    /**
     * count total of property managers in database
     * @return
     */
    public long countTotal()
    {
        TypedQuery<Long> listOfManagers = em.createNamedQuery(PropertyManager.COUNT_TOTAL, Long.class);
        return listOfManagers.getSingleResult();
    }
   

    /**
     * select all property manager
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PropertyManager> selectAll() throws Exception{
        TypedQuery<PropertyManager> listOfManagers = em.createNamedQuery(PropertyManager.SELECT_ALL, PropertyManager.class);//SelectAllManager
        return listOfManagers.getResultList();
    }

    /**
     * find property manager by using given ID
     * @param id
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PropertyManager> findByID(Long id) throws Exception{
        // can use another way: em.find(entityClass, keyWord) 
        Query qr = em.createNamedQuery(PropertyManager.FIND, PropertyManager.class);//FindManager
        qr.setParameter("keyWord", id);
        return qr.getResultList();
    }

    /**
     * get EntityManager, it is will be called from abstract class
     * @return
     * @throws Exception 
     */
    @Override
    public EntityManager getEntityManager() throws Exception{
        return em;
    }

    /**
     * find property manager by name,
     * this JPQL is to find all property manager how has name contain given keyword 
     * For example:
     * my name is Quyet Quang Quy
     * Keyword is Q
     * 
     * the result still has my name in it because my name contain Q
     * @param keyWord
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PropertyManager> find(String keyWord) throws Exception{
        Query qr = em.createNamedQuery(PropertyManager.FIND_ONE, PropertyManager.class);//FindOneManager
        qr.setParameter("keyWord", "%" + keyWord.toUpperCase() + "%");//% is work in JPQL 
        return qr.getResultList();
    }
}
