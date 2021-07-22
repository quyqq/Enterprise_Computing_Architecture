/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller.rentProperty;

import controller.SessionController;
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
 * This managed bean is a backed bean (controller in business tier)that handle mainly searchRent.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extension is changed to be .fx in web.xml file
 * Receive data from presentation and talk to persistence tier
 */
@Named(value = "searchRentPropertyRequest")
@RequestScoped
public class SearchRentPropertyRequest {

    @EJB// inject session bean
    private ForRentEJB fREJB;
    @Inject// independency injection from other managed bean
    private SessionController sc;

    private long keyWord;

    /**
     * Creates a new instance of SearchRentProperty
     * Default constructor
     */
    public SearchRentPropertyRequest() {
    }

    /**
     * this method is to navigate to searchRent page
     * @return URL of searchRent
     */
    public String openSearchRent() {
        return "/property/rent/searchRent.fx";
    }

    /**
     * this method is to find rent property based on the property ID
     */
    public void acctionLoadFindRentProperty() {

        try {
            sc.setShowMSG(true);// show message block
            sc.setSearchResultListRentProperty(fREJB.findByID(keyWord));// load and find property base on given ID
            if (sc.getSearchResultListRentProperty().size() <= 0) {// if it is empty
                sc.setSuccsessfully(false);// set message block style
                sc.setMessage("The Rent property ID [" + keyWord + "] could not be found");// set message that shows in message block
            } else {
                sc.setSuccsessfully(true);
                sc.setMessage("Found " + sc.getSearchResultListRentProperty().size() + " property with the matching ID.");
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    /**
     * This method is called after the request is constructed (initialization )
     * This method is to setup default values 
     */
    @PostConstruct
    private void ini() {
        sc.setShowMSG(false);
        sc.setSearchResultListRentProperty(null);
        
    }
    //-------------------------------getter and setter--------------------------------------

    /**
     *
     * @return
     */
    public long getKeyWord() {
        return keyWord;
    }

    /**
     *
     * @param keyWord
     */
    public void setKeyWord(long keyWord) {
        this.keyWord = keyWord;
    }

}
