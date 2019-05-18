/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity.todo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Connectis
 */
@Entity
@Table(name = "FORMULARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formulario.findAll", query = "SELECT f FROM Formulario f")
    , @NamedQuery(name = "Formulario.findByIdForm", query = "SELECT f FROM Formulario f WHERE f.idForm = :idForm")
    , @NamedQuery(name = "Formulario.findByNombre", query = "SELECT f FROM Formulario f WHERE f.nombre = :nombre")
    , @NamedQuery(name = "Formulario.findByEmail", query = "SELECT f FROM Formulario f WHERE f.email = :email")
    , @NamedQuery(name = "Formulario.findByTelefono", query = "SELECT f FROM Formulario f WHERE f.telefono = :telefono")
    , @NamedQuery(name = "Formulario.findByTipoFormulario", query = "SELECT f FROM Formulario f WHERE f.tipoFormulario = :tipoFormulario")
    , @NamedQuery(name = "Formulario.findByAsunto", query = "SELECT f FROM Formulario f WHERE f.asunto = :asunto")
    , @NamedQuery(name = "Formulario.findByMensaje", query = "SELECT f FROM Formulario f WHERE f.mensaje = :mensaje")})
public class Formulario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_FORM")
    private Integer idForm;
    @Size(max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TELEFONO")
    private Integer telefono;
    @Size(max = 30)
    @Column(name = "TIPO_FORMULARIO")
    private String tipoFormulario;
    @Size(max = 20)
    @Column(name = "ASUNTO")
    private String asunto;
    @Size(max = 100)
    @Column(name = "MENSAJE")
    private String mensaje;

    public Formulario() {
    }

    public Formulario(Integer idForm) {
        this.idForm = idForm;
    }

    public Integer getIdForm() {
        return idForm;
    }

    public void setIdForm(Integer idForm) {
        this.idForm = idForm;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(String tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idForm != null ? idForm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formulario)) {
            return false;
        }
        Formulario other = (Formulario) object;
        if ((this.idForm == null && other.idForm != null) || (this.idForm != null && !this.idForm.equals(other.idForm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.todo.Formulario[ idForm=" + idForm + " ]";
    }
    
}
