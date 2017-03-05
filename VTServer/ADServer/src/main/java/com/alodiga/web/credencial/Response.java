/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.web.credencial;

/**
 *
 * @author rrodriguez
 */
public class Response {
    private String respuesta;

    public Response(String respuesta) {
        this.respuesta = respuesta;
    }

    
    /**
     * @return the respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
