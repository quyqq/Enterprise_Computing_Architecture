/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
            @NamedQuery(name = PropertyManager.SELECT_ALL, // select all property manager
                    query = "SELECT mn FROM PropertyManager mn")
            ,
            @NamedQuery(name = PropertyManager.FIND, // find property manager by using given ID
                    query = "SELECT mn FROM PropertyManager mn WHERE mn.id=:keyWord")
            ,
            /**
             * find property manager by name,
             * this JPQL is to find all property manager how has name contain given keyword 
             * For example:
             * my name is Quyet Quang Quy
             * Keyword is Q
             * 
             * the result still has my name in it because my name contain Q
             */
            @NamedQuery(name = PropertyManager.FIND_ONE,
                    query = "SELECT mn FROM PropertyManager mn WHERE UPPER(mn.lastName) LIKE :keyWord OR UPPER(mn.firstName) LIKE "
                    + ":keyWord OR upper(CONCAT( MN.firstName , ' ',MN.lastName)) LIKE :keyWord OR upper(CONCAT( MN.lastName "
                    + ", ' ',MN.firstName)) LIKE :keyWord  "),
            @NamedQuery(name = PropertyManager.COUNT_TOTAL,// count total of property managers in database
                    query = "SELECT COUNT(mn) FROM PropertyManager mn")
        })
@Table(name = "PropertyManager")
/**
 * because not all database management systems support GenerationType.SEQUENCE,
 * to make sure it works, sequence table is created
 */
@TableGenerator(
        name = "PropertyManager_generator",// name generator
        table = "SEQUENCE",// name of table
        pkColumnName = "Generator",//name of generator (PropertyManager_generator) that will be insert in SEQUENCE table
        valueColumnName = "AllocationSize",// allocation size is 50 over 50 it will increate to 100 ... 150 .... 200 ...
        allocationSize = 50,
        initialValue = 1)// starting value is 1
public class PropertyManager implements Serializable, IBaseEntity {

    // those variable will not be mapped in the database, it is used in @NamedQueries and @EJB when create query
    public static final String SELECT_ALL = "SelectAllManager";
    public static final String FIND = "FindManager";
    public static final String FIND_ONE = "FindOneManager";
    public static final String COUNT_TOTAL = "countTotalManager";

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PropertyManager_generator")// set id generated value
    @Column(name = "ManagerID")
    private Long id;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = true)
    private byte[] picture;
    @Column(nullable = false, length = 20)
    private String firstName;
    @Column(nullable = false, length = 20)
    private String lastName;
    @Column(nullable = false, length = 100)
    private String address;
    @Column(nullable = true, length = 100)
    private String email;
    @Column(nullable = false, length = 20)
    private String phoneNumber;
    @Temporal(TemporalType.DATE)
    private Date hireDate;
    @Column(nullable = false)
    private Boolean gender;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Transient// this field will not be mapped because age can be calculated in runtime, no need to map it
    private Integer age;

    /**
     * CascadeType.REMOVE
     * CascadeType.MERGE 
     * CascadeType.REFRESH
     * CascadeType.DETACH
     * 
     * they are is good for changing propertyManger entity
     * For example, if user delete an property manager entity from the database,
     * cascade will automatically delete all allocations of the deleting property manager from allocation table
     */
    @OneToMany(mappedBy = "Manager", cascade = {
        CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH
    }, fetch = FetchType.LAZY)
    private List<Allocation> allocations;

    /**
     * default constructor
     */
    public PropertyManager() {
    }

    /**
     * parameters constructor
     * @param picture
     * @param firstName
     * @param lastName
     * @param address
     * @param email
     * @param phoneNumber
     * @param hireDate
     * @param gender
     * @param birthday
     * @param allocations
     */
    public PropertyManager(byte[] picture, String firstName, String lastName, String address, String email, String phoneNumber, Date hireDate, Boolean gender, Date birthday, List<Allocation> allocations) {
        this.picture = picture;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.gender = gender;
        this.birthday = birthday;
        this.allocations = allocations;
    }

    //------------------------------getter and setter---------------------------

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public List<Allocation> getAllocations() {
        return allocations;
    }

    /**
     *
     * @param allocations
     */
    public void setAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
    }

    /**
     *
     * @return
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     *
     * @param picture
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
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
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     *
     * @param hireDate
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    /**
     *
     * @return
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     *
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     *
     * @return
     */
    public Integer getAge() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(birthday);
        setAge(Calendar.getInstance().get(Calendar.YEAR) - calendar.get(Calendar.YEAR));
        return age;
    }

    /**
     *
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

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
        if (!(object instanceof PropertyManager)) {
            return false;
        }
        PropertyManager other = (PropertyManager) object;
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
                //"picture: %s\n" +
                "firstName : %s\n"
                + "lastName: %s\n"
                + "address : %s\n"
                + "phoneNumber: %s\n"
                + "hireDate : %s\n"
                + "gender : %s\n"
                + "birthday: %s\n"
                + "age:%s",
                //this.picture,
                this.firstName,
                this.lastName,
                this.address,
                this.phoneNumber,
                this.hireDate,
                this.gender,
                this.birthday,
                this.age);
    }

}
