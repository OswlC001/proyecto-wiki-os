/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingBeans;

/**
 *
 * @author SiperProg
 */
import entidades.Usuario;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import sessionBeans.UsuarioFacade;

@ManagedBean
public class UserLoginView {

    @EJB
    private UsuarioFacade usuarioFacade;

    private String username;
    private String password;
    private String ingreso;
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    public String getIngreso() {
        Boolean logeado = (Boolean) session.getAttribute("sesionLoggedIn");
        if (logeado != null && logeado) {
            String usuario = (String) session.getAttribute("usuario");
            ingreso = usuario + " (Cuenta)";
        } else {
            ingreso = "Ingresar";
        }
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        Usuario usuario = usuarioFacade.find(username);
        if (usuario != null) {
            if (username != null && username.equals(usuario.getUsId()) && password != null && password.equals(usuario.getUsClave())) {
                loggedIn = true;
                ingreso = "Bienvenido " + username;
                context.addCallbackParam("usuario", username);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
            } else {
                loggedIn = false;
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
            }
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        session.setAttribute("sesionLoggedIn", loggedIn);
        session.setAttribute("usuario", username);
    }

    public void logout() {
        session.setAttribute("sesionLoggedIn", false);
        session.setAttribute("usuario", null);
        username = null;
        password = null;
    }
}
