/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This entity is to represent the data from database and it's fields are mapped to database
 * It contains all necessary JPQL for interact with database  
 */
@Entity
@NamedQueries(
        {
            @NamedQuery(name = Allocation.SELECT_ALL,// select all allocation from database
                    query = "SELECT al from Allocation al ORDER BY al.allocationID ASC")
            ,
            @NamedQuery(name = Allocation.FIND,// find allocation based on given ID
                    query = "SELECT al FROM Allocation al WHERE al.allocationID = :keyWord")
            ,
            @NamedQuery(name = Allocation.PROPERTY_IS_MANAGED,// find all allocation based on propety ID
                    query = "SELECT pp FROM Allocation pp WHERE pp.property.id = :pID")
            ,
            @NamedQuery(name = Allocation.MANAGER_IS_MANAGING_GIVEN_PROPERTY,// find all allocation based on manager ID and property ID
                    query = "SELECT p FROM Allocation p  WHERE p.property.id = :pID AND p.manager.id = :mID")
            ,
            @NamedQuery(name = Allocation.FIND_ALLOCATE_MANAGERID,// find all allocation based on manager ID
                    query = "SELECT al FROM Allocation al WHERE al.manager.id = :managerID")
            ,
            @NamedQuery(name = Allocation.TOTAL_ALLOCATED_MANAGER,// count how many allocated manager
                    query = "SELECT COUNT(DISTINCT al.manager.id) FROM Allocation al"
                    + " WHERE al.manager.id IN (SELECT mng.id FROM PropertyManager mng)")
            ,
            @NamedQuery(name = Allocation.TOTAL_ALLOCATED_RENT,// count how many allocated rent property
                    query = "SELECT COUNT(DISTINCT al.property.id) FROM Allocation al "
                    + "WHERE al.property.id IN (SELECT fr.id FROM ForRent fr)")
            ,
            @NamedQuery(name = Allocation.TOTAL_ALLOCATED_SALE,// count how many allocated sale property
                    query = "SELECT COUNT(DISTINCT al.manager.id) FROM Allocation al "
                    + "WHERE al.property.id IN (SELECT fs.id FROM ForSale fs)")

        })
public class Allocation implements Serializable, IBaseEntity {

    // those variable will not be mapped in the database, it is used in @NamedQueries and @EJB when create query
    public static final String SELECT_ALL = "SelectAllAllocion";
    public static final String FIND = "FindAllocation";
    public static final String PROPERTY_IS_MANAGED = "IsPropertyManaged";
    public static final String MANAGER_IS_MANAGING_GIVEN_PROPERTY = "ManagerIsManagingGivenProperty";
    public static final String FIND_ALLOCATE_MANAGERID = "FindAllocationByManagerID";
    public static final String TOTAL_ALLOCATED_RENT = "totalAllocatedRent";
    public static final String TOTAL_ALLOCATED_SALE = "totalAllocatedSale";
    public static final String TOTAL_ALLOCATED_MANAGER = "totalAllocatedManager";

    private static final long serialVersionUID = 1L;// version controll

    @EmbeddedId// composite keys because it is combine of PropertyMangerID and PropertyID
    private AllocationID allocationID;

    @Temporal(TemporalType.TIMESTAMP)// type time
    @Column(nullable = false)// ca not be null
    private Date allocatedDate;
    
    // create feign key link managerID_ columns to managerID colmn in PropertyManger entity
    @MapsId("managerID")
    @ManyToOne(fetch = FetchType.LAZY)// not load data 
    @JoinColumn(name = "managerID_", nullable = false)// name of column
    private PropertyManager manager;

    // create feign key link propertyID_ columns to propertyID colmn in property entity
    @MapsId("propertyID")
    @OneToOne(fetch = FetchType.EAGER)// load data when it is retrieved
    @JoinColumn(name = "propertyID_", nullable = false, unique = true)// set unique = true because one property can not managed bay 2 or more property managers
    private Property property;

    /**
     * default constructor
     */
    public Allocation() {
    }

    /**
     * parameter constructor
     * @param allocationID
     * @param allocatedDate
     * @param manager manager Object
     * @param property property object
     */
    public Allocation(AllocationID allocationID, Date allocatedDate,
            PropertyManager manager, Property property) {
        this.allocationID = allocationID;
        this.allocatedDate = allocatedDate;
        this.manager = manager;
        this.property = property;
    }

    
    //---------------------------------getter and setter------------------------

    /**
     *
     * @return
     */
    public AllocationID getAllocationID() {
        return allocationID;
    }

    /**
     *
     * @param allocationID
     */
    public void setAllocationID(AllocationID allocationID) {
        this.allocationID = allocationID;
    }

    /**
     *
     * @return
     */
    public Date getAllocatedDate() {
        return allocatedDate;
    }

    /**
     *
     * @param allocatedDate
     */
    public void setAllocatedDate(Date allocatedDate) {
        this.allocatedDate = allocatedDate;
    }

    /**
     *
     * @return
     */
    public PropertyManager getManager() {
        return manager;
    }

    /**
     *
     * @param manager
     */
    public void setManager(PropertyManager manager) {
        this.manager = manager;
    }

    /**
     *
     * @return
     */
    public Property getProperty() {
        return property;
    }

    /**
     *
     * @param property
     */
    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return String.format(
                "allocationID: %s\n"
                + "allocatedDate: %s\n"
                + "manager: %s\n"
                + "property: %s",
                this.allocationID,
                this.allocatedDate,
                this.manager,
                this.property);
    }

}
