/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller.manager;

import controller.SessionController;
import entities.PropertyManager;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import sessionBeans.PropertyManagerEJB;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This managed bean is a backed bean (controller in business tier)that handle listAllManagers.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extention is changed to be .fx in web.xml file
 * Receive data from presentation and talk to persistence tier
 */
@Named(value = "listAllManagersRequest")
@RequestScoped
public class ListAllManagersRequest {

    @EJB// inject session bean
    private PropertyManagerEJB pMEJB;
    @Inject// inject from other managed bean
    private SessionController sc;

    private List<PropertyManager> listPropertyManager;

    /**
     * Creates a new instance of ListAllManagers
     */
    public ListAllManagersRequest() {
    }

    /**
     * open page and list all property managers
     *
     * @return link to access the page
     */
    public String openListAllMaagers() {
        return "/manager/listAllManagers.fx";
    }

    public String viewManagerDetail(PropertyManager pm) {
        sc.setShowMSG(false);// hide measge block
        sc.setCurrentManager(pm);// set current manager in SesscionController
        return "/manager/viewManagerDetails.fx";
    }

    /**
     * this method is to load all property manager
     *
     */
    @PostConstruct
    public void acctionLoadAllManagers() {
        try {
            listPropertyManager = pMEJB.selectAll();// load all managers in database
            sc.setShowMSG(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
//--------------------------getter and setter-----------------------------------

    /**
     *
     * @return
     */
    public List<PropertyManager> getListPropertyManager() {
        return listPropertyManager;
    }

    /**
     *
     * @param listPropertyManager
     */
    public void setListPropertyManager(List<PropertyManager> listPropertyManager) {
        this.listPropertyManager = listPropertyManager;
    }
    

}
