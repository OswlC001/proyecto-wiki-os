package backingBeans;

import entidades.Contenido;
import entidades.Discusion;
import entidades.Titulo;
import entidades.Usuario;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import sessionBeans.ContenidoFacade;
import sessionBeans.DiscusionFacade;
import sessionBeans.UsuarioFacade;

@Named(value = "contenidoController")
@ViewScoped
public class ContenidoController extends AbstractController<Contenido> {

    @EJB
    private DiscusionFacade discusionFacade;

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private ContenidoFacade contenidoFacade;

    @Inject
    private TituloController titCodigoController;
    @Inject
    private UsuarioController usIdController;
    @Inject
    private DiscusionController discusionListController;

    String contenido;
    String usCodigo;
    String titulo;
    Contenido cont;
    Integer titCodigo;
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    Map params = externalContext.getRequestParameterMap();
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    public ContenidoController() {
        // Inform the Abstract parent controller of the concrete Contenido?cap_first Entity
        super(Contenido.class);
    }

    public String getTitulo() {
        obtDatos();
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        titCodigoController.setSelected(null);
        usIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Titulo controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTitCodigo(ActionEvent event) {
        if (this.getSelected() != null && titCodigoController.getSelected() == null) {
            titCodigoController.setSelected(this.getSelected().getTitCodigo());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsId(ActionEvent event) {
        if (this.getSelected() != null && usIdController.getSelected() == null) {
            usIdController.setSelected(this.getSelected().getUsId());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Discusion entities that
     * are retrieved from Contenido?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Discusion page
     */
    public String navigateDiscusionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Discusion_items", this.getSelected().getDiscusionList());
        }
        return "/discusion/index";
    }

    public List<Contenido> getItemsContenido() {
        return contenidoFacade.getItemsContenido(titCodigoController.findObj(titCodigo));
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getContenido() {
        try {
            List<Contenido> listCont = contenidoFacade.getItemsContenido(titCodigoController.findObj(titCodigo));
            contenido = "vacio";
            if (listCont.size() > 0) {
                Contenido contObj = listCont.get(listCont.size() - 1);
                if (contObj != null) {
                    contenido = contObj.getConContenido();
                }
            }
        } catch (Exception ex) {
        }
        return contenido;
    }

    public Integer getTitCodigo() {
        return titCodigo;
    }

    public void setTitCodigo(Integer titCodigo) {
        this.titCodigo = titCodigo;
    }

    public String guardar() {
        Contenido contObj = new Contenido();
        contObj.setConContenido(contenido);
        contObj.setConFecha(new Date());
        contObj.setTitCodigo(titCodigoController.findObj(titCodigo));
        contObj.setUsId(usIdController.findObj(usCodigo));
        contenidoFacade.create(contObj);
        return "/contenido/index.xhtml";
    }

    public String getUsCodigo() {
        return usCodigo;
    }

    public void setUsCodigo(String usCodigo) {
        this.usCodigo = usCodigo;
    }

    private void obtDatos() {
        try {
            usCodigo = (String) session.getAttribute("usuario");
            Object param = params.get("param");
            if (param == null) {
                if (titCodigo == null) {
                    titCodigo = 1;
                }
            } else {
                titCodigo = new Integer(param.toString());
            }
            Titulo titObj = titCodigoController.findObj(titCodigo);
            if (titObj != null) {
                titulo = titObj.getTitTitulo();
            } else {
                titulo = "";
            }
        } catch (Exception ex) {
        }
    }

    public List<Usuario> getItemsUsuario() {
        usCodigo = (String) session.getAttribute("usuario");
        List<Usuario> itemsUsuario = null;
        if (usCodigo != null) {
            itemsUsuario = usuarioFacade.findByID(usCodigo);
        }
        return itemsUsuario;
    }

    public void actualizarUsuario() {
        usCodigo = usIdController.getSelected().getUsId();
        if (usCodigo != null) {
            session.setAttribute("usuario", usCodigo);
        }
    }

    public Contenido getCont() {
        try {
            List<Contenido> listCont = contenidoFacade.getItemsContenido(titCodigoController.findObj(titCodigo));
            if (listCont.size() > 0) {
                Contenido contObj = listCont.get(listCont.size() - 1);
                if (contObj != null) {
                    cont = contObj;
                }
            }
        } catch (Exception ex) {
        }
        return cont;
    }

    public void setCont(Contenido cont) {
        this.cont = cont;
    }

    public List<Discusion> getItemsComentarios() {
        obtDatos();
        System.out.println("titCodigo " + titCodigo);
        List<Discusion> itemsComentarios = discusionFacade.getItemsComentarios(titCodigoController.findObj(titCodigo));
        return itemsComentarios;
    }

}
