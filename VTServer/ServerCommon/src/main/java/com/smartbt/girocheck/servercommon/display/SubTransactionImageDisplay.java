
package com.smartbt.girocheck.servercommon.display;

import java.io.Serializable;

/**
 *
 * @author Alejo
 */


public class SubTransactionImageDisplay implements Serializable{
    
    private static long serialVersionUID = 1L;
    
        /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID( long aSerialVersionUID ) {
        serialVersionUID = aSerialVersionUID;
    }
    
    private Integer id;
    
    private String image;
    
    public SubTransactionImageDisplay(){}

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }
    
}
