package br.upf.musictrackr.facade;

import br.upf.musictrackr.entity.UsuarioEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UsuarioFacade extends AbstractFacade<UsuarioEntity> {

    @PersistenceContext(unitName = "UPF")
    private EntityManager em;

    public UsuarioFacade() {
        super(UsuarioEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioEntity buscarPorEmail(String email, String senha) {
        try {
            return em.createQuery(
                    "SELECT u FROM UsuarioEntity u WHERE u.email = :email AND u.senha = :senha", UsuarioEntity.class)
                    .setParameter("email", email)
                    .setParameter("senha", senha)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
