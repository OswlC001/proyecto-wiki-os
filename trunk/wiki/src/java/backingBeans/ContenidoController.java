package backingBeans;

import entidades.Contenido;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import sessionBeans.ContenidoFacade;

@Named(value = "contenidoController")
@ViewScoped
public class ContenidoController extends AbstractController<Contenido> {

    @EJB
    private ContenidoFacade contenidoFacade;

    @Inject
    private TituloController titCodigoController;
    @Inject
    private UsuarioController usIdController;
    @Inject
    private DiscusionController discusionListController;

    String contenido;
    String usCodigo="Oswaldo";
    String titulo = "Casa";
    Integer titCodigo=4;

    public ContenidoController() {
        // Inform the Abstract parent controller of the concrete Contenido?cap_first Entity
        super(Contenido.class);
    }

    public String getTitulo() {
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
        List<Contenido> listCont = contenidoFacade.getItemsContenido(titCodigoController.findObj(titCodigo));
        contenido = "vacio";
        if (listCont.size() > 0) {
            Contenido contObj = listCont.get(listCont.size() - 1);
            if (contObj != null) {
                contenido = contObj.getConContenido();
            }
        }
        return contenido;
    }
    
    public void guardar(){
        Contenido contObj = new Contenido();
        contObj.setConContenido(contenido);
        contObj.setConFecha(new Date());
        contObj.setTitCodigo(titCodigoController.findObj(titCodigo));
        contObj.setUsId(usIdController.findObj(usCodigo));
        contenidoFacade.create(contObj);
    }

}
