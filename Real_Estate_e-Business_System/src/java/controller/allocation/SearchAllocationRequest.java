/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller.allocation;

import controller.SessionController;
import entities.AllocationID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import sessionBeans.AllocationEJB;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This managed bean is a backed bean (controller in business tier)that handle searchAllocation.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extention is changed to be .fx in web.xml file
 * Receive data from presentation tier and talk to persistence tier
 */
@Named(value = "searchAllocationRequest")
@RequestScoped
public class SearchAllocationRequest {

    private String keyWord;// the entered key word from user 

    // dependency injection @EJB from persistence tier @Inject from other managed beans
    @Inject
    private SessionController sc;

    @EJB
    private AllocationEJB aejb;

    /**
     * Creates a new instance of SearchAllocationRequest
     */
    public SearchAllocationRequest() {
    }

    /**
     * fire up the page searchAllocation
     * @return
     */
    public String openSearchAllocation() {

        return "/allocation/searchAllocation.fx";
    }

    /**
     * this method is called after the request is constructed.
     * set up the default values for variables
     */
    @PostConstruct
    private void ini() {
        sc.setShowMSG(false);
        sc.setSearchResultListAllocations(null);
    }

    /**
     * this method is to find allocation based on ID
     * the ID is composite key is combination of manager ID and property ID with - in the middle
     */
    public void findAllcation() {
        try {

            sc.setShowMSG(true);//show mssage box
            String[] arrayKey = keyWord.trim().split("-");// break allocation id to 2 items managerID and propertyID
            long managerID = Long.valueOf(arrayKey[0].trim());
            long propertyID = Long.valueOf(arrayKey[1].trim());
            AllocationID id = new AllocationID(managerID, propertyID);
            sc.setSearchResultListAllocations(aejb.findByID(id));// find allocation based on given key words which is stored in AllocationID object
            if (sc.getSearchResultListAllocations().size() <= 0) {// if is true
                sc.setSuccsessfully(false);// show error style message box
                sc.setMessage("The allocation [" + keyWord + "] could not be found");// set message
            } else {
                sc.setSuccsessfully(true);// show successful style of message box
                sc.setMessage("Found " + sc.getSearchResultListAllocations().size() + " allocation with the matching ID.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//--------------------------------getters ans setter--------------------------------------------

    /**
     *
     * @return
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     *
     * @param keyWord
     */
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

}
