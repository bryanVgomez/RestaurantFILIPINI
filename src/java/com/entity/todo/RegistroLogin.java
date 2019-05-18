/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity.todo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jbenitez
 */
@Entity
@Table(name = "REGISTRO_LOGIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroLogin.findAll", query = "SELECT r FROM RegistroLogin r")
    , @NamedQuery(name = "RegistroLogin.findByUsername", query = "SELECT r FROM RegistroLogin r WHERE r.username = :username")
    , @NamedQuery(name = "RegistroLogin.findByPass", query = "SELECT r FROM RegistroLogin r WHERE r.pass = :pass")
    , @NamedQuery(name = "RegistroLogin.findByUltimaConexion", query = "SELECT r FROM RegistroLogin r WHERE r.ultimaConexion = :ultimaConexion")})
public class RegistroLogin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 20)
    @Column(name = "PASS")
    private String pass;
    @Column(name = "ULTIMA_CONEXION")
    @Temporal(TemporalType.DATE)
    private Date ultimaConexion;

    public RegistroLogin() {
    }

    public RegistroLogin(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(Date ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroLogin)) {
            return false;
        }
        RegistroLogin other = (RegistroLogin) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.todo.RegistroLogin[ username=" + username + " ]";
    }
    
}
