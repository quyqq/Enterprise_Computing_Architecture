/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller.rentProperty;

import controller.SessionController;
import entities.ForRent;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import sessionBeans.ForRentEJB;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This managed bean is a backed bean (controller in business tier)that handle listAllRent.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extension is changed to be .fx in web.xml file
 * Receive data from presentation and talk to persistence tier
 */
@Named(value = "listAllRentProperyRequest")
@RequestScoped
public class ListAllRentProperyRequest {

    @EJB //@EJB is independency injection from persistence teir
    ForRentEJB fREJB;
    
    @Inject// independency injection from other managed bean
    SessionController sc;

    private List<ForRent> listOfRentProperty;

    /**
     * Creates a new instance of ListAllRentProperyRequest
     * Default constructor
     */
    public ListAllRentProperyRequest() {
    }

    /**
     * this method is to navigate to listAllRent page
     * @return URL of listAllRent
     */
    public String openListAllRent() {
        return "/property/rent/listAllRent.fx";
    }
    
    /**
     * this method is to navigate to viewRentPropertyDetails page
     * @param fr rent property object
     * @return URL of viewRentPropertyDetails
     */
    public String viewRentPropertyDetail(ForRent fr)
    {
        sc.setShowMSG(false);// hide message block
        sc.setCurrentRent(fr); // set curent rent property that will be used in viewRentPropertyDetails page
        sc.setSeeMore(true);// set to show all details
        return "/property/rent/viewRentPropertyDetails.fx";
    }
    
    /**
     * This method is called after the request is constructed (initialization )
     * This method is to setup default values 
     */
    @PostConstruct
    public void listAllRentProperty() {
        
        try {
            listOfRentProperty = fREJB.selectAll();// load all rent properties from database
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
    //-----------------------------------getter and setter---------------------------------------

    /**
     *
     * @return
     */

    public List<ForRent> getListOfRentProperty() {
        return listOfRentProperty;
    }

    /**
     *
     * @param listOfRentProperty
     */
    public void setListOfRentProperty(List<ForRent> listOfRentProperty) {
        this.listOfRentProperty = listOfRentProperty;
    }
    
}
