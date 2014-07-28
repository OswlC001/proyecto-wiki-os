/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SiperProg
 */
@Entity
@Table(name = "titulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Titulo.findAll", query = "SELECT t FROM Titulo t"),
    @NamedQuery(name = "Titulo.findByTitCodigo", query = "SELECT t FROM Titulo t WHERE t.titCodigo = :titCodigo"),
    @NamedQuery(name = "Titulo.findByTitTitulo", query = "SELECT t FROM Titulo t WHERE t.titTitulo = :titTitulo")})
public class Titulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TIT_CODIGO")
    private Integer titCodigo;
    @Size(max = 70)
    @Column(name = "TIT_TITULO")
    private String titTitulo;
    @OneToMany(mappedBy = "titCodigo")
    private List<Contenido> contenidoList;

    public Titulo() {
    }

    public Titulo(Integer titCodigo) {
        this.titCodigo = titCodigo;
    }

    public Integer getTitCodigo() {
        return titCodigo;
    }

    public void setTitCodigo(Integer titCodigo) {
        this.titCodigo = titCodigo;
    }

    public String getTitTitulo() {
        return titTitulo;
    }

    public void setTitTitulo(String titTitulo) {
        this.titTitulo = titTitulo;
    }

    @XmlTransient
    public List<Contenido> getContenidoList() {
        return contenidoList;
    }

    public void setContenidoList(List<Contenido> contenidoList) {
        this.contenidoList = contenidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (titCodigo != null ? titCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Titulo)) {
            return false;
        }
        Titulo other = (Titulo) object;
        if ((this.titCodigo == null && other.titCodigo != null) || (this.titCodigo != null && !this.titCodigo.equals(other.titCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Titulo[ titCodigo=" + titCodigo + " ]";
    }
    
}
