/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This is composite key that is used in 
 */
@Embeddable
public class AllocationID implements Serializable {    
   
    private Long managerID;
    private Long propertyID;
    
    /**
     * default constructor
     */
    public AllocationID() {
    }

    /**
     *
     * parameter constructor
     * @param managerID type long
     * @param propertyID type long
     */
    public AllocationID(Long managerID, Long propertyID) {
        this.managerID = managerID;
        this.propertyID = propertyID;
    }

    //------------------------getter and setter--------------------------------

    /**
     *
     * @return
     */
    public Long getManagerID() {
        return managerID;
    }

    /**
     *
     * @param managerID
     */
    public void setManagerID(Long managerID) {
        this.managerID = managerID;
    }

    /**
     *
     * @return
     */
    public Long getPropertyID() {
        return propertyID;
    }

    /**
     *
     * @param propertyID
     */
    public void setPropertyID(Long propertyID) {
        this.propertyID = propertyID;
    }

    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); 
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        return super.hashCode(); 
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return String.format(" managerID: %s \n propertyID: %s", 
                this.managerID,this.propertyID);
    }
    
    
    
}
