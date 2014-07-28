/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entidades.Contenido;
import entidades.Titulo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SiperProg
 */
@Stateless
public class ContenidoFacade extends AbstractFacade<Contenido> {

    @PersistenceContext(unitName = "wikiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContenidoFacade() {
        super(Contenido.class);
    }

    public List<Contenido> getItemsContenido(Titulo titCodigo) {
        List<Contenido> itemsContenido = em.createQuery("SELECT c FROM Contenido c WHERE c.titCodigo = :titCodigo").setParameter("titCodigo", titCodigo).getResultList();
        return itemsContenido;
    }

}
