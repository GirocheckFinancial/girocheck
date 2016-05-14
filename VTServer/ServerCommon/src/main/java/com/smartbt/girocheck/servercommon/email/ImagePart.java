
package com.smartbt.girocheck.servercommon.email;

/**
 *
 * @author Roberto
 */


public class ImagePart {
    private String imageBase64;
    private String imageContentType;
    private String imageContentID;
    private String imageName;

    public ImagePart(String imageBase64, String imageName, String imageContentID ) {
        this.imageBase64 = imageBase64;
//        this.imageContentType = "image/JPEG";
        this.imageContentType = "image/tiff";
        this.imageContentID = imageContentID;
        this.imageName = imageName;
    }

    /**
     * @return the imageBase64
     */
    public String getImageBase64() {
        return imageBase64;
    }

    /**
     * @param imageBase64 the imageBase64 to set
     */
    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    /**
     * @return the imageContentType
     */
    public String getImageContentType() {
        return imageContentType;
    }

    /**
     * @param imageContentType the imageContentType to set
     */
    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    /**
     * @return the imageContentID
     */
    public String getImageContentID() {
        return imageContentID;
    }

    /**
     * @param imageContentID the imageContentID to set
     */
    public void setImageContentID(String imageContentID) {
        this.imageContentID = imageContentID;
    }

    /**
     * @return the imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @param imageName the imageName to set
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    
    
}
