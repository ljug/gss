/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares.sb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.cofares.Articles;
import net.cofares.Mouvement;

/**
 *
 * @author pfares
 */
@Stateless
public class MouvementFacade extends AbstractFacade<Mouvement> {
    @PersistenceContext(unitName = "inventairePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MouvementFacade() {
        super(Mouvement.class);
    }
    public List<Mouvement> findByArticle(Articles a){
        return em.createNamedQuery("Mouvement.findByIdArticle").setParameter("idArticle", a).getResultList();
    }
}
