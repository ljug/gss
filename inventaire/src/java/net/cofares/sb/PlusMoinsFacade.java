/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares.sb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.cofares.PlusMoins;

/**
 *
 * @author pfares
 */
@Stateless
public class PlusMoinsFacade extends AbstractFacade<PlusMoins> {
    @PersistenceContext(unitName = "inventairePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlusMoinsFacade() {
        super(PlusMoins.class);
    }
    
}
