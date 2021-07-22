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
 * This entity is to represent the data from database and it's fields are mapped to database
 * It contains all necessary JPQL for interact with database  
 */
@Entity
/**
 * this will create a column name Type in the table 
 * and store values (ForRent or ForSale) when new property is inserted in database. 
 * Value depend on subclass
 */
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "Type")
@Inheritance(strategy = InheritanceType.JOINED)// joined is mean it will link subclasses with relationship
@NamedQueries(
        {
            @NamedQuery(name = "SelectAllProperties", // never use but it is good for practice
                    query = "SELECT pr FROM Property pr")
            ,
            @NamedQuery(name = "FindProperty", // never use but it is good for practice
                    query = "SELECT pr FROM Property pr WHERE pr.id = :PropertyID")
        })

@Table(name = "Property")
/**
 * because not all database management systems support GenerationType.SEQUENCE,
 * to make sure it works, sequence table is created
 */
@TableGenerator(
        name = "Property_generator",// name generator
        table = "SEQUENCE",// name of table
        pkColumnName = "Generator",//name of generator (Property_generator) that will be insert in SEQUENCE table
        valueColumnName = "AllocationSize",// allocation size is 50 over 50 it will increate to 100 ... 150 .... 200 ...
        allocationSize = 50,
        initialValue = 1)// starting value is 1
public abstract class Property implements Serializable, IBaseEntity {

    private static final long serialVersionUID = 1L;// version control

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Property_generator")// set id generated value
    @Column(name = "propertyID")
    private Long id;
    @Column(length = 150, nullable = false)
    private String propertyType;
    @Column(length = 175, nullable = false)
    private String address;
    @Column(length = 400)
    private String description;
    @Column(nullable = false)
    private Double lotSize;
    @Column(nullable = false)
    private Double squareFeet;
    @Column(nullable = false)
    private Integer levels;
    @Column(nullable = false)
    private Integer numberBathroom;
    @Column(nullable = false)
    private Integer numberBedroom;
    @Column(nullable = false)
    private Integer numberToilet;
    @Column(nullable = false)
    private Integer numberGarage;   
    @Column(nullable = true)
    private Integer yearBuilt;
    
    /**
     * default constructor
     */
    public Property() {
    }

    /**
     * parameter constructor
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
    public Property(Long id, String propertyType, String address, String description, Double lotSize, Double squareFeet, Integer levels, Integer numberBathroom, Integer numberBedroom, Integer numberToilet, Integer numberGarage, Integer yearBuilt) {
        this.id = id;
        this.propertyType = propertyType;
        this.address = address;
        this.description = description;
        this.lotSize = lotSize;
        this.squareFeet = squareFeet;
        this.levels = levels;
        this.numberBathroom = numberBathroom;
        this.numberBedroom = numberBedroom;
        this.numberToilet = numberToilet;
        this.numberGarage = numberGarage;
        this.yearBuilt = yearBuilt;
    }

    //-----------------------------getter and setter---------------------------

    /**
     *
     * @return
     */
    
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getPropertyType() {
        return propertyType;
    }

    /**
     *
     * @param propertyType
     */
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public Double getLotSize() {
        return lotSize;
    }

    /**
     *
     * @param lotSize
     */
    public void setLotSize(Double lotSize) {
        this.lotSize = lotSize;
    }

    /**
     *
     * @return
     */
    public Double getSquareFeet() {
        return squareFeet;
    }

    /**
     *
     * @param squareFeet
     */
    public void setSquareFeet(Double squareFeet) {
        this.squareFeet = squareFeet;
    }

    /**
     *
     * @return
     */
    public Integer getLevels() {
        return levels;
    }

    /**
     *
     * @param levels
     */
    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    /**
     *
     * @return
     */
    public Integer getNumberBathroom() {
        return numberBathroom;
    }

    /**
     *
     * @param numberBathroom
     */
    public void setNumberBathroom(Integer numberBathroom) {
        this.numberBathroom = numberBathroom;
    }

    /**
     *
     * @return
     */
    public Integer getNumberBedroom() {
        return numberBedroom;
    }

    /**
     *
     * @param numberBedroom
     */
    public void setNumberBedroom(Integer numberBedroom) {
        this.numberBedroom = numberBedroom;
    }

    /**
     *
     * @return
     */
    public Integer getNumberToilet() {
        return numberToilet;
    }

    /**
     *
     * @param numberToilet
     */
    public void setNumberToilet(Integer numberToilet) {
        this.numberToilet = numberToilet;
    }

    /**
     *
     * @return
     */
    public Integer getNumberGarage() {
        return numberGarage;
    }

    /**
     *
     * @param numberGarage
     */
    public void setNumberGarage(Integer numberGarage) {
        this.numberGarage = numberGarage;
    }

    /**
     *
     * @return
     */
    public Integer getYearBuilt() {
        return yearBuilt;
    }

    /**
     *
     * @param yearBuilt
     */
    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * 
     * @param object
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Property)) {
            return false;
        }
        Property other = (Property) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return String.format(
                "id: %s\n"
                + "address: %s\n"
                + "description: %s\n"
                + "lotSize: %s\n"
                + "squareFeet : %s\n"
                + "level: %s\n"
                + "numberBathroom: %s\n"
                + "numberBedroom: %s\n"
                + "numberToilet: %s\n"                
                + "numberGarage: %s\n"
                + "yearBuilt: %s\n",
                this.id,
                this.address,
                this.description,
                this.lotSize,
                this.squareFeet,
                this.levels,
                this.numberBathroom,
                this.numberBedroom,
                this.numberToilet,                
                this.numberGarage,
                this.yearBuilt);
    }

}
