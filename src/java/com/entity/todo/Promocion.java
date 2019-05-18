/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity.todo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Connectis
 */
@Entity
@Table(name = "PROMOCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promocion.findAll", query = "SELECT p FROM Promocion p")
    , @NamedQuery(name = "Promocion.findByIDPROMOCION", query = "SELECT p FROM Promocion p WHERE p.iDPROMOCION = :iDPROMOCION")
    , @NamedQuery(name = "Promocion.findByNombre", query = "SELECT p FROM Promocion p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Promocion.findByPrecio", query = "SELECT p FROM Promocion p WHERE p.precio = :precio")})
public class Promocion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "iD_PROMOCION")
    private Integer iDPROMOCION;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 45)
    @Column(name = "PRECIO")
    private String precio;
    @OneToMany(mappedBy = "promocion")
    private Collection<Pedido> pedidoCollection;
    @JoinColumn(name = "PRODUCTO", referencedColumnName = "ID_PRODUCTO")
    @ManyToOne
    private Producto producto;

    public Promocion() {
    }

    public Promocion(Integer iDPROMOCION) {
        this.iDPROMOCION = iDPROMOCION;
    }

    public Integer getIDPROMOCION() {
        return iDPROMOCION;
    }

    public void setIDPROMOCION(Integer iDPROMOCION) {
        this.iDPROMOCION = iDPROMOCION;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPROMOCION != null ? iDPROMOCION.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promocion)) {
            return false;
        }
        Promocion other = (Promocion) object;
        if ((this.iDPROMOCION == null && other.iDPROMOCION != null) || (this.iDPROMOCION != null && !this.iDPROMOCION.equals(other.iDPROMOCION))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.todo.Promocion[ iDPROMOCION=" + iDPROMOCION + " ]";
    }
    
}
