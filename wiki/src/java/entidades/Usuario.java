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
import javax.persistence.Id;
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
 * @author SiperProg
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsId", query = "SELECT u FROM Usuario u WHERE u.usId = :usId"),
    @NamedQuery(name = "Usuario.findByUsApellidos", query = "SELECT u FROM Usuario u WHERE u.usApellidos = :usApellidos"),
    @NamedQuery(name = "Usuario.findByUsNombre", query = "SELECT u FROM Usuario u WHERE u.usNombre = :usNombre"),
    @NamedQuery(name = "Usuario.findByUsTipo", query = "SELECT u FROM Usuario u WHERE u.usTipo = :usTipo"),
    @NamedQuery(name = "Usuario.findByUsClave", query = "SELECT u FROM Usuario u WHERE u.usClave = :usClave")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "US_ID")
    private String usId;
    @Size(max = 70)
    @Column(name = "US_APELLIDOS")
    private String usApellidos;
    @Size(max = 70)
    @Column(name = "US_NOMBRE")
    private String usNombre;
    @Size(max = 1)
    @Column(name = "US_TIPO")
    private String usTipo;
    @Size(max = 30)
    @Column(name = "US_CLAVE")
    private String usClave;
    @OneToMany(mappedBy = "usId")
    private List<Contenido> contenidoList;
    @OneToMany(mappedBy = "usId")
    private List<Discusion> discusionList;

    public Usuario() {
    }

    public Usuario(String usId) {
        this.usId = usId;
    }

    public String getUsId() {
        return usId;
    }

    public void setUsId(String usId) {
        this.usId = usId;
    }

    public String getUsApellidos() {
        return usApellidos;
    }

    public void setUsApellidos(String usApellidos) {
        this.usApellidos = usApellidos;
    }

    public String getUsNombre() {
        return usNombre;
    }

    public void setUsNombre(String usNombre) {
        this.usNombre = usNombre;
    }

    public String getUsTipo() {
        return usTipo;
    }

    public void setUsTipo(String usTipo) {
        this.usTipo = usTipo;
    }

    public String getUsClave() {
        return usClave;
    }

    public void setUsClave(String usClave) {
        this.usClave = usClave;
    }

    @XmlTransient
    public List<Contenido> getContenidoList() {
        return contenidoList;
    }

    public void setContenidoList(List<Contenido> contenidoList) {
        this.contenidoList = contenidoList;
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
        hash += (usId != null ? usId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usId == null && other.usId != null) || (this.usId != null && !this.usId.equals(other.usId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuario[ usId=" + usId + " ]";
    }
    
}
