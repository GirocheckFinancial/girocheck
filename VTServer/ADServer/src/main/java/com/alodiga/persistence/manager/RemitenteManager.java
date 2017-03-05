/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.persistence.manager;

import com.alodiga.persistence.dao.DestinatarioDAO;
import com.alodiga.persistence.dao.RemitenteDAO;
import com.alodiga.persistence.domain.Destinatario;
import com.alodiga.persistence.domain.Remitente;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrodriguez
 */
@Service
@Transactional
public class RemitenteManager {
    
    @Autowired
    private RemitenteDAO remitenteDAO;
    
    @Autowired
    private DestinatarioDAO destinatarioDAO;

    public Remitente getRemitenteByPhone(String telefono) {
        Remitente remitente = remitenteDAO.getRemitenteByPhone(telefono);
        
        if(remitente != null){
            remitente.setDestinatarios( destinatarioDAO.getDestinatariosByRemitente( remitente.getId()));
        }
        
        return remitente;
    }
    
     public void save(Destinatario destinatario) {
         
    }
    
}
