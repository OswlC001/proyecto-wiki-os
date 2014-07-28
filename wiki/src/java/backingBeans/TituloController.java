package backingBeans;

import entidades.Titulo;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tituloController")
@ViewScoped
public class TituloController extends AbstractController<Titulo> {

    @Inject
    private ContenidoController contenidoListController;

    public TituloController() {
        // Inform the Abstract parent controller of the concrete Titulo?cap_first Entity
        super(Titulo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Contenido entities that
     * are retrieved from Titulo?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Contenido page
     */
    public String navigateContenidoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Contenido_items", this.getSelected().getContenidoList());
        }
        return "/contenido/index";
    }

}
