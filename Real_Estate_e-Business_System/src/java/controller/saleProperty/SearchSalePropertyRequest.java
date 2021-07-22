/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller.saleProperty;

import controller.SessionController;
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
 * This managed bean is a backed bean (controller in business tier)that handle mainly searchSale.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extension is changed to be .fx in web.xml file
 * Receive data from presentation and talk to persistence tier
 */
@Named(value = "searchSalePropertyRequest")
@RequestScoped
public class SearchSalePropertyRequest {

    @Inject// independency injection from other managed bean
    SessionController sc;
    
    @EJB//@EJB is independency injection from persistence teir
    ForSaleEJB fSEJB;
    
    private long keyWord;

    /**
     * Creates a new instance of SearchSalePropertyRequest
     * default constructor
     */
    public SearchSalePropertyRequest() {
    }

    /**
     * this method is to navigate to searchSale page
     * @return URL of searchSale
     */
    public String openSearchSaleProperty() {
        return "/property/sale/searchSale.fx";
    }
    
    /**
     * this method is to find sale property based on given ID
     */
    public void acctionLoadFindSaleProperty() {

        try {
            sc.setShowMSG(true);// show message bllock
            sc.setSearchResultListSaleProperty(fSEJB.findByID(keyWord));// find and load property to the list in SessionController
            if (sc.getSearchResultListSaleProperty().size() <= 0) {// if it is empty
                sc.setSuccsessfully(false);// set style of message block
                sc.setMessage("The Sale property ID [" + keyWord + "] could not be found");// set message that shows in message block
            } else {
                sc.setSuccsessfully(true);
                sc.setMessage("Found " + sc.getSearchResultListSaleProperty().size() + " property with the matching ID.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    
    /**
     * This method is called after the request is constructed (initialization )
     * This method is to setup default value to variables
     */
    @PostConstruct
    private void ini()
    {
        sc.setShowMSG(false);
        sc.setSearchResultListSaleProperty(null);
    }
    
    
    //-------------------------------------------------------------------------

    public long getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(long keyWord) {
        this.keyWord = keyWord;
    }
    
}
