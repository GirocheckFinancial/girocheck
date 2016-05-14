package com.smartbt.girocheck.servercommon.utils;


import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.PNGEncodeParam;
import com.sun.media.jai.codec.TIFFDecodeParam;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javaxt.io.Image;

public class ImgConvTiffToPng {
    
    public ImgConvTiffToPng(){};
    
    public byte[] convert(byte[] tiff) throws Exception{

        byte[] out = new byte[0];
        try {
            InputStream inputStream = new ByteArrayInputStream(tiff);

            TIFFDecodeParam param = null;

            ImageDecoder dec = ImageCodec.createImageDecoder("tiff", inputStream, param);
            RenderedImage op = dec.decodeAsRenderedImage(0);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            PNGEncodeParam jpgparam = null;
            ImageEncoder en = ImageCodec.createImageEncoder("png", outputStream, jpgparam);
            en.encode(op);
            outputStream = (ByteArrayOutputStream) en.getOutputStream();
            out = outputStream.toByteArray();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return out;
        
    }   
    
    public byte[] rotate180(byte[] imagee){

        Image image = new Image(imagee);
        image.rotate(180);
        image.rotateCounterClockwise();
        image.rotateClockwise();

        return image.getByteArray();
    }
    
//    private BufferedImage createImageFromBytes(byte[] imageData) {
//        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
//        try {
//            return ImageIO.read(bais);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private byte[] image_byte_data(BufferedImage image) {
//        WritableRaster raster = image.getRaster();
//        DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
//        return buffer.getData();
//    }
    
//    public byte[] read(String imageName) throws IOException {
//
//        File file = new File(imageName+".png"); 
//        byte[] fileContent = Files.readAllBytes(file.toPath());
//        file.delete();
//        
//        return fileContent;
//    }
//
//    public void write(byte[] image, String imageName) throws IOException {
//
//        File frameJPG = new File(imageName+".png");
//
//        FileOutputStream outputStream = new FileOutputStream(frameJPG);
//        outputStream.write(image);
//        outputStream.flush();
//        outputStream.close();
//    }
        
}