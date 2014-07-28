/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SiperProg
 */
@Entity
@Table(name = "discusion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Discusion.findAll", query = "SELECT d FROM Discusion d"),
    @NamedQuery(name = "Discusion.findByDisCodigo", query = "SELECT d FROM Discusion d WHERE d.disCodigo = :disCodigo")})
public class Discusion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DIS_CODIGO")
    private Integer disCodigo;
    @Lob
    @Size(max = 65535)
    @Column(name = "DIS_COMENTARIO")
    private String disComentario;
    @JoinColumn(name = "CON_CODIGO", referencedColumnName = "CON_CODIGO")
    @ManyToOne
    private Contenido conCodigo;
    @JoinColumn(name = "US_ID", referencedColumnName = "US_ID")
    @ManyToOne
    private Usuario usId;

    public Discusion() {
    }

    public Discusion(Integer disCodigo) {
        this.disCodigo = disCodigo;
    }

    public Integer getDisCodigo() {
        return disCodigo;
    }

    public void setDisCodigo(Integer disCodigo) {
        this.disCodigo = disCodigo;
    }

    public String getDisComentario() {
        return disComentario;
    }

    public void setDisComentario(String disComentario) {
        this.disComentario = disComentario;
    }

    public Contenido getConCodigo() {
        return conCodigo;
    }

    public void setConCodigo(Contenido conCodigo) {
        this.conCodigo = conCodigo;
    }

    public Usuario getUsId() {
        return usId;
    }

    public void setUsId(Usuario usId) {
        this.usId = usId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (disCodigo != null ? disCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Discusion)) {
            return false;
        }
        Discusion other = (Discusion) object;
        if ((this.disCodigo == null && other.disCodigo != null) || (this.disCodigo != null && !this.disCodigo.equals(other.disCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Discusion[ disCodigo=" + disCodigo + " ]";
    }
    
}
