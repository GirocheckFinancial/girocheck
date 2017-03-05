/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.persistence.manager;

import com.alodiga.persistence.domain.Destinatario;
import com.alodiga.persistence.dto.BTransferencia;
import com.alodiga.persistence.dto.Transferencia;
import com.alodiga.util.email.GoogleMail;
import com.alodiga.util.response.ReporteWebResponseDataList;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrodriguez
 */
@Service
@Transactional
public class ReporteManager {
    
    @Autowired
    private IntegrationManager integrationManager;
      
    
    public ReporteWebResponseDataList reporte( BTransferencia request)  throws MessagingException{
        List<Transferencia> list = integrationManager.buscar( request).getList();
         
        Double sumaDineroEntregado = 0D;
        Double sumaTotalPagar = 0D;
        Double sumaComision = 0D;
        
        for (Transferencia t : list) {
            if(t.getDineroEntregado() != null){
                sumaDineroEntregado += t.getDineroEntregado();
            }
            if(t.getTotalPagar() != null){
                sumaTotalPagar += t.getTotalPagar();
            }
            if(t.getComision() != null){
                sumaComision +=  t.getComision();
            }
        }
        
        return new ReporteWebResponseDataList( list, list.size(),   sumaDineroEntregado,   sumaTotalPagar, sumaComision); 
    }
    
    public void emailReporte(String email, BTransferencia request) throws MessagingException{
        List<Transferencia> list = integrationManager.buscar( request).getList();
        
        GoogleMail.sendReportEmail(  email, request.getFechaDel(), request.getFechaAl(), list);
    }
    
}
