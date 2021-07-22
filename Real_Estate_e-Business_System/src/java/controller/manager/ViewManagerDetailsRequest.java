/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller.manager;

import java.util.Base64;
import controller.SessionController;
import entities.Allocation;
import entities.PropertyManager;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.Part;
import sessionBeans.AllocationEJB;
import sessionBeans.PropertyManagerEJB;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This managed bean is a backed bean (controller in business tier)that handle viewManagerDetails.xhtml.
 * It is created in any request and after finish the request, it will be removed
 * The .xhtml extention is changed to be .fx in web.xml file
 * Receive data from presentation and talk to persistence tier
 */
@Named(value = "viewManagerDetailsRequest")
@RequestScoped
public class ViewManagerDetailsRequest {

    private PropertyManager manager;
    private Part picture;// store picture
    private List<Allocation> listAllocatedProperty;// the list of allocated propery which is allocated for the current property manager

    @Inject// inject from other managed bean
    private SessionController sc;

     //@EJB is independency injection from persistence teir
    @EJB
    private PropertyManagerEJB pMEJB;
    
    @EJB
    private AllocationEJB aejb;

    /**
     * Creates a new instance of VewManagerDetails
     * default constructor
     */
    public ViewManagerDetailsRequest() {
        manager = new PropertyManager();
    }

    /**
     * This method is called after the request is constructed (initialization )
     * This method is to setup default value for variables
     */
    @PostConstruct
    public void getAllPassedValues() {
        manager = sc.getCurrentManager();
        listAllocatedProperty = aejb.FindAllocationByManagerID(manager.getId());// load properties of the current manager
    }

    /**
     * Load picture to image tag by convert it to String by using Base64
     * encoding then save in database however, it will increate data size by 30%
     *
     * @return readable string that can show as a picture
     */
    public String loadPicture() {
        if (manager.getPicture() != null) {

            return new String(manager.getPicture(), StandardCharsets.UTF_8);// convert byte to string (decode)
        } else {
            return null;
        }
    }

    /**
     * this method is to upload selected picture to database by use Base64 to
     * convert picture to string and save it as byte[]
     */
    public void uploadePic() {

        if (picture != null) {

            try {

                Image originalPic = ImageIO.read(picture.getInputStream()).getScaledInstance(150, 150, Image.SCALE_DEFAULT);// read picture to memory and reduce size
                BufferedImage bi = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);// create a buffer before reproduce picture

                // produce picture 
                Graphics2D g2d = bi.createGraphics();
                g2d.drawImage(originalPic, 0, 0, null);
                g2d.dispose();

                byte[] imageInByte;
                try (ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream()) {// write new picture as byte[] 
                    ImageIO.write(bi, picture.getSubmittedFileName().split("\\.")[1], arrayOutputStream);
                    arrayOutputStream.flush();
                    imageInByte = arrayOutputStream.toByteArray();
                }

                manager.setPicture(("data:image/"
                        + picture.getSubmittedFileName().split("\\.")[1].toLowerCase() + ";base64,"
                        + Base64.getEncoder().encodeToString(imageInByte)).getBytes());// save picture in manager variable and encode it

                pMEJB.update(manager);
                sc.setMessage("Upload file [" + picture.getSubmittedFileName() + "] successfully");// set message that show in message block
                sc.setSuccsessfully(true);// style of message block
                sc.setShowMSG(true);// show message block

            } catch (Exception e) {
                sc.setMessage("Upload file [" + picture.getSubmittedFileName() + "] unsuccessfully: " + e.getMessage());
                sc.setSuccsessfully(false);
                sc.setShowMSG(true);
            }
        }

        
    }

    
    //-------------------------------etters and getter-------------------------------------------

    /**
     *
     * @return
     */

    public PropertyManager getManager() {
        return manager;
    }

    /**
     *
     * @param manager
     */
    public void setManager(PropertyManager manager) {
        this.manager = manager;
    }

    /**
     *
     * @return
     */
    public Part getPicture() {
        return picture;
    }

    /**
     *
     * @param picture
     */
    public void setPicture(Part picture) {
        this.picture = picture;
    }

    /**
     *
     * @return
     */
    public List<Allocation> getListAllocatedProperty() {
        return listAllocatedProperty;
    }

    /**
     *
     * @param listAllocatedProperty
     */
    public void setListAllocatedProperty(List<Allocation> listAllocatedProperty) {
        this.listAllocatedProperty = listAllocatedProperty;
    }

    

}
