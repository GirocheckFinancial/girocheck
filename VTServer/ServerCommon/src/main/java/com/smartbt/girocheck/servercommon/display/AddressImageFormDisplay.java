
package com.smartbt.girocheck.servercommon.display;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alejo
 */

@XmlRootElement
public class AddressImageFormDisplay implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String addressImage;

    public AddressImageFormDisplay(){}
    
    /**
     * @return the addressImage
     */
    public String getAddressImage() {
        return addressImage;
    }

    /**
     * @param addressImage the addressImage to set
     */
    public void setAddressImage(String addressImage) {
        this.addressImage = addressImage;
    }
    
}
