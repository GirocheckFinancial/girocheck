package com.alodiga.persistence.domain;

import com.alodiga.persistence.dto.Transferencia;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "destinatario")
@XmlRootElement
public class Destinatario{
    @Id
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "serial")
    @SequenceGenerator(name = "pk_sequence", sequenceName = "destinatario_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Generated(GenerationTime.INSERT)
    private Long id;

    @Column(name = "nombre") 
    private String nombre;

    @Column(name = "lastName") 
    private String lastName;

    @Column(name = "direccion") 
    private String direccion;

    @Column(name = "municipio") 
    private String municipio;

    @Column(name = "departamento") 
    private String departamento;

    @Column(name = "pais") 
    private String pais;

    @Column(name = "telefono")
    private String telefono; 
     
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "remitente")
    private Remitente remitente;

    public Destinatario() {
    }

    
    
    public Destinatario(Transferencia t) { 
        this.nombre = t.getNomDestinatario();
        this.direccion = t.getDirDestinatario();
        this.municipio = t.getNomCiudadDestinatario();
        this.departamento = t.getCodEstadoDestinatario();
        this.pais = t.getCodPaisDestinatario();
        this.telefono = t.getTelDestinatario(); 
    }
    
    public Destinatario update(Transferencia t) { 
        this.nombre = t.getNomDestinatario();
        this.direccion = t.getDirDestinatario();
        this.municipio = t.getNomCiudadDestinatario();
        this.departamento = t.getCodCiudadDestinatario();
        this.pais = t.getCodPaisDestinatario();
        this.telefono = t.getTelDestinatario(); 
        return this;
    }

    
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the remitente
     */
    public Remitente getRemitente() {
        return remitente;
    }

    /**
     * @param remitente the remitente to set
     */
    public void setRemitente(Remitente remitente) {
        this.remitente = remitente;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
