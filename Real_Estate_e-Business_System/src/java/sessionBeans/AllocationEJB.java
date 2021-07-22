/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package sessionBeans;

import entities.Allocation;
import entities.AllocationID;
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
 * It handle Allocation entity only
 */
@Stateless// it not going to save in server memeory
@LocalBean// it is using in the same java virtual machine
@TransactionManagement(TransactionManagementType.CONTAINER)// container will handle the transaction, can be useing custom by set is as BEAN
public class AllocationEJB extends ABaseBean<Allocation, AllocationID> {

    //dependency injection persistence
    @PersistenceContext(unitName = "Real_Estate_e-Business_SystemPU")
    EntityManager em;

    /**
     * default constructor
     */
    public AllocationEJB() {
    }

    /**
     * count allocated rent property in database
     * @return
     */
    public long countAllcatedRent() {
        TypedQuery<Long> listOfAllocation = em.createNamedQuery(Allocation.TOTAL_ALLOCATED_RENT, Long.class);// create query and execute it
        return listOfAllocation.getSingleResult();
    }

    /**
     * count allocated sale property
     * @return
     */
    public long countAllcatedSale() {
        TypedQuery<Long> listOfAllocation = em.createNamedQuery(Allocation.TOTAL_ALLOCATED_SALE, Long.class);
        return listOfAllocation.getSingleResult();

    }

    /**
     * count allocated property managers
     * @return
     */
    public long countAllcatedManager() {
        TypedQuery<Long> listOfAllocation = em.createNamedQuery(Allocation.TOTAL_ALLOCATED_MANAGER, Long.class);
        return listOfAllocation.getSingleResult();
    }

    /**
     * select all allocation from database
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Allocation> selectAll() throws Exception {
        TypedQuery<Allocation> listOfAllocation = em.createNamedQuery(Allocation.SELECT_ALL, Allocation.class);//SelectAllAllocion
        return listOfAllocation.getResultList();
    }

    /**
     * find allocation based on given ID
     * @param keyWord
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Allocation> findByID(AllocationID keyWord) throws Exception {
        // can use another way: em.find(entityClass, keyWord) 
        Query qr = em.createNamedQuery(Allocation.FIND, Allocation.class);//"FindAllocation"
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
     * no use of this method
     * @param keyWord
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Allocation> find(String keyWord) throws Exception {

        return null;// no need of use this method

    }

    /**
     *
     * check the given property is managed by the given property manager
     * @param propertyID
     * @return
     */
    public boolean isPropertyIsManaged(long propertyID) {
        Query qr = em.createNamedQuery(Allocation.PROPERTY_IS_MANAGED, Allocation.class);//IsPropertyManaged
        qr.setParameter("pID", propertyID);
        return qr.getResultList().size() > 0;
    }

    /**
     * find all allocation based on manager ID and property ID
     * @param propertyID
     * @param managerID
     * @return
     */
    public boolean isGivenPropertyIsManagedByTheManager(long propertyID, long managerID) {

        Query qr = em.createNamedQuery(Allocation.MANAGER_IS_MANAGING_GIVEN_PROPERTY, Allocation.class);//ManagerIsManagingGivenProperty
        qr.setParameter("pID", propertyID);
        qr.setParameter("mID", managerID);
        return qr.getResultList().size() > 0;
    }

    /**
     * find all allocation based on manager ID
     * @param managerID
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Allocation> FindAllocationByManagerID(long managerID) {
        Query qr = em.createNamedQuery(Allocation.FIND_ALLOCATE_MANAGERID, Allocation.class);
        qr.setParameter("managerID", managerID);
        return qr.getResultList();
    }

   
}
