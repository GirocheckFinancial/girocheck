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
import com.alodiga.persistence.dto.BTransferencia;
import com.alodiga.persistence.dto.CotizarRequest;
import com.alodiga.persistence.dto.CotizarResult;
import com.alodiga.persistence.dto.Estado;
import com.alodiga.persistence.dto.EstadoList;
import com.alodiga.persistence.dto.NTransferencia;
import com.alodiga.persistence.dto.Pais;
import com.alodiga.persistence.dto.PaisList;
import com.alodiga.persistence.dto.RTransferencia;
import com.alodiga.persistence.dto.Transferencia;
import com.alodiga.persistence.dto.TransferenciaList;
import com.alodiga.util.response.WebResponseDataList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rrodriguez
 */
@Service
public class IntegrationManager {

//    private final String URL = "http://52.27.61.96/receiverXML1.asp";
//    private final String URL = "http://54.88.151.216/receiverXML1.asp";
    private final String URL = "http://54.226.63.11/receiverXML1.asp";
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired RemitenteDAO remitenteDAO;
    
    @Autowired DestinatarioDAO destinatarioDAO;

    private String callWS(String inputXML, boolean wrap) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(inputXML, headers);

        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

        String xml = response.getBody();

//        System.out.println("parser " + xml);
        
        if (wrap){
            xml = "<TRANSFERENCIA>" + xml + "</TRANSFERENCIA>";
        }
        return xml;
    }
 
    public static void main(String[] args) throws JAXBException {
        RTransferencia t = new RTransferencia();
        t.setCodEnvio("001T271");
        System.out.println(t.toXML()); 
         
    }
    
    public Transferencia buscarPorCodigo(RTransferencia t){
        System.out.println(" IntegrationManager.buscarPorCodigo :: REQUESTXML = " + t.toXML()); 
        
        String responseXML = callWS(t.toXML(), false);
        
        System.out.println(" IntegrationManager.buscarPorCodigo :: responseXML = " + responseXML);
        
        return Transferencia.getFromXML(responseXML); 
    }

    public List<Pais> listarPaises() { 
        String request = "<BPAISES></BPAISES>";

        String response = callWS(request, false);

        if (response == null) {
            return null;
        }

        PaisList paisList = new PaisList(response);
         
        return paisList.getList();
    }

    public List<Estado> listarEstados(String codPais) { 
        String request = "<BESTADOS><PAIS>" + codPais + "</PAIS></BESTADOS>";

        String response = callWS(request, false);

        System.out.println("response = " + response);
        
        if (response == null) {
            return null;
        }

        EstadoList estadoList = new EstadoList(response);
        return estadoList.getList();
    }
 
 
    
    public Transferencia doTransaction(NTransferencia request) { 
        request.setTipoVenta("TR01");
        request.setComentario("");
        request.setBiCrea("MIA-2");
        request.setDirPostalRemite("");
        request.setFechaNacDestinatario("");
         
        String xml = request.toXML();
        System.out.println("req =  " + xml);
        xml = callWS(xml, true);
        System.out.println("RESPONSE =  " + xml);
//        Transferencia transferencia = (Transferencia) JAXB.unmarshal(new StringReader(xml), Transferencia.class);
        Transferencia transferencia = Transferencia.getFromXML(xml);

        persistRemitenteAndDestinatario(request);
        
        return transferencia;
    }
    
    public CotizarResult cotizar(CotizarRequest request) {   
        String xml = request.toXML();
        System.out.println("req =  " + xml);
        xml = callWS(xml, false);
        System.out.println("RESPONSE =  " + xml);
//        Transferencia transferencia = (Transferencia) JAXB.unmarshal(new StringReader(xml), Transferencia.class);
        CotizarResult result = CotizarResult.getFromXML(xml);
 
        return result;
    }
 
    public WebResponseDataList buscar(BTransferencia request){ 
        
        if(request.getPage() == 0){
            request.setPage(1);
            request.setLimit(500);
        }
        
        String xml = request.toXML();
        System.out.println("request = " + xml);
        xml = callWS(xml, false);
        System.out.println("response = " + xml);
        TransferenciaList transferenciaList = new TransferenciaList(xml);
        List<Transferencia> list = transferenciaList.getList();
 
        return new WebResponseDataList(list, list.size());
    }
    
    private void persistRemitenteAndDestinatario(Transferencia t){
        Remitente remitente = remitenteDAO.getRemitenteByPhone(t.getTelRemite());
        boolean isNew;
        
        if(isNew = remitente == null){
            remitente = new Remitente(t); 
        }else{
            remitente.update(t);
        }
        
        remitenteDAO.saveOrUpdate(remitente);
        
        Destinatario destinatario = null;
        
        if(!isNew){
            List<Destinatario> destinatarios = destinatarioDAO.getDestinatariosByRemitente(remitente.getId());
        
            for (Destinatario d : destinatarios) {
                if(d.getNombre().equalsIgnoreCase( t.getNomDestinatario())){
                    destinatario = d.update(t);
                    break;
                }
            }
        }
        
        if(destinatario == null){
            destinatario = new Destinatario(t);
        }
        
        destinatario.setRemitente(remitente);
        
        destinatarioDAO.saveOrUpdate(destinatario);
        
    }
     

    
}
