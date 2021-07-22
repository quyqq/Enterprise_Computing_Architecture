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
@Table(name = "Sale")// set name of table in database is Sale
@NamedQueries(
        {
            @NamedQuery(name = ForSale.SELECT_ALL, // select all sale property
                    query = "SELECT s FROM ForSale s")
            ,
            @NamedQuery(name = ForSale.Find, // find sale property based on given ID
                    query = "SELECT fs FROM ForSale fs WHERE fs.id=:keyWord")
        ,@NamedQuery(name = ForSale.COUNT_TOTAL,// count total of sale property
                query = "SELECT COUNT(fs) FROM ForSale fs")
        })
@DiscriminatorValue(value = "ForSale")// this value will be insert in Propery Entity when new Sale property is inserted
public class ForSale extends Property implements Serializable, IBaseEntity {
    
    // those variable will not be mapped in the database, it is used in @NamedQueries and @EJB when create query
    public static final String SELECT_ALL = "SelectAllSale";
    public static final String Find = "FindSale";
    public static final String COUNT_TOTAL = "countTotalSale";

    private static final long serialVersionUID = 1L;// version controll
    @Column(nullable = false, precision = 12, scale = 2)// money should be decimal type with 10 digits and 2 decimal
    private BigDecimal salePrice;

    /**
     * default constructor
     */
    public ForSale() {
    }

    /**
     * parameter constructor
     * @param salePrice
     */
    public ForSale(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * parameter constructor
     * @param salePrice
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
    public ForSale(BigDecimal salePrice, Long id, String propertyType, String address, 
            String description, Double lotSize, Double squareFeet, Integer levels, 
            Integer numberBathroom, Integer numberBedroom, Integer numberToilet, 
            Integer numberGarage, Integer yearBuilt) {
        super(id, propertyType, address, description, lotSize, 
                squareFeet, levels, numberBathroom, numberBedroom, 
                numberToilet, numberGarage, yearBuilt);
        this.salePrice = salePrice;
    }

      
    //-------------------------------setter and getter--------------------------

    /**
     *
     * @return
     */

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    /**
     *
     * @param salePrice
     */
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return super.toString() + "\n Sale Price: " + salePrice;
    }

}
