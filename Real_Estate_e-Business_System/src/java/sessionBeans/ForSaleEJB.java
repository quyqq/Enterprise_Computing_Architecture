/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package sessionBeans;

import entities.ForSale;
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
 * It handle ForSale entity only
 */
@Stateless// it not going to save in server memeory
@LocalBean// it is using in the same java virtual machine
@TransactionManagement(TransactionManagementType.CONTAINER)// container will handle the transaction, can be useing custom by set is as BEAN
public class ForSaleEJB extends ABaseBean<ForSale, Long> {

    //dependency injection persistence
    @PersistenceContext(unitName = "Real_Estate_e-Business_SystemPU")
    private EntityManager em;

    /**
     * default constructor
     */
    public ForSaleEJB() {
    }

    /**
     * count total of sale property
     * @return
     */
    public long countTotal() {
        TypedQuery<Long> listOfSale = em.createNamedQuery(ForSale.COUNT_TOTAL, Long.class);//countTotalSale
        return listOfSale.getSingleResult();
    }

    /**
     * select all sale property
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ForSale> selectAll() throws Exception {
        TypedQuery<ForSale> listOfSale = em.createNamedQuery(ForSale.SELECT_ALL, ForSale.class);//SelectAllSale
        return listOfSale.getResultList();
    }

    /**
     * find sale property based on given ID
     * @param keyWord
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ForSale> findByID(Long keyWord) throws Exception {
        // can use another way: em.find(entityClass, keyWord) 
        Query qr = em.createNamedQuery(ForSale.Find, ForSale.class);//FindSale
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
     * this method is no use
     * @param keyWord
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ForSale> find(String keyWord) throws Exception {
        return null;
    }

}
