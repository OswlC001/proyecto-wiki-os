/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionBeans;

import entidades.Discusion;
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
public class DiscusionFacade extends AbstractFacade<Discusion> {
    @PersistenceContext(unitName = "wikiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiscusionFacade() {
        super(Discusion.class);
    }
    
    public List<Discusion> getItemsComentarios(Titulo titulo){
        List<Discusion> itemsComentarios = em.createQuery("SELECT d FROM Discusion d WHERE d.conCodigo.titCodigo = :titCodigo").setParameter("titCodigo", titulo).getResultList();
        return itemsComentarios;
    }
    
}
