/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.web.credencial;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 *
 * @author rrodriguez
 */
@WebService(serviceName = "Credencial")
public class Credencial {

    /**
     * This is a sample web service operation // public String
     * hello(@WebParam(name = "name") String txt) {
     */
    @WebMethod(operationName = "hello")
    @ResponsePayload
    public Response hello(@RequestPayload Person person) {
        return new Response("Hola " + person.getNombre() + " !");
    }
}
