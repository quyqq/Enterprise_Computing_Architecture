/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller.manager;

import controller.SessionController;
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
 * This managed bean is a backed bean (controller in business tier)that handle searchManager.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extention is changed to be .fx in web.xml file
 * Receive data from presentation and talk to persistence tier
 */
@Named(value = "searchManagerRequest")
@RequestScoped
public class SearchManagerRequest {

    @EJB// inject session bean
    private PropertyManagerEJB pMEJB;
    @Inject // inject from other managed beans
    private SessionController sc;

    private String keyWord;

    /**
     * Creates a new instance of SearchManager
     */
    public SearchManagerRequest() {
        keyWord = "";
    }

    /**
     * Open page and clear searhedListManager
     *
     * @return link to access the page
     */
    public String openSearchManager() {
        return "/manager/searchManager.fx";
    }
    /**
    * This method is called after the request is constructed (initialization )
    * This method is to setup default values for variables
    */
    @PostConstruct
    private void setShowMSG() {
        sc.setShowMSG(false);
        sc.setSearchResultListPropertyManager(null);
    }

    /**
     * this method is to find all property manager base on given keyword
     *
     */
    public void acctionLoadFindManager() {
        try {

            sc.setShowMSG(true);// show mesage block
            if (keyWord.trim().length() > 0) {// check key word is not empty
                sc.setSearchResultListPropertyManager(pMEJB.find(keyWord.trim()));// find and set the list of result in SessionContrller
                if (sc.getSearchResultListPropertyManager().size() <= 0) {// if is empty
                    sc.setSuccsessfully(false);// set style of message block
                    sc.setMessage("The manager [" + keyWord + "] could not be found");// set message show in message block
                } else {
                    sc.setSuccsessfully(true);
                    sc.setMessage("Found " + sc.getSearchResultListPropertyManager().size() + " manager with name that contains the given key words.");
                }
            } else {
                sc.setSearchResultListPropertyManager(null);// clear the list

                sc.setSuccsessfully(false);
                sc.setMessage("Please enter name of property manager");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //---------------------------------getter and setter-------------------------------------
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

}
