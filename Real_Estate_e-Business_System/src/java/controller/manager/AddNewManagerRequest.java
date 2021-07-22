/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller.manager;

import controller.FunctionalClass;
import controller.SendMSG;
import controller.SessionController;
import entities.PropertyManager;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import sessionBeans.PropertyManagerEJB;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This managed bean is a backed bean (controller in business tier)that handle addNewManager.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extention is changed to be .fx in web.xml file
 * Receive data from presentation and talk to persistence tier
 */
@Named(value = "addNewManagerRequest")
@RequestScoped
public class AddNewManagerRequest {

    @EJB// inject session bean
    private PropertyManagerEJB pMEJB;
    @Inject// inject from other managed bean
    private SessionController sc;
    
    private PropertyManager manager;// new manager 

    /**
     * Creates a new instance of AddNewManagerRequest
     */
    public AddNewManagerRequest() {
        manager = new PropertyManager();
    }

    /**
     * open new manager face
     *
     * @return link to access the page
     */
    public String openAddNewManagerPage() {

        return "/manager/addNewManager.fx";

    }

    /**
     * inset new property manager     *
     * @return
     */
    public String insertNewManager() {

        try {
            pMEJB.insert(manager);//insert new manager object to database
            sc.setCurrentManager(manager);// set current manager which is used in viewManagerDetails page
            sc.setSuccsessfully(true);// set style of mesage block
            sc.setMessage("Insert new property manger successfully");// set message
            sc.setShowMSG(true);// show mesage bloxk
            return "/manager/viewManagerDetails.fx";
        } catch (Exception ex) {
            Throwable root = FunctionalClass.findRootError(ex);// find root error
            SendMSG.flashData("msg", root.getMessage());// send error message to errorPage.fx (xhtml)
            return "/errorPage.fx";
        }

    }
    /**
     * This method is called after the request is constructed
     * set default values (initialization )
     */
    @PostConstruct
    private void ini() {
        sc.setShowMSG(false);
    }
    
    //---------------------stter and getter-------------------------------------------------
    public PropertyManager getManager() {
        return manager;
    }

    public void setManager(PropertyManager manager) {
        this.manager = manager;
    }

   
    
}
