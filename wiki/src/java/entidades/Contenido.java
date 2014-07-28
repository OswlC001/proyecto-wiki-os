/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SiperProg
 */
@Entity
@Table(name = "contenido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contenido.findAll", query = "SELECT c FROM Contenido c"),
    @NamedQuery(name = "Contenido.findByConCodigo", query = "SELECT c FROM Contenido c WHERE c.conCodigo = :conCodigo"),
    @NamedQuery(name = "Contenido.findByConFecha", query = "SELECT c FROM Contenido c WHERE c.conFecha = :conFecha")})
public class Contenido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CON_CODIGO")
    private Integer conCodigo;
    @Lob
    @Size(max = 65535)
    @Column(name = "CON_CONTENIDO")
    private String conContenido;
    @Column(name = "CON_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date conFecha;
    @JoinColumn(name = "TIT_CODIGO", referencedColumnName = "TIT_CODIGO")
    @ManyToOne
    private Titulo titCodigo;
    @JoinColumn(name = "US_ID", referencedColumnName = "US_ID")
    @ManyToOne
    private Usuario usId;
    @OneToMany(mappedBy = "conCodigo")
    private List<Discusion> discusionList;

    public Contenido() {
    }

    public Contenido(Integer conCodigo) {
        this.conCodigo = conCodigo;
    }

    public Integer getConCodigo() {
        return conCodigo;
    }

    public void setConCodigo(Integer conCodigo) {
        this.conCodigo = conCodigo;
    }

    public String getConContenido() {
        return conContenido;
    }

    public void setConContenido(String conContenido) {
        this.conContenido = conContenido;
    }

    public Date getConFecha() {
        return conFecha;
    }

    public void setConFecha(Date conFecha) {
        this.conFecha = conFecha;
    }

    public Titulo getTitCodigo() {
        return titCodigo;
    }

    public void setTitCodigo(Titulo titCodigo) {
        this.titCodigo = titCodigo;
    }

    public Usuario getUsId() {
        return usId;
    }

    public void setUsId(Usuario usId) {
        this.usId = usId;
    }

    @XmlTransient
    public List<Discusion> getDiscusionList() {
        return discusionList;
    }

    public void setDiscusionList(List<Discusion> discusionList) {
        this.discusionList = discusionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conCodigo != null ? conCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contenido)) {
            return false;
        }
        Contenido other = (Contenido) object;
        if ((this.conCodigo == null && other.conCodigo != null) || (this.conCodigo != null && !this.conCodigo.equals(other.conCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Contenido[ conCodigo=" + conCodigo + " ]";
    }
    
}
