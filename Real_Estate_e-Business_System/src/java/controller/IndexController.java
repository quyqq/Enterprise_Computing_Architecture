/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionBeans.AllocationEJB;
import sessionBeans.ForRentEJB;
import sessionBeans.ForSaleEJB;
import sessionBeans.PropertyManagerEJB;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This managed bean is a backed bean (controller in business tier)that handle index.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extention is changed to be .fx in web.xml file
 * Receive data from presentation tier and talk to persistence tier
 */
@Named(value = "indexController")
@RequestScoped
public class IndexController {

    // those variables are retrieved from index.xhtml
    private long totalProperty;
    private long totalAllocatedProperty;
    private long totalRent;
    private long totalAllcatedRent;
    private long totalSale;
    private long totalAllocatedSale;
    private long totalManager;
    private long totalAllocatedManager;

    //@EJB is independancy injection from presentation tier
    @EJB
    private ForRentEJB fr;
    @EJB
    private ForSaleEJB fs;
    @EJB
    private PropertyManagerEJB pm;
    @EJB
    private AllocationEJB allocationEJB;

    /**
     * Creates a new instance of IndexController
     */
    public IndexController() {
    }

    /**
     * This method is to load all calculated numbers after the request is constructed  
     */
    @PostConstruct
    private void loadPage() {

        totalRent = fr.countTotal();
        totalSale = fs.countTotal();
        totalProperty = totalRent + totalSale;
        totalManager = pm.countTotal();
        totalAllcatedRent = allocationEJB.countAllcatedRent();
        totalAllocatedSale = allocationEJB.countAllcatedSale();
        totalAllocatedProperty = totalAllcatedRent + totalAllocatedSale;
        totalAllocatedManager = allocationEJB.countAllcatedManager();

    }

    //-----------------------------get and set methods---------------------------------------------

    /**
     *
     * @return
     */
    public long getTotalProperty() {
        return totalProperty;
    }

    /**
     *
     * @param totalProperty
     */
    public void setTotalProperty(long totalProperty) {
        this.totalProperty = totalProperty;
    }

    /**
     *
     * @return
     */
    public long getTotalAllocatedProperty() {
        return totalAllocatedProperty;
    }

    /**
     *
     * @param totalAllocatedProperty
     */
    public void setTotalAllocatedProperty(long totalAllocatedProperty) {
        this.totalAllocatedProperty = totalAllocatedProperty;
    }

    /**
     *
     * @return
     */
    public long getTotalRent() {
        return totalRent;
    }

    /**
     *
     * @param totalRent
     */
    public void setTotalRent(long totalRent) {
        this.totalRent = totalRent;
    }

    /**
     *
     * @return
     */
    public long getTotalAllcatedRent() {
        return totalAllcatedRent;
    }

    /**
     *
     * @param totalAllcatedRent
     */
    public void setTotalAllcatedRent(long totalAllcatedRent) {
        this.totalAllcatedRent = totalAllcatedRent;
    }

    /**
     *
     * @return
     */
    public long getTotalSale() {
        return totalSale;
    }

    /**
     *
     * @param totalSale
     */
    public void setTotalSale(long totalSale) {
        this.totalSale = totalSale;
    }

    /**
     *
     * @return
     */
    public long getTotalAllocatedSale() {
        return totalAllocatedSale;
    }

    /**
     *
     * @param totalAllocatedSale
     */
    public void setTotalAllocatedSale(long totalAllocatedSale) {
        this.totalAllocatedSale = totalAllocatedSale;
    }

    /**
     *
     * @return
     */
    public long getTotalManager() {
        return totalManager;
    }

    /**
     *
     * @param totalManager
     */
    public void setTotalManager(long totalManager) {
        this.totalManager = totalManager;
    }

    /**
     *
     * @return
     */
    public long getTotalAllocatedManager() {
        return totalAllocatedManager;
    }

    /**
     *
     * @param totalAllocatedManager
     */
    public void setTotalAllocatedManager(long totalAllocatedManager) {
        this.totalAllocatedManager = totalAllocatedManager;
    }

}
