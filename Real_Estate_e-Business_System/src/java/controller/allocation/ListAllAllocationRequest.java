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
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import sessionBeans.AllocationEJB;
import sessionBeans.ForRentEJB;
import sessionBeans.ForSaleEJB;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This managed bean is a backed bean (controller in business tier)that handle listAllAllocation.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extention is changed to be .fx in web.xml file
 * Receive data from presentation tier and talk to persistence tier
 */
@Named(value = "listAllAllocationRequest")
@RequestScoped
public class ListAllAllocationRequest {

    private List<Allocation> listAllocations;

    private boolean isNewAllocation;// because the controller is resuseable so this variable is to know what is use case is (New allocation or just view)

    // dependency injection @EJB from persistence tier @Inject from other managed beans
    @EJB
    private AllocationEJB aejb;

    @EJB
    private ForRentEJB frejb;

    @EJB
    private ForSaleEJB fsejb;

    @Inject
    private SessionController sc;

    /**
     * Creates a new instance of ListAllAllocationRequest
     * Default constructor
     */
    public ListAllAllocationRequest() {
        listAllocations = new LinkedList<>();
        isNewAllocation = false;
    }

    /**
     * fire up the page listAllAllocation
     * @return URL
     */
    public String openListAllAllocation() {

        sc.setShowMSG(false);// hide message box
        return "/allocation/listAllAllocation.fx";
    }

    /**
     * navigate to viewManagerDetails
     * @param mng object PropertyManager
     * @return URL
     */
    public String viewManagerDetails(PropertyManager mng) {
        sc.setCurrentManager(mng);// set current manager and it is used in the next page. this approach is one way to send data to next page
        sc.setShowMSG(false);// hide message box
        return "/manager/viewManagerDetails.fx";
    }

    /**
     * this method is to view property details
     * @param ID the id of property
     * @return URL
     */
    public String viewPropertyDetails(long ID) {
        try {
            sc.setShowMSG(false);// hide message box
            sc.setSeeMore(true);// show more data on view page
            List<ForRent> tmpRent = frejb.findByID(ID);// find rent object
            List<ForSale> tmpSale = fsejb.findByID(ID);// find sale object
            if (tmpRent.size() > 0) {
                sc.setCurrentRent(tmpRent.get(0));// set current rent object that is used in viewRentPropertyDetails 
                return "/property/rent/viewRentPropertyDetails.fx";
            }
            sc.setCurrentSale(tmpSale.get(0));
            return "/property/sale/viewSalePropertyDetails.fx";

        } catch (Exception ex) {
            Throwable root = FunctionalClass.findRootError(ex);//find root error
            SendMSG.flashData("msg", "Error" + root.getMessage());// send error message to errorPage.fx (xhtml)
            return "/errorPage.fx";
        }
    }

    /**
     * this method is to delete allocation base on option of current view
     * @param al allocation object
     * @param option type of object 
     * 1:the current view is after add new allocation, if the user wants to delete the allocation,
     * the page will redirect to addNewAllocation page
     * 2: if the current view is listAllAllocation page simple just refresh the current page
     * 3: if the current view is searchAllocation page simple just refresh the current page
     * @return
     */
    public String deleteAllocation(Allocation al, int option) {
        try {

            aejb.delete(al);
            switch (option) {
                case 1:
                    sc.setShowMSG(false);
                    return "/allocation/addNewAllocation.fx";
                case 2:
                    return "/allocation/listAllAllocation.fx?faces-redirect=true";
                default:
                    return "/allocation/searchAllocation.fx?faces-redirect=true";
            }
        } catch (Exception ex) {
            Throwable root = FunctionalClass.findRootError(ex);// find root error
            SendMSG.flashData("msg", "Error" + root.getMessage());// send error message to errorPage.fx (xhtml)
            return "/errorPage.fx";
        }
    }
    /**
     * this method is called after the request is constructed.
     * get sent data from previous page if there is any
     */
    @PostConstruct
    private void ini() {

        Object dataIn = SendMSG.GetFlashData("newAllocation");// get sent data from previous page
        if (dataIn != null) {// if it contain data that mean the new allocation that is just made, otherwise it is listAllAllocation use case
            listAllocations.add((Allocation) dataIn);// add to the list
            isNewAllocation = true;// this variable is to controll how the message box behave 
        } else {
            try {
                isNewAllocation = false;
                listAllocations = aejb.selectAll();// load allocation on the list which is used to show on view
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //---------------------------getters and setters-----------------------------------------------

    /**
     *
     * @return
     */
    public List<Allocation> getListAllocations() {
        return listAllocations;
    }

    /**
     *
     * @param listAllocations
     */
    public void setListAllocations(List<Allocation> listAllocations) {
        this.listAllocations = listAllocations;
    }

    /**
     *
     * @return
     */
    public boolean isIsNewAllocation() {
        return isNewAllocation;
    }

    /**
     *
     * @param isNewAllocation
     */
    public void setIsNewAllocation(boolean isNewAllocation) {
        this.isNewAllocation = isNewAllocation;
    }

}
