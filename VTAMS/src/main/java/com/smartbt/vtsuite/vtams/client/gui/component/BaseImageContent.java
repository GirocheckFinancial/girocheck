package com.smartbt.vtsuite.vtams.client.gui.component;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CanvasItem;

/**
 *
 * @author Alejo
 */


public class BaseImageContent extends DynamicForm{

    private CanvasItem imageContent;
    private Img logo;
    
    public BaseImageContent(String title, int Height, int Width ){
        
        super();

        setWidth100();
        setMargin(10);
        setTitleOrientation(TitleOrientation.TOP);
        
        imageContent = new CanvasItem();
        imageContent.setColSpan(10);
        imageContent.setTitle(title);
        imageContent.setHeight(Height);
        imageContent.setWidth(Width);
        
        logo = new Img();
        logo.setAutoFit(true);
        logo.setSrc("http://www.karenika.com/down_digi/empty_overlay_small.png");
        imageContent.setCanvas(logo);
        
        
        setFields(imageContent);
    }

    /**
     * @return the imageContent
     */
    public CanvasItem getImageContent() {
        return imageContent;
    }

    /**
     * @param imageContent the imageContent to set
     */
    public void setImageContent(CanvasItem imageContent) {
        this.imageContent = imageContent;
    }

    /**
     * @return the logo
     */
    public Img getLogo() {
        return logo;
    }

    /**
     * @param logo the logo to set
     */
    public void setLogo(Img logo) {
        this.logo = logo;
    }


}
