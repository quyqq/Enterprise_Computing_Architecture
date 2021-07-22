/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller.allocation;

import controller.FunctionalClass;
import controller.SendMSG;
import controller.SessionController;
import entities.Allocation;
import entities.ForRent;
import entities.ForSale;
import entities.PropertyManager;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import sessionBeans.AllocationEJB;
import sessionBeans.ForRentEJB;
import sessionBeans.ForSaleEJB;
import sessionBeans.PropertyManagerEJB;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This managed bean is a backed bean (controller in business tier)that handle mainly addNewAllocation.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extension is changed to be .fx in web.xml file
 * Receive data from presentation and talk to persistence tier
 */
@Named(value = "addNewAllocationRequest")
@RequestScoped
public class AddNewAllocationRequest {

    // those variable is to store template data in this request and they are loaded into list on  presentation tier
    private List<PropertyManager> managers;
    private List<ForSale> forSales;
    private List<ForRent> forRents;
    // those variable is to store selected IDs from selected list in combo box on presentation tier (View)
    private long selectedMangerID;
    private long selectedRentID;
    private long selectedSaleID;

    @Inject// independency injection from other managed bean
    SessionController sc;

    //@EJB is independency injection from persistence teir
    @EJB
    private ForRentEJB fr;
    @EJB
    private ForSaleEJB fs;
    @EJB
    private PropertyManagerEJB pm;
    @EJB
    private AllocationEJB allocationEJB;

    /**
     * Creates a new instance of AddNewAllocationRequest
     */
    public AddNewAllocationRequest() {
    }

    /**
     * this method is to navigate to addNewAllocation page
     * @return URL of addNewAllocation
     */
    public String openAddNewAllocation() {

        return "/allocation/addNewAllocation.fx";
    }

/**
 * this method is to allocate selected rent property to selected manager
 * the select property and manager is base on 
 * private long selectedMangerID;
 * private long selectedRentID;
 * private long selectedSaleID;
 * 
 * sc.setShowMSG(true);// show message on view by set the variable on SessionController which is injected 
 * sc.setSuccsessfully(false);// set style of message
 * sc.setMessage(<String Value>); // set the message
 * 
 * @return URL
 */
    public String allocateRentProperty() {

        if (selectedMangerID <= 0 || selectedRentID <= 0) {// checking selected requirements
            sc.setShowMSG(true);// show message on view by set the variable on SessionController which is injected 
            sc.setSuccsessfully(false);// set style of message

            if (selectedMangerID <= 0) {
                sc.setMessage("Please select a property manager");// set the message
                return "";
            }

            sc.setMessage("Please select a rent property");
            return "";
        } else {
            try {

                boolean managed = allocationEJB.isPropertyIsManaged(selectedRentID);// check the selected property is managed by selected manager
                boolean managedBy = allocationEJB.isGivenPropertyIsManagedByTheManager(selectedRentID, selectedMangerID);// check the selected property is managed by an other manager
                if (managed | managedBy) {// if it is true the show the error message on view
                    sc.setShowMSG(true);
                    sc.setSuccsessfully(false);
                    if (managedBy) {

                        sc.setMessage("This selected rent property already managed");
                    } else {
                        sc.setMessage("This rent property already managed by another manager");
                    }
                    return "";
                } else {
                    PropertyManager manager = pm.findByID(selectedMangerID).get(0);// find a selected manager object
                    ForRent fRent = fr.findByID(selectedRentID).get(0);// find selected propery object
                    Allocation alct = new Allocation();
                    alct.setManager(manager);
                    alct.setProperty(fRent);
                    alct.setAllocatedDate(new Date());
                    allocationEJB.insert(alct);// insert to databse
                    sc.setShowMSG(true);
                    sc.setSuccsessfully(true);
                    sc.setMessage("Allocate rent property successfully");
                    SendMSG.flashData("newAllocation", alct);
                    return "/allocation/listAllAllocation.fx";
                }

            } catch (Exception ex) {
                Throwable root = FunctionalClass.findRootError(ex);// find root error
                SendMSG.flashData("msg", "Error" + root.getMessage());// send error message to errorPage.fx (xhtml)
                return "/errorPage.fx";
            }
        }

    }

    /**
    * this method is to allocate selected sale property to selected manager
    * the select property and manager is base on 
    * private long selectedMangerID;
    * private long selectedRentID;
    * private long selectedSaleID;
    * 
    * sc.setShowMSG(true);// show message on view by set the variable on SessionController which is injected 
    * sc.setSuccsessfully(false);// set style of message
    * sc.setMessage(<String Value>); // set the message
    * 
    * @return URL
    */
    public String allocateSaleProperty() {

        if (selectedMangerID <= 0 || selectedSaleID <= 0) {// checking selected requirements
            sc.setShowMSG(true);// show message on view by set the variable on SessionController which is injected 
            sc.setSuccsessfully(false);// set style of message

            if (selectedMangerID <= 0) {
                sc.setMessage("Please select a property manager");// set the message
                return "";
            }

            sc.setMessage("Please select a sale property");
            return "";
        } else {
            try {

                boolean managed = allocationEJB.isPropertyIsManaged(selectedSaleID);// check the selected property is managed by selected manager
                boolean managedBy = allocationEJB.isGivenPropertyIsManagedByTheManager(selectedSaleID, selectedMangerID);// check the selected property is managed by an other manager
                if (managed | managedBy) {// if it is true the show the error message on view
                    sc.setShowMSG(true);
                    sc.setSuccsessfully(false);
                    if (managedBy) {

                        sc.setMessage("This selected sale property already managed");

                    } else {
                        sc.setMessage("This sale property already managed by another manager");
                    }
                    return "";

                } else {
                    PropertyManager manager = pm.findByID(selectedMangerID).get(0);// find a selected manager object
                    ForSale fSale = fs.findByID(selectedSaleID).get(0);// find selected propery object
                    Allocation alct = new Allocation();
                    alct.setManager(manager);
                    alct.setProperty(fSale);
                    alct.setAllocatedDate(new Date());
                    allocationEJB.insert(alct);// insert to databse
                    sc.setShowMSG(true);
                    sc.setSuccsessfully(true);
                    sc.setMessage("Allocate sale property successfully");
                    SendMSG.flashData("newAllocation", alct);
                    return "/allocation/listAllAllocation.fx";
                }

            } catch (Exception ex) {
                Throwable root = FunctionalClass.findRootError(ex);// find root error
                SendMSG.flashData("msg", "Error" + root.getMessage());// send error message to errorPage.fx (xhtml)
                return "/errorPage.fx";
            }
        }

    }
    
    /**
     * This method is called after the request is constructed (initialization )
     * This method is to load all data to combo box on view and setup default selected value for combo box
     */
    @PostConstruct
    private void loadDataToCombobox() {
        try {
            // data for loading into combo box
            managers = pm.selectAll();
            forSales = fs.selectAll();
            forRents = fr.selectAll();
            //default value for combo box
            selectedMangerID = -1;
            selectedRentID = -1;
            selectedSaleID = -1;
            // hide message box
            sc.setShowMSG(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //---------------------------------getters and stters------------------------------------------

    /**
     *
     * @return
     */
    public List<PropertyManager> getManagers() {
        return managers;
    }

    /**
     *
     * @param managers
     */
    public void setManagers(List<PropertyManager> managers) {
        this.managers = managers;
    }

    /**
     *
     * @return
     */
    public List<ForSale> getForSales() {
        return forSales;
    }

    /**
     *
     * @param forSales
     */
    public void setForSales(List<ForSale> forSales) {
        this.forSales = forSales;
    }

    public List<ForRent> getForRents() {
        return forRents;
    }

    /**
     *
     * @param forRents
     */
    public void setForRents(List<ForRent> forRents) {
        this.forRents = forRents;
    }

    public long getSelectedMangerID() {
        return selectedMangerID;
    }

    /**
     *
     * @param selectedMangerID
     */
    public void setSelectedMangerID(long selectedMangerID) {
        this.selectedMangerID = selectedMangerID;
    }

    /**
     *
     * @return
     */
    public long getSelectedRentID() {
        return selectedRentID;
    }

    /**
     *
     * @param selectedRentID
     */
    public void setSelectedRentID(long selectedRentID) {
        this.selectedRentID = selectedRentID;
    }

    /**
     *
     * @return
     */
    public long getSelectedSaleID() {
        return selectedSaleID;
    }

    /**
     *
     * @param selectedSaleID
     */
    public void setSelectedSaleID(long selectedSaleID) {
        this.selectedSaleID = selectedSaleID;
    }

}
