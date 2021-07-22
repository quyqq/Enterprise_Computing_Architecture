/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package controller;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This class is to find the root of error when the application throw an exception.
 * The exception always return general message so this class is to find the cause message.
 */
public class FunctionalClass {
    
    /**
     * This method is to find the root of error
     * @param in Throwable type
     * @return the root of error
     */
    public static Throwable findRootError(Throwable in)
    {
        Throwable out = in;
        while (out.getCause() != null) {// loop and find the root when is null that mean there are no child left. So it is the root
                out = out.getCause();
            }
        
        return out;
    }
}
