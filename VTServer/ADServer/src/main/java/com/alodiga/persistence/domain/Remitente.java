package com.alodiga.persistence.domain;

import com.alodiga.persistence.dto.Transferencia;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "remitente")
@XmlRootElement
public class Remitente  implements Serializable{

    @Id
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "serial")
    @SequenceGenerator(name = "pk_sequence", sequenceName = "remitente_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Generated(GenerationTime.INSERT)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
     
    @Column(name = "direccion")
    private String direccion;
     
    @Column(name = "ciudad")
    private String ciudad;
     
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "zipcode")
    private String zipcode;
    
    @Column(name = "telefono")
    private String telefono; 

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "remitente")
    private List<Destinatario> destinatarios;

    public Remitente() {
    }

    
    
    public Remitente(Transferencia t) { 
        this.name = t.getNomRemite();
        //this.name = t.get
        this.fechaNacimiento = t.getFechaNacRemite();
        this.direccion = t.getDirRemite();
        this.ciudad = t.getNomCiudadRemite();
        this.estado = t.getCodEstadoRemite();
        this.zipcode = t.getZipRemite();
        this.telefono = t.getTelRemite(); 
    }
    
    public void update(Transferencia t) { 
        this.name = t.getNomRemite();
        this.fechaNacimiento = t.getFechaNacRemite();
        this.direccion = t.getDirRemite();
        this.ciudad = t.getNomCiudadRemite();
        this.estado = t.getCodEstadoRemite();
        this.zipcode = t.getZipRemite();
        this.telefono = t.getTelRemite(); 
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the fechaNacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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
     * @return the destinatarios
     */
    public List<Destinatario> getDestinatarios() {
        return destinatarios;
    }

    /**
     * @param destinatarios the destinatarios to set
     */
    public void setDestinatarios(List<Destinatario> destinatarios) {
        this.destinatarios = destinatarios;
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
