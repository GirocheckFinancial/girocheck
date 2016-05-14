/*
 ** File: ImageHelper.java
 **
 ** Date Created: June 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.servercommon.utils.receipt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import org.imgscalr.Scalr;

/**
 *
 * @author Carlos Romero
 */
public class ImageHelper {

    /**
     * Resizes an image
     *
     * @param image The Base64 encoded signature
     * @param imageType THe image type - "png"
     * @param width The maximum width
     * @param height The maximum height
     * @param maintainAspectRatio Always true
     * @return
     * @throws IOException
     */
    public static String resizeImagePNG(String image, String imageType, int width, int height, boolean maintainAspectRatio) throws Exception {
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(image.substring(22))));
        return resizeImagePNG(originalImage, imageType, width, height, maintainAspectRatio);
    }

    public static String resizeImagePNG(BufferedImage originalImage, String imageType, int width, int height, boolean maintainAspectRatio) throws Exception {
        Image resizedImage = Scalr.resize(originalImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, width, height, Scalr.OP_ANTIALIAS);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) resizedImage, "png", baos);

        return "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
    }

    public static String trim(String image) throws IOException {

        BufferedImage img = ImageIO.read(new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(image.substring(22))));

        int width = getTrimmedWidth(img);
        int height = getTrimmedHeight(img);

        BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImg.createGraphics();
        g.drawImage(img, 0, 0, null);
        img = newImg;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);

        return DatatypeConverter.printBase64Binary(baos.toByteArray());
    }

    private static int getTrimmedWidth(BufferedImage img) {
        int height = img.getHeight();
        int width = img.getWidth();
        int trimmedWidth = 0;

        for (int i = 0; i < height; i++) {
            for (int j = width - 1; j >= 0; j--) {
                if (img.getRGB(j, i) != Color.WHITE.getRGB()
                        && j > trimmedWidth) {
                    trimmedWidth = j;
                    break;
                }
            }
        }

        return trimmedWidth;
    }

    private static int getTrimmedHeight(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int trimmedHeight = 0;

        for (int i = 0; i < width; i++) {
            for (int j = height - 1; j >= 0; j--) {
                if (img.getRGB(i, j) != Color.WHITE.getRGB()
                        && j > trimmedHeight) {
                    trimmedHeight = j;
                    break;
                }
            }
        }
        return trimmedHeight;
    }
}
