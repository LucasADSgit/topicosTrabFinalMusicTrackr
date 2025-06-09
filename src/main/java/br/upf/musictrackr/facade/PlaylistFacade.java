
package br.upf.musictrackr.facade;

import br.upf.musictrackr.entity.PlaylistEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PlaylistFacade extends AbstractFacade<PlaylistEntity> {

    @PersistenceContext(unitName = "UPF")
    private EntityManager em;


    public PlaylistFacade() {
        super(PlaylistEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
