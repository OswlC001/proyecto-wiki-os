package backingBeans;

import entidades.Usuario;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> {

    @Inject
    private ContenidoController contenidoListController;
    @Inject
    private DiscusionController discusionListController;

    public UsuarioController() {
        // Inform the Abstract parent controller of the concrete Usuario?cap_first Entity
        super(Usuario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Contenido entities that
     * are retrieved from Usuario?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Contenido page
     */
    public String navigateContenidoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Contenido_items", this.getSelected().getContenidoList());
        }
        return "/contenido/index";
    }

    /**
     * Sets the "items" attribute with a collection of Discusion entities that
     * are retrieved from Usuario?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Discusion page
     */
    public String navigateDiscusionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Discusion_items", this.getSelected().getDiscusionList());
        }
        return "/discusion/index";
    }

}
