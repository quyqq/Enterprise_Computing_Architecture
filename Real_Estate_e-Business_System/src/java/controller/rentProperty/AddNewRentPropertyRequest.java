/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller.rentProperty;

import controller.FunctionalClass;
import controller.SendMSG;
import controller.SessionController;
import entities.ForRent;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import sessionBeans.ForRentEJB;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This managed bean is a backed bean (controller in business tier)that handle addNewRent.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extension is changed to be .fx in web.xml file
 * Receive data from presentation and talk to persistence tier
 */
@Named(value = "addNewRentPropertyRequest")
@RequestScoped
public class AddNewRentPropertyRequest {

    private ForRent newRent;
    
    @Inject// independency injection from other managed bean
    private SessionController sc;
    
    @EJB//@EJB is independency injection from persistence teir
    ForRentEJB fREJB;

    /**
     * Creates a new instance of AddNewRentPropertyRequest
     * Default constructor
     */
    public AddNewRentPropertyRequest() {
        newRent = new ForRent();
    }

    /**
     * this method is to navigate to addNewRent page
     * @return URL of addNewRent
     */
    public String openAddNewRent() {
        sc.setShowMSG(false);
        return "/property/rent/addNewRent.fx";
    }

    /**
     * this method is to insert new property to database
     * @return URL
     */
    public String insertNewRentProperty() {
        try {

            fREJB.insert(newRent);// insert to database
            sc.setCurrentRent(newRent);// set current rent in SessionController to use in viewRentPropertyDetails page 
            sc.setShowMSG(true);// show message block
            sc.setSuccsessfully(true);// set message block style
            sc.setMessage("Insert new rent property successfully");// set message that shows in message block
            sc.setSeeMore(false);// show all information of the current property in viewRentPropertyDetails page
            return "/property/rent/viewRentPropertyDetails.fx";

        } catch (Exception ex) {
            Throwable root = FunctionalClass.findRootError(ex);// find root error
            SendMSG.flashData("msg", "Error" + root.getMessage());// send error message to errorPage.fx (xhtml)
            return "/errorPage.fx";
        }
    }

    //---------------------------getter and setter---------------------------------------

    /**
     *
     * @return
     */
    public ForRent getNewRent() {
        return newRent;
    }

    /**
     *
     * @param newRent
     */
    public void setNewRent(ForRent newRent) {
        this.newRent = newRent;
    }

}
