/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller.saleProperty;

import controller.SessionController;
import entities.ForSale;
import java.util.List;
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
 * This managed bean is a backed bean (controller in business tier)that handle mainly listAllSale.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extension is changed to be .fx in web.xml file
 * Receive data from presentation and talk to persistence tier
 */
@Named(value = "listAllSalePropertyRequest")
@RequestScoped
public class ListAllSalePropertyRequest {

    @Inject// independency injection from other managed bean
    SessionController sc;
    
    @EJB//@EJB is independency injection from persistence teir
    ForSaleEJB fSEJB;
    
    
    private List<ForSale> listAllSale;// all sale properties in the database

    /**
     * Creates a new instance of ListAllSalePropertyRequest
     * default constructor
     */
    public ListAllSalePropertyRequest() {
    }

    /**
     * this method is to navigate to listAllSale page
     * @return URL of listAllSale
     */
    public String openListSaleProperty() {
        return "/property/sale/listAllSale.fx";
    }
    
    /**
     * this method is to navigate to viewSalePropertyDetails page
     * @param fs sale property
     * @return URL of viewSalePropertyDetails
     */
    public String viewSalePropertyDetail(ForSale fs)
    {
        sc.setShowMSG(false);
        sc.setCurrentSale(fs); 
        sc.setSeeMore(true);
        return "/property/sale/viewSalePropertyDetails.fx";
    }
    
    /**
     * This method is called after the request is constructed (initialization )
     * This method is to load all data to listAllSale
     */
    @PostConstruct
    public void listAllSaleProperty()
    {
        try {
            listAllSale = fSEJB.selectAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //---------------------------------getter and setter----------------------------------------

    /**
     *
     * @return
     */

    public List<ForSale> getListAllSale() {
        return listAllSale;
    }

    /**
     *
     * @param listAllSale
     */
    public void setListAllSale(List<ForSale> listAllSale) {
        this.listAllSale = listAllSale;
    }
    
}
