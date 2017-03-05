package com.alodiga.web.mobile;

import com.alodiga.persistence.domain.Remitente;
import com.alodiga.persistence.dto.BTransferencia;
import com.alodiga.persistence.dto.Corresponsal;
import com.alodiga.persistence.dto.CotizarRequest;
import com.alodiga.persistence.dto.CotizarResult;
import com.alodiga.persistence.dto.Estado;
import com.alodiga.persistence.dto.FormaEntrega;
import com.alodiga.persistence.dto.FormaEntregaList;
import com.alodiga.persistence.dto.NTransferencia;
import com.alodiga.persistence.dto.Pais;
import com.alodiga.persistence.dto.RTransferencia;
import com.alodiga.persistence.dto.Transferencia;
import com.alodiga.persistence.manager.IntegrationManager;
import com.alodiga.persistence.manager.OperationManager;
import com.alodiga.persistence.manager.RemitenteManager;
import com.alodiga.persistence.manager.ReporteManager;
import com.alodiga.util.response.ReporteWebResponseDataList;
import com.alodiga.util.response.WebResponse;
import com.alodiga.util.response.WebResponseData;
import com.alodiga.util.response.WebResponseDataList;
import java.util.ArrayList;

import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import com.alodiga.persistence.manager.IntegrationManager;
//import com.alodiga.persistence.manager.RemitenteManager;

/**
 *
 * @author Robe
 */
@RestController
@RequestMapping(value = "/alodiga/mobile", method = RequestMethod.GET)
public class MobileController {

    @Autowired
    private IntegrationManager integrationManager;

    @Autowired
    private RemitenteManager remitenteManager;

    @Autowired
    private ReporteManager reporteManager;

    @Autowired
    private OperationManager operationManager;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public WebResponseData test() {
        System.out.println("Test()");
        return new WebResponseData("Working!!!");
    }
//MOCK

    @RequestMapping(value = "/config")
    public @ResponseBody
    List<Pais> config() {
        return integrationManager.listarPaises();
    }

    @RequestMapping(value = "/listarPaises")
    public @ResponseBody
    List<Pais> listarPaises() {

        Pais pais = new Pais("GUATEMALA", "GT", "Q");

        List<Corresponsal> corresponsales = new ArrayList<>();
        corresponsales.add(new Corresponsal("Corresponsal 1", "Code1"));
        corresponsales.add(new Corresponsal("Corresponsal 2", "Code2"));

        List<FormaEntrega> formaEntregaList = new ArrayList<>();
        formaEntregaList.add(new FormaEntrega("Entrega 1"));
        formaEntregaList.add(new FormaEntrega("Entrega 2"));

        FormaEntregaList formasEntrega = new FormaEntregaList(formaEntregaList);

        for (Corresponsal corresponsal : corresponsales) {
            corresponsal.setFormasEntrega(formasEntrega);
        }

//        pais.setCorresponsales("");
//        pais.setCorresponsales(new CorresponsalList(corresponsales));
        List list = new ArrayList();

        list.add(pais);
        return list;
    }

//    @RequestMapping(value = "/listarPaises")
//    public @ResponseBody
//    List<Pais> listarPaises() {
//       return integrationManager.listarPaises();
//    }
    @RequestMapping(value = "/listarEstados", method = RequestMethod.POST)
    public @ResponseBody
    List<Estado> listarEstados(@RequestParam("pais") String pais) {
        System.out.println("pais = " + pais);
        
        if(pais != null && pais.equalsIgnoreCase("US")){
            pais = "USA";
        }
        
        return integrationManager.listarEstados(pais);

    }

//    @RequestMapping(value = "/buscar")
//    public @ResponseBody Transferencia  buscar() {
//        return integrationManager.buscarTransferenciaPorCodigo();
//    }
//    @RequestMapping(value = "/montos")
//    public @ResponseBody Transferencia  montos() {
//        return integrationManager.revisarMontos();
//    }
    @RequestMapping(value = "/doTransaction", method = RequestMethod.GET)
    public @ResponseBody
    Transferencia doTransaction(
            @RequestParam("codCorresponsal") String codCorresponsal,
            @RequestParam("codCiudadRemite") String codCiudadRemite,
            @RequestParam("codCiudadDestinatario") String codCiudadDestinatario,
            @RequestParam("dirRemite") String dirRemite,
            @RequestParam("dirDestinatario") String dirDestinatario,
            @RequestParam("fecha") String fecha,
            @RequestParam("tipoCambio") String tipoCambio,
            @RequestParam("totalPagar") String totalPagar,
            @RequestParam("agenciaOrigen") String agenciaOrigen,
            @RequestParam("dineroEntregado") String dineroEntregado,
            @RequestParam("montoEntregar") String montoEntregar, 
            @RequestParam("nomRemite") String nomRemite,
            @RequestParam("nomDestinatario") String nomDestinatario, 
            @RequestParam("telRemite") String telRemite,
            @RequestParam("telDestinatario") String telDestinatario, 
            @RequestParam("zipRemite") String zipRemite,
            @RequestParam("codEstadoRemite") String codEstadoRemite, 
            @RequestParam("codEstadoDestinatario") String codEstadoDestinatario,
            @RequestParam("codPaisDestinatario") String codPaisDestinatario, 
            @RequestParam("fechaNacRemite") String fechaNacRemite,
            @RequestParam("nomCiudadRemite") String nomCiudadRemite, 
            @RequestParam("nomCiudadDestinatario") String nomCiudadDestinatario,
            @RequestParam(value = "numeroCuenta", defaultValue = "") String numeroCuenta,
            @RequestParam(value = "formaPago", defaultValue = "") String formaPago,
            @RequestParam(value = "incluyeComision", defaultValue = "true") Boolean incluyeComision
    ) { 

         agenciaOrigen = "MIA-1";
         
        NTransferencia request = new NTransferencia();

        request.setCodCorresponsal(codCorresponsal);
        request.setCodCiudadRemite(codCiudadRemite);
        request.setCodCiudadDestinatario(codCiudadDestinatario);
        request.setTipoVenta("TR01"); //hardcoded
        request.setDirRemite(dirRemite);
        request.setDirDestinatario(dirDestinatario);
        request.setFecha(fecha);//TODO enviar fecha de hoy
        request.setComentario("");//hardcoded
        request.setTipoCambio(parseAmount(tipoCambio));
        request.setTotalPagar(parseAmount(totalPagar));
        request.setAgenciaOrigen(agenciaOrigen); //del login
        request.setBiCrea("MIA-2"); //hardcoded
        request.setDineroEntregado(parseAmount(dineroEntregado));//dinero convertido a la moneda de ese pais.
        request.setMontoEntregar(parseAmount(montoEntregar)); //monto en dolares
        request.setNomRemite(nomRemite);
        request.setNomDestinatario(nomDestinatario);
        request.setTelRemite(telRemite);
        request.setTelDestinatario(telDestinatario);
        request.setZipRemite(zipRemite);
        request.setCodEstadoRemite(codEstadoRemite);
        request.setCodEstadoDestinatario(codEstadoDestinatario);
        request.setCodPaisDestinatario(codPaisDestinatario);
        request.setDirPostalRemite("");//hardcoded
        request.setFechaNacRemite(fechaNacRemite);
        request.setFechaNacDestinatario("");//hardcoded
        request.setNomCiudadRemite(nomCiudadRemite);
        request.setNomCiudadDestinatario(nomCiudadDestinatario);
        request.setNumeroCuenta(numeroCuenta);
        request.setIncluyeComision((incluyeComision + "").toUpperCase());
        
        if (formaPago == null || formaPago.isEmpty()) {
            formaPago = "0";
        }

        request.setFormaPago(formaPago);
        return integrationManager.doTransaction(request);
    }

    @RequestMapping(value = "/remitente", method = RequestMethod.GET/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public @ResponseBody
    Remitente remitente(@RequestParam("telefono") String telefono,
            @RequestParam("agenciaOrigen") String agenciaOrigen) {
         agenciaOrigen = "MIA-1";
        return remitenteManager.getRemitenteByPhone(telefono);
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public @ResponseBody
    WebResponseDataList buscar(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "0") Integer limit,
            @RequestParam("fechaDel") String fechaDel,
            @RequestParam("fechaAl") String fechaAl,
            @RequestParam("nombreR") String nombreR,
            @RequestParam("nombreD") String nombreD,
            @RequestParam("agenciaOrigen") String agenciaOrigen
    ) {
        agenciaOrigen = "MIA-1"; //Provissional
        BTransferencia request = new BTransferencia(fechaDel, fechaAl, nombreR, nombreD, agenciaOrigen, page, limit);
        return integrationManager.buscar(request);
    }

    @RequestMapping(value = "/emailReporte", method = RequestMethod.GET/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public @ResponseBody
    WebResponse emailReporte(
            @RequestParam("email") String email,
            @RequestParam("fechaDel") String fechaDel,
            @RequestParam("fechaAl") String fechaAl,
            @RequestParam("nombreR") String nombreR,
            @RequestParam("nombreD") String nombreD,
            @RequestParam("agenciaOrigen") String agenciaOrigen
    ) throws MessagingException {
        BTransferencia request = new BTransferencia(fechaDel, fechaAl, nombreR, nombreD, agenciaOrigen, 1, 100);

        email = email.replace("%40", "@");
        agenciaOrigen = "MIA-1"; //Provissional
        reporteManager.emailReporte(email, request);
        return new WebResponse();
    }

    @RequestMapping(value = "/reporte", method = RequestMethod.GET/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public @ResponseBody
    ReporteWebResponseDataList reporte(
            @RequestParam("fechaDel") String fechaDel,
            @RequestParam("fechaAl") String fechaAl,
            @RequestParam("nombreR") String nombreR,
            @RequestParam("nombreD") String nombreD,
            @RequestParam("agenciaOrigen") String agenciaOrigen
    ) throws MessagingException {
        agenciaOrigen = "MIA-1"; //Provissional
        BTransferencia request = new BTransferencia(fechaDel, fechaAl, nombreR, nombreD, agenciaOrigen, 1, 100);
        return reporteManager.reporte(request);
    }

    @RequestMapping(value = "/cotizar", method = RequestMethod.POST)
    public @ResponseBody
    CotizarResult cotizar(
            @RequestParam("monto") String monto,
            @RequestParam("agenciaOrigen") String agenciaOrigen,
            @RequestParam("corresponsal") String corresponsal,
            @RequestParam(value = "incluyeComision", defaultValue = "true") Boolean incluyeComision,
            @RequestParam("formaEntrega") String formaEntrega) {
       
        agenciaOrigen = "MIA-1";
        
        return integrationManager.cotizar(new CotizarRequest(monto, agenciaOrigen, corresponsal, formaEntrega, (incluyeComision + "").toUpperCase()));
    }

    @RequestMapping(value = "/buscarPorCodigo", method = RequestMethod.POST)
    public @ResponseBody
    Transferencia buscarPorCodigo(
            @RequestParam("codEnvio") String codEnvio) {
        System.out.println("MobileController.cotizar :: codEnvio = " + codEnvio);
        
        return integrationManager.buscarPorCodigo(new RTransferencia(codEnvio)); 
    }

    @RequestMapping(value = "/listCities", method = RequestMethod.GET)
    public @ResponseBody
    List<String> listCities(String state) {
        return operationManager.listCitiesFromStateCode(state);
    }

    private Double parseAmount(String strAmount){
        if(strAmount != null && strAmount.length() > 0 && (strAmount.startsWith("Q") || strAmount.startsWith("$"))){
            strAmount = strAmount.substring(1);
        }
        return Double.parseDouble(strAmount);
    }
    
}
