/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package sessionBeans;

import entities.ForRent;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This EJB is to handle all data process come from business tier to database in general
 * It handle ForRent entity only
 */
@Stateless// it not going to save in server memeory
@LocalBean// it is using in the same java virtual machine
@TransactionManagement(TransactionManagementType.CONTAINER)// container will handle the transaction, can be useing custom by set is as BEAN
public class ForRentEJB extends ABaseBean<ForRent, Long> {

    //dependency injection persistence
    @PersistenceContext(unitName = "Real_Estate_e-Business_SystemPU")
    EntityManager em;

    /**
     * default constructor
     */
    public ForRentEJB() {
    }

    /**
     * count total of rent property
     * @return
     */
    public long countTotal() {
        TypedQuery<Long> listOfRent = em.createNamedQuery(ForRent.COUNT_TOTAL, Long.class);//countTotalRent
        return listOfRent.getSingleResult();
    }

    /**
     * select all rent property
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ForRent> selectAll() throws Exception {
        TypedQuery<ForRent> listOfRent = em.createNamedQuery(ForRent.SELECT_ALL, ForRent.class);//SelectALLRent
        return listOfRent.getResultList();
    }

    /**
     * find rent property based on given ID
     * @param keyWord
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ForRent> findByID(Long keyWord) throws Exception {
        Query qr = em.createNamedQuery(ForRent.FIND, ForRent.class);// can use another way: em.find(entityClass, keyWord) 
        qr.setParameter("keyWord", keyWord);

        return qr.getResultList();
    }

    /**
     * get EntityManager, it is will be called from abstract class
     * @return
     * @throws Exception 
     */
    @Override
    public EntityManager getEntityManager() throws Exception {
        return em;
    }

    /**
     * No use of this method
     * @param keyWord
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ForRent> find(String keyWord) throws Exception {
        return null;
    }

}
