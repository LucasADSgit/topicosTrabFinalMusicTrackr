
package br.upf.musictrackr.facade;

import br.upf.musictrackr.entity.MusicaEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class MusicaFacade extends AbstractFacade<MusicaEntity> {

    @PersistenceContext(unitName = "UPF")
    private EntityManager em;

    public MusicaFacade() {
        super(MusicaEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
