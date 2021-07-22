/*
 * Application: Real Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This managed bean is a backed bean (controller in business tier)that handle errorPage.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extention is changed to be .fx in web.xml file
 * Receive data from presentation tier and talk to persistence tier
 */
@Named(value = "ErrorController")
@RequestScoped
public class ErrorController {

    /**
     * Creates a new instance of ErrorController
     */
    public ErrorController() {
    }

    /**
     *
     * @return the message from a previous page
     */
    public String actionGetErrorMessage() {
        return "There was an error corrupted with details: "  + FacesContext.getCurrentInstance().getExternalContext()// get sent data adn return it
                .getFlash().get("msg").toString();
    }
    
    /**
     *
     * @return link of errorPage.fx that can be navigated to
     */
    public String openErrorPage() {
       
        return "/errorPage.fx?faces-redirect=true";
    }
}
