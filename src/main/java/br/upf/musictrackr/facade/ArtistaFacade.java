package br.upf.musictrackr.facade;

import br.upf.musictrackr.entity.ArtistaEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ArtistaFacade extends AbstractFacade<ArtistaEntity> {

    @PersistenceContext(unitName = "UPF")
    private EntityManager em;

    public ArtistaFacade() {
        super(ArtistaEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
