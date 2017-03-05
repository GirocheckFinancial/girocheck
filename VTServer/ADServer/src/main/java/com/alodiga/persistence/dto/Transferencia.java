/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.persistence.dto;

import java.io.StringReader;
import java.io.StringWriter;
import javax.rmi.CORBA.Tie;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * <TRANSFERENCIA>
 * <COD_CORRESPONSAL>HUE-51</COD_CORRESPONSAL>
 * <CUENTA_BANCO></CUENTA_BANCO>
 * <COD_CIUDAD_REMITE>HUE-1142</COD_CIUDAD_REMITE>
 * <COD_CIUDAD_DESTINATARIO>HUE-932</COD_CIUDAD_DESTINATARIO>
 * <TIPOVENTA>TR01</TIPOVENTA>
 * <DIR_REMITE>TEST</DIR_REMITE>
 * <DIR_DESTINATARIO>GUATEMALA</DIR_DESTINATARIO>
 * <FECHA>08/12/2016</FECHA>
 * <COMENTARIO></COMENTARIO>
 * <TIPO_CAMBIO>7.83</TIPO_CAMBIO>
 * <TOTAL_PAGAR>19</TOTAL_PAGAR>
 * <AGENCIA_ORIGEN>MIA-1</AGENCIA_ORIGEN>
 * <BI_CREA>MIA-2</BI_CREA>
 * <DINERO_ENTREGADO>783</DINERO_ENTREGADO>
 * <MONTO_ENTREGAR>100</MONTO_ENTREGAR>
 * <NOM_REMITE>PABLO ESCOBAR GAVIRIA</NOM_REMITE>
 * <NOM_DESTINATARIO>TEST2</NOM_DESTINATARIO>
 * <TEL_REMITE>123-4</TEL_REMITE>
 * <TEL_DESTINATARIO></TEL_DESTINATARIO>
 * <ZIP_REMITE></ZIP_REMITE>
 * <COD_ESTADO_REMITE>HUE-22</COD_ESTADO_REMITE>
 * <NOMBRE_ESTADO_REMITE>FLORIDA</NOMBRE_ESTADO_REMITE>
 * <COD_ESTADO_DESTINATARIO>HUE-3</COD_ESTADO_DESTINATARIO>
 * <NOMBRE_ESTADO_DESTINATARIO>GUATEMALA</NOMBRE_ESTADO_DESTINATARIO>
 * <NOMBRE_PAIS_REMITE>USA</NOMBRE_PAIS_REMITE>
 * <COD_PAIS_DESTINATARIO>GT</COD_PAIS_DESTINATARIO>
 * <NOMBRE_PAIS_DESTINATARIO>GUATEMALA</NOMBRE_PAIS_DESTINATARIO>
 * <ESTATUS>1</ESTATUS>
 * <CLAVE_PAGO>70456373</CLAVE_PAGO>
 * </TRANSFERENCIA>
 *
 *
 * @author rrodriguez
 */
@XmlRootElement(name = "TRANSFERENCIA")
public class Transferencia {

    private String codEnvio;
    private String codCorresponsal;
    private String codCiudadRemite;
    private String codCiudadDestinatario;
    private String tipoVenta;
    private String dirRemite;
    private String dirDestinatario;
    private String fecha;
    private String comentario;
    private Double tipoCambio;
    private Double totalPagar;
    private String agenciaOrigen;
    private String biCrea;
    private Double dineroEntregado;
    private Double montoEntregar;
    private String nomRemite;
    private String nomDestinatario;
    private String telRemite;
    private String telDestinatario;
    private String zipRemite;
    private String codEstadoRemite;
    private String codEstadoDestinatario;
    private String codPaisDestinatario;
    private String dirPostalRemite;
    private String fechaNacRemite;
    private String fechaNacDestinatario;
    private String nomCiudadRemite;
    private String nomCiudadDestinatario;
    private String estatus;
    private String clavePago;
    

    private Double comision;

    private Double eRemite;
    private Double eDestino;

    private String respuesta;

    private String numeroCuenta;

    //nuevos campos
    private String nombreEstadoRemite;
    private String nombreEstadoDestinatario;
    private String nombrePaisRemite;
    private String nombrePaisDestinatario;
    private String incluyeComision;
    private String tarifa;
    
    private String moneda;//
    private String nombreAgencia;//
    private String direccionAgencia;//
    private String telefonoAgencia;//
    private String puntoPago; //
    private String formaPago;
    private String comisionAD;
     

    public Transferencia() {
    }

    public Transferencia(String codEnvio) {
        this.codEnvio = codEnvio;
    }

    public static Transferencia getFromXML(String xml) {
        if (xml == null || xml.isEmpty()) {
            return new Transferencia();
        }
        return (Transferencia) JAXB.unmarshal(new StringReader(xml), Transferencia.class);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String toXML() {
        StringWriter sw = new StringWriter();
        JAXB.marshal(this, sw);
        return sw.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "").trim();
    }

    public String getCodEnvio() {
        return codEnvio;
    }

    @XmlElement(name = "COD_ENVIO")
    public void setCodEnvio(String codEnvio) {
        this.codEnvio = codEnvio;
    }

    /**
     * @return the codCorresponsal
     */
    public String getCodCorresponsal() {
        return codCorresponsal;
    }

    /**
     * @param codCorresponsal the codCorresponsal to set
     */
    @XmlElement(name = "COD_CORRESPONSAL")
    public void setCodCorresponsal(String codCorresponsal) {
        this.codCorresponsal = codCorresponsal;
    }

    /**
     * @return the codCiudadDestinatario
     */
    public String getCodCiudadDestinatario() {
        return codCiudadDestinatario;
    }

    public String getCodCiudadRemite() {
        return codCiudadRemite;
    }

    @XmlElement(name = "COD_CIUDAD_REMITE")
    public void setCodCiudadRemite(String codCiudadRemite) {
        this.codCiudadRemite = codCiudadRemite;
    }

    /**
     * @param codCiudadDestinatario the codCiudadDestinatario to set
     */
    @XmlElement(name = "COD_CIUDAD_DESTINATARIO")
    public void setCodCiudadDestinatario(String codCiudadDestinatario) {
        this.codCiudadDestinatario = codCiudadDestinatario;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    @XmlElement(name = "TIPOVENTA")
    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public String getDirRemite() {
        return dirRemite;
    }

    @XmlElement(name = "DIR_REMITE")
    public void setDirRemite(String dirRemite) {
        this.dirRemite = dirRemite;
    }

    /**
     * @return the dirDestinatario
     */
    public String getDirDestinatario() {
        return dirDestinatario;
    }

    /**
     * @param dirDestinatario the dirDestinatario to set
     */
    @XmlElement(name = "DIR_DESTINATARIO")
    public void setDirDestinatario(String dirDestinatario) {
        this.dirDestinatario = dirDestinatario;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    @XmlElement(name = "FECHA")
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    @XmlElement(name = "COMENTARIO")
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the tipoCambio
     */
    public Double getTipoCambio() {
        return tipoCambio;
    }

    /**
     * @param tipoCambio the tipoCambio to set
     */
    @XmlElement(name = "TIPO_CAMBIO")
    public void setTipoCambio(Double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    @XmlElement(name = "TOTAL_PAGAR")
    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    /**
     * @return the agenciaOrigen
     */
    public String getAgenciaOrigen() {
        return agenciaOrigen;
    }

    /**
     * @param agenciaOrigen the agenciaOrigen to set
     */
    @XmlElement(name = "AGENCIA_ORIGEN")
    public void setAgenciaOrigen(String agenciaOrigen) {
        this.agenciaOrigen = agenciaOrigen;
    }

    /**
     * @return the biCrea
     */
    public String getBiCrea() {
        return biCrea;
    }

    /**
     * @param biCrea the biCrea to set
     */
    @XmlElement(name = "BI_CREA")
    public void setBiCrea(String biCrea) {
        this.biCrea = biCrea;
    }

    public Double getDineroEntregado() {
        return dineroEntregado;
    }

    @XmlElement(name = "DINERO_ENTREGADO")
    public void setDineroEntregado(Double dineroEntregado) {
        this.dineroEntregado = dineroEntregado;
    }

    public Double getMontoEntregar() {
        return montoEntregar;
    }

    @XmlElement(name = "MONTO_ENTREGAR")
    public void setMontoEntregar(Double montoEntregar) {
        this.montoEntregar = montoEntregar;
    }

    /**
     * @return the nomRemite
     */
    public String getNomRemite() {
        return nomRemite;
    }

    /**
     * @param nomRemite the nomRemite to set
     */
    @XmlElement(name = "NOM_REMITE")
    public void setNomRemite(String nomRemite) {
        this.nomRemite = nomRemite;
    }

    /**
     * @return the nomDestinatario
     */
    public String getNomDestinatario() {
        return nomDestinatario;
    }

    /**
     * @param nomDestinatario the nomDestinatario to set
     */
    @XmlElement(name = "NOM_DESTINATARIO")
    public void setNomDestinatario(String nomDestinatario) {
        this.nomDestinatario = nomDestinatario;
    }

    /**
     * @return the telRemite
     */
    public String getTelRemite() {
        return telRemite;
    }

    /**
     * @param telRemite the telRemite to set
     */
    @XmlElement(name = "TEL_REMITE")
    public void setTelRemite(String telRemite) {
        this.telRemite = telRemite;
    }

    /**
     * @return the telDestinatario
     */
    public String getTelDestinatario() {
        return telDestinatario;
    }

    /**
     * @param telDestinatario the telDestinatario to set
     */
    @XmlElement(name = "TEL_DESTINATARIO")
    public void setTelDestinatario(String telDestinatario) {
        this.telDestinatario = telDestinatario;
    }

    /**
     * @return the zipRemite
     */
    public String getZipRemite() {
        return zipRemite;
    }

    /**
     * @param zipRemite the zipRemite to set
     */
    @XmlElement(name = "ZIP_REMITE")
    public void setZipRemite(String zipRemite) {
        this.zipRemite = zipRemite;
    }

    /**
     * @return the codEstadoRemite
     */
    public String getCodEstadoRemite() {
        return codEstadoRemite;
    }

    /**
     * @param codEstadoRemite the codEstadoRemite to set
     */
    @XmlElement(name = "COD_ESTADO_REMITE")
    public void setCodEstadoRemite(String codEstadoRemite) {
        this.codEstadoRemite = codEstadoRemite;
    }

    /**
     * @return the codEstadoDestinatario
     */
    public String getCodEstadoDestinatario() {
        return codEstadoDestinatario;
    }

    /**
     * @param codEstadoDestinatario the codEstadoDestinatario to set
     */
    @XmlElement(name = "COD_ESTADO_DESTINATARIO")
    public void setCodEstadoDestinatario(String codEstadoDestinatario) {
        this.codEstadoDestinatario = codEstadoDestinatario;
    }

    public String getCodPaisDestinatario() {
        return codPaisDestinatario;
    }

    @XmlElement(name = "COD_PAIS_DESTINATARIO")
    public void setCodPaisDestinatario(String codPaisDestinatario) {
        this.codPaisDestinatario = codPaisDestinatario;
    }

    public String getDirPostalRemite() {
        return dirPostalRemite;
    }

    @XmlElement(name = "DIR_POSTAL_REMITE")
    public void setDirPostalRemite(String dirPostalRemite) {
        this.dirPostalRemite = dirPostalRemite;
    }

    public String getFechaNacRemite() {
        return fechaNacRemite;
    }

    @XmlElement(name = "FECHA_NAC_REMITE")
    public void setFechaNacRemite(String fechaNacRemite) {
        this.fechaNacRemite = fechaNacRemite;
    }

    public String getFechaNacDestinatario() {
        return fechaNacDestinatario;
    }

    @XmlElement(name = "FECHA_NAC_DESTINATARIO")
    public void setFechaNacDestinatario(String fechaNacDestinatario) {
        this.fechaNacDestinatario = fechaNacDestinatario;
    }

    public String getNomCiudadRemite() {
        return nomCiudadRemite;
    }

    @XmlElement(name = "NOM_CIUDAD_REMITE")
    public void setNomCiudadRemite(String nomCiudadRemite) {
        this.nomCiudadRemite = nomCiudadRemite;
    }

    public String getNomCiudadDestinatario() {
        return nomCiudadDestinatario;
    }

    @XmlElement(name = "NOM_CIUDAD_DESTINATARIO")
    public void setNomCiudadDestinatario(String nomCiudadDestinatario) {
        this.nomCiudadDestinatario = nomCiudadDestinatario;
    }

    public String getEstatus() {
        return estatus;
    }

    @XmlElement(name = "ESTATUS")
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getClavePago() {
        return clavePago;
    }

    @XmlElement(name = "CLAVE_PAGO")
    public void setClavePago(String clavePago) {
        this.clavePago = clavePago;
    }

    /**
     * @return the eRemite
     */
    public Double geteRemite() {
        return eRemite;
    }

    /**
     * @param eRemite the eRemite to set
     */
    @XmlElement(name = "EREMITE")
    public void seteRemite(Double eRemite) {
        this.eRemite = eRemite;
    }

    public Double geteDestino() {
        return eDestino;
    }

    @XmlElement(name = "RDESTINO")
    public void seteDestino(Double eDestino) {
        this.eDestino = eDestino;
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
    @XmlElement(name = "RESPUESTA")
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    @XmlElement(name = "CUENTA_BANCO")
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * @return the nombreEstadoRemite
     */
    public String getNombreEstadoRemite() {
        return nombreEstadoRemite;
    }

    /**
     * @param nombreEstadoRemite the nombreEstadoRemite to set
     */
    @XmlElement(name = "NOMBRE_ESTADO_REMITE")
    public void setNombreEstadoRemite(String nombreEstadoRemite) {
        this.nombreEstadoRemite = nombreEstadoRemite;
    }

    /**
     * @return the nombreEstadoDestinatario
     */
    public String getNombreEstadoDestinatario() {
        return nombreEstadoDestinatario;
    }

    /**
     * @param nombreEstadoDestinatario the nombreEstadoDestinatario to set
     */
    @XmlElement(name = "NOMBRE_ESTADO_DESTINATARIO")
    public void setNombreEstadoDestinatario(String nombreEstadoDestinatario) {
        this.nombreEstadoDestinatario = nombreEstadoDestinatario;
    }

    /**
     * @return the nombrePaisRemite
     */
    public String getNombrePaisRemite() {
        return nombrePaisRemite;
    }

    /**
     * @param nombrePaisRemite the nombrePaisRemite to set
     */
    @XmlElement(name = "NOMBRE_PAIS_REMITE")
    public void setNombrePaisRemite(String nombrePaisRemite) {
        this.nombrePaisRemite = nombrePaisRemite;
    }

    /**
     * @return the nombrePaisDestinatario
     */
    public String getNombrePaisDestinatario() {
        return nombrePaisDestinatario;
    }

    /**
     * @param nombrePaisDestinatario the nombrePaisDestinatario to set
     */
    @XmlElement(name = "NOMBRE_PAIS_DESTINATARIO")
    public void setNombrePaisDestinatario(String nombrePaisDestinatario) {
        this.nombrePaisDestinatario = nombrePaisDestinatario;
    }

    /**
     * @return the comision
     */
    public Double getComision() {
        return comision;
    }

    @XmlElement(name = "COMISION")
    public void setComision(Double comision) {
        this.comision = comision;
    }

    /**
     * @return the formaPago
     */
    public String getFormaPago() {
        return formaPago;
    }

    @XmlElement(name = "FORMA_PAGO")
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * @return the incluyeComision
     */
    public String getIncluyeComision() {
        return incluyeComision;
    }

    /**
     * @param incluyeComision the incluyeComision to set
     */
    @XmlElement(name = "INCLUYE_COMISION")
    public void setIncluyeComision(String incluyeComision) {
        this.incluyeComision = incluyeComision;
    }

    /**
     * @return the tarifa
     */
    public String getTarifa() {
        return tarifa;
    }

    /**
     * @param tarifa the tarifa to set
     */
    @XmlElement(name = "TARIFA")
    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    /**
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    @XmlElement(name = "MONEDA_PAGO")
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the nombreAgencia
     */
    public String getNombreAgencia() {
        return nombreAgencia;
    }

    /**NOMBRE_AGENCIA
     * @param nombreAgencia the nombreAgencia to set
     */
    @XmlElement(name = "NOMBRE_AGENCIA")
    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    /**
     * @return the direccionAgencia
     */
    public String getDireccionAgencia() {
        return direccionAgencia;
    }

    /**
     * @param direccionAgencia the direccionAgencia to set
     */
    @XmlElement(name = "DIRECCION_AGENCIA")
    public void setDireccionAgencia(String direccionAgencia) {
        this.direccionAgencia = direccionAgencia;
    }

    /**
     * @return the telefonoAgencia
     */
    public String getTelefonoAgencia() {
        return telefonoAgencia;
    }

    /**
     * @param telefonoAgencia the telefonoAgencia to set
     */
    @XmlElement(name = "TELEFONO_AGENCIA")
    public void setTelefonoAgencia(String telefonoAgencia) {
        this.telefonoAgencia = telefonoAgencia;
    }

    /**
     * @return the puntoPago
     */
    public String getPuntoPago() {
        return puntoPago;
    }

    /**PUNTO_PAGO
     * @param puntoPago the puntoPago to set
     */
    @XmlElement(name = "PUNTO_PAGO")
    public void setPuntoPago(String puntoPago) {
        this.puntoPago = puntoPago;
    }

    /**
     * @return the comisionAD
     */
    public String getComisionAD() {
        return comisionAD;
    }

     @XmlElement(name = "COMISIONAD")
    public void setComisionAD(String comisionAD) {
        this.comisionAD = comisionAD;
    }
 

}
