/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller;

import entities.Allocation;
import entities.ForRent;
import entities.ForSale;
import entities.PropertyManager;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This managed bean is a backed bean (controller or business tier)that handle for whole session.
 * It is created only once when client access the application, it is used to store global variables 
 */
@Named(value = "SessionController")
@SessionScoped
public class SessionController implements Serializable {

    // those list is to store the searching results
    private List<ForRent> searchResultListRentProperty;
    private List<ForSale> searchResultListSaleProperty;
    private List<Allocation> searchResultListAllocations;
    private List<PropertyManager> searchResultListPropertyManager;
    // those variables is to store current objec to show it in a view page
    private PropertyManager currentManager;
    private ForRent currentRent;
    private ForSale currentSale;
    // those variables is switches that controll the message showing up on pages 
    private boolean showMSG;
    private boolean succsessfully;
    private String message;
    private boolean seeMore;

    /**
     * default constructor
     * set up an initialize variables 
     */
    public SessionController() {
        searchResultListRentProperty = new LinkedList<>();
        searchResultListSaleProperty = new LinkedList<>();
        searchResultListAllocations = new LinkedList<>();
        searchResultListPropertyManager = new LinkedList<>();
        currentManager = new PropertyManager();
        currentRent = new ForRent();
        currentSale = new ForSale();
        showMSG = false;
        succsessfully = false;
        message = "";
        seeMore = false;
    }

    //-------------------------get and set---------------------------------------
    public List<ForRent> getSearchResultListRentProperty() {
        return searchResultListRentProperty;
    }

    public void setSearchResultListRentProperty(List<ForRent> searchResultListRentProperty) {
        this.searchResultListRentProperty = searchResultListRentProperty;
    }

    public List<ForSale> getSearchResultListSaleProperty() {
        return searchResultListSaleProperty;
    }

    public void setSearchResultListSaleProperty(List<ForSale> searchResultListSaleProperty) {
        this.searchResultListSaleProperty = searchResultListSaleProperty;
    }

    public List<Allocation> getSearchResultListAllocations() {
        return searchResultListAllocations;
    }

    public void setSearchResultListAllocations(List<Allocation> searchResultListAllocations) {
        this.searchResultListAllocations = searchResultListAllocations;
    }

    public List<PropertyManager> getSearchResultListPropertyManager() {
        return searchResultListPropertyManager;
    }

    public void setSearchResultListPropertyManager(List<PropertyManager> searchResultListPropertyManager) {
        this.searchResultListPropertyManager = searchResultListPropertyManager;
    }

    public PropertyManager getCurrentManager() {
        return currentManager;
    }

    public void setCurrentManager(PropertyManager currentManager) {
        this.currentManager = currentManager;
    }

    public ForRent getCurrentRent() {
        return currentRent;
    }

    public void setCurrentRent(ForRent currentRent) {
        this.currentRent = currentRent;
    }

    public ForSale getCurrentSale() {
        return currentSale;
    }

    public void setCurrentSale(ForSale currentSale) {
        this.currentSale = currentSale;
    }

    public boolean isShowMSG() {
        return showMSG;
    }

    public void setShowMSG(boolean showMSG) {
        this.showMSG = showMSG;
    }

    public boolean isSuccsessfully() {
        return succsessfully;
    }

    public void setSuccsessfully(boolean succsessfully) {
        this.succsessfully = succsessfully;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSeeMore() {
        return seeMore;
    }

    public void setSeeMore(boolean seeMore) {
        this.seeMore = seeMore;
    }

    

   
}
