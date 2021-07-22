/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller;

import javax.faces.context.FacesContext;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This class is to send data to next page and it also get data from a previous page.
 */
public class SendMSG {

    /**
     * flash (send) data to next page
     * @param <T> generic type
     * @param name variable (parameter) name
     * @param msg data sending
     */
    public static <T> void flashData(String name,T  msg) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put(name,msg);
    }

    /**
     *
     * @param name parameter name to receive. This name need to be exactly the same with sending parameter
     * @return any kind of the receiving data and it can be cast latter. We can improve it by changing 
     * public class SendMSG 
     * to 
     * public class SendMSG<B>
     * 
     * with 
     * 
     * public B GetFlashData(String name) {
     * return (B)FacesContext.getCurrentInstance().getExternalContext().getFlash().get(name);
     * }
     * 
     * 
     */
    public static Object GetFlashData(String name) {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(name);
    }
}
