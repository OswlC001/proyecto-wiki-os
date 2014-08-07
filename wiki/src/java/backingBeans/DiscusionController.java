package backingBeans;

import entidades.Discusion;
import entidades.Titulo;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import sessionBeans.DiscusionFacade;

@Named(value = "discusionController")
@ViewScoped
public class DiscusionController extends AbstractController<Discusion> {
   

    @Inject
    private ContenidoController conCodigoController;
    @Inject
    private UsuarioController usIdController;
    
    

    public DiscusionController() {
        // Inform the Abstract parent controller of the concrete Discusion?cap_first Entity
        super(Discusion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        conCodigoController.setSelected(null);
        usIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Contenido controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareConCodigo(ActionEvent event) {
        if (this.getSelected() != null && conCodigoController.getSelected() == null) {
            conCodigoController.setSelected(this.getSelected().getConCodigo());
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
    
  
}
