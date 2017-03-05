/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.persistence.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rrodriguez
 */
@XmlRootElement(name = "RTRANSFERENCIA")
public class RTransferencia extends Transferencia {

    public RTransferencia() {
    }

    public RTransferencia(String codEnvio) {
        super(codEnvio);
    }

}
