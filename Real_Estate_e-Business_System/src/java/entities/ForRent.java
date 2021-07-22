/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This entity is to represent the data from database and it's fields are mapped to database
 * It contains all necessary JPQL for interact with database  
 */
@Entity
@Table(name = "Rent")// map the name in database is Rent
@NamedQueries(
        {
            @NamedQuery(name = ForRent.SELECT_ALL, // select all rent property
                    query = "SELECT r FROM ForRent r")
            ,
            @NamedQuery(name = ForRent.FIND, // find rent property based on given ID
                    query = "SELECT fr FROM ForRent fr WHERE fr.id = :keyWord")
            ,
            @NamedQuery(name = ForRent.COUNT_TOTAL, // count total of rent property
                    query = "SELECT COUNT(FR) FROM ForRent fr")
        })
@DiscriminatorValue(value = "ForRent")// this value will be insert in Propery Entity when new Rent property is inserted
public class ForRent extends Property implements Serializable, IBaseEntity {

    // those variable will not be mapped in the database, it is used in @NamedQueries and @EJB when create query
    public static final String SELECT_ALL = "SelectALLRent";
    public static final String FIND = "FindRent";
    public static final String COUNT_TOTAL = "countTotalRent";

    private static final long serialVersionUID = 1L;// version controll

    @Column(nullable = false, precision = 10, scale = 2)// money should be decimal type with 10 digits and 2 decimal
    private BigDecimal weeklyRent;
    @Column(nullable = true)// can be null
    private Boolean providedBed;
    @Column(nullable = true)
    private Boolean providedWashingMachine;
    @Column(nullable = true)
    private Boolean providedDishWasher;
    @Column(nullable = true)
    private Boolean providedAirConditioner;
    @Column(nullable = true)
    private Boolean providedClothesDryer;

    /**
     * default constructor
     */
    public ForRent() {
    }

    /**
     * parameters constructor
     * @param weeklyRent
     * @param providedBed
     * @param providedWashingMachine
     * @param providedDishWasher
     * @param providedAirConditioner
     * @param providedClothesDryer
     */
    public ForRent(BigDecimal weeklyRent, Boolean providedBed, Boolean providedWashingMachine, Boolean providedDishWasher, Boolean providedAirConditioner, Boolean providedClothesDryer) {
        this.weeklyRent = weeklyRent;
        this.providedBed = providedBed;
        this.providedWashingMachine = providedWashingMachine;
        this.providedDishWasher = providedDishWasher;
        this.providedAirConditioner = providedAirConditioner;
        this.providedClothesDryer = providedClothesDryer;
    }

    /**
     *
     * Parameter constructor
     * @param weeklyRent
     * @param providedBed
     * @param providedWashingMachine
     * @param providedDishWasher
     * @param providedAirConditioner
     * @param providedClothesDryer
     * @param id
     * @param propertyType
     * @param address
     * @param description
     * @param lotSize
     * @param squareFeet
     * @param levels
     * @param numberBathroom
     * @param numberBedroom
     * @param numberToilet
     * @param numberGarage
     * @param yearBuilt
     */
    public ForRent(BigDecimal weeklyRent, Boolean providedBed, Boolean providedWashingMachine, Boolean providedDishWasher, Boolean providedAirConditioner, Boolean providedClothesDryer, Long id, String propertyType, String address, String description, Double lotSize, Double squareFeet, Integer levels, Integer numberBathroom, Integer numberBedroom, Integer numberToilet, Integer numberGarage, Integer yearBuilt) {
        super(id, propertyType, address, description, lotSize, squareFeet, levels, numberBathroom, numberBedroom, numberToilet, numberGarage, yearBuilt);
        this.weeklyRent = weeklyRent;
        this.providedBed = providedBed;
        this.providedWashingMachine = providedWashingMachine;
        this.providedDishWasher = providedDishWasher;
        this.providedAirConditioner = providedAirConditioner;
        this.providedClothesDryer = providedClothesDryer;
    }

   

    //----------------------------------getter seter-----------------------------

    /**
     *
     * @return
     */
    public BigDecimal getWeeklyRent() {
        return weeklyRent;
    }

    /**
     *
     * @param weeklyRent
     */
    public void setWeeklyRent(BigDecimal weeklyRent) {
        this.weeklyRent = weeklyRent;
    }

    /**
     *
     * @return
     */
    public Boolean getProvidedBed() {
        return providedBed;
    }

    /**
     *
     * @param providedBed
     */
    public void setProvidedBed(Boolean providedBed) {
        this.providedBed = providedBed;
    }

    /**
     *
     * @return
     */
    public Boolean getProvidedWashingMachine() {
        return providedWashingMachine;
    }

    /**
     *
     * @param providedWashingMachine
     */
    public void setProvidedWashingMachine(Boolean providedWashingMachine) {
        this.providedWashingMachine = providedWashingMachine;
    }

    /**
     *
     * @return
     */
    public Boolean getProvidedDishWasher() {
        return providedDishWasher;
    }

    /**
     *
     * @param providedDishWasher
     */
    public void setProvidedDishWasher(Boolean providedDishWasher) {
        this.providedDishWasher = providedDishWasher;
    }

    /**
     *
     * @return
     */
    public Boolean getProvidedAirConditioner() {
        return providedAirConditioner;
    }

    /**
     *
     * @param providedAirConditioner
     */
    public void setProvidedAirConditioner(Boolean providedAirConditioner) {
        this.providedAirConditioner = providedAirConditioner;
    }

    /**
     *
     * @return
     */
    public Boolean getProvidedClothesDryer() {
        return providedClothesDryer;
    }

    /**
     *
     * @param providedClothesDryer
     */
    public void setProvidedClothesDryer(Boolean providedClothesDryer) {
        this.providedClothesDryer = providedClothesDryer;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return super.toString() + String.format(
                "\nweeklyRent: %s\n"
                + "providedBed: %s\n"
                + "providedWashingMachine: %s\n"
                + "providedDishWasher: %s\n"
                + "providedAirConditioner: %s\n"
                + "providedClothesDryer: %s",
                this.weeklyRent,
                this.providedBed,
                this.providedWashingMachine,
                this.providedDishWasher,
                this.providedAirConditioner,
                this.providedClothesDryer);
    }

}
