/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller.saleProperty;

import controller.FunctionalClass;
import controller.SendMSG;
import controller.SessionController;
import entities.ForSale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import sessionBeans.ForSaleEJB;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This managed bean is a backed bean (controller in business tier)that handle mainly addNewSale.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extension is changed to be .fx in web.xml file
 * Receive data from presentation and talk to persistence tier
 */
@Named(value = "addNewSalePropertyRequest")
@RequestScoped
public class AddNewSalePropertyRequest {

    @Inject// independency injection from other managed bean
    SessionController sc;

    @EJB//@EJB is independency injection from persistence teir
    ForSaleEJB fsejb;

    private ForSale newSale;

    /**
     * Creates a new instance of AddNewSalePropertyRequest
     * Default constructor
     */
    public AddNewSalePropertyRequest() {
    }

    /**
     * this method is to navigate to addNewSale page
     * @return URL of addNewSale
     */
    public String openAddNewSaleProperty() {
        return "/property/sale/addNewSale.fx";
    }

    /**
     * this method is to insert new property to database
     * @return URL
     */
    public String insertNewSaleProperty() {
        try {

            fsejb.insert(newSale);// insert to new property to the database
            sc.setCurrentSale(newSale);// set current sale property in SessionController that is used in viewSalePropertyDetails page
            sc.setShowMSG(true);// show message block
            sc.setSuccsessfully(true);// set style of message block
            sc.setMessage("Insert new sale property successfully");// the message that show in message block
            sc.setSeeMore(false);// show all details of property
            return "/property/sale/viewSalePropertyDetails.fx";

        } catch (Exception ex) {
            Throwable root = FunctionalClass.findRootError(ex);// find root error
            SendMSG.flashData("msg", "Error" + root.getMessage());// send error message to errorPage.fx (xhtml)
            return "/errorPage.fx";
        }
    }

    /**
     * This method is called after the request is constructed (initialization )
     * This method is to setup default values for variables
     */
    @PostConstruct
    private void ini() {
        newSale = new ForSale();
    }

    //-----------------------getter setter--------------------------------------------------

    /**
     *
     * @return
     */
    public ForSale getNewSale() {
        return newSale;
    }

    /**
     *
     * @param newSale
     */
    public void setNewSale(ForSale newSale) {
        this.newSale = newSale;
    }

}
