
package br.upf.musictrackr.controller;

import br.upf.musictrackr.entity.MusicaEntity;
import br.upf.musictrackr.entity.PlaylistEntity;
import br.upf.musictrackr.facade.MusicaFacade;
import br.upf.musictrackr.facade.PlaylistFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PlaylistController implements Serializable {

    @EJB
    private PlaylistFacade playlistFacade;

    @EJB
    private MusicaFacade musicaFacade;

    private PlaylistEntity entidade = new PlaylistEntity();
    private PlaylistEntity selecionado;

    public String inserir() {
        try {
            if (entidade.getId() == null) {
                playlistFacade.create(entidade);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Playlist salva com sucesso!", null));
            } else {
                playlistFacade.edit(entidade);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Playlist atualizada com sucesso!", null));
            }
            entidade = new PlaylistEntity(); // limpa o formulário
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar playlist: " + e.getMessage(), null));
            e.printStackTrace();
        }
        return null;
    }

    public String excluir(PlaylistEntity p) {
        playlistFacade.remove(p);
        return null;
    }

    public List<PlaylistEntity> getListarTodos() {
        return playlistFacade.findAll();
    }

    public List<MusicaEntity> getListaMusicas() {
        return musicaFacade.findAll();
    }

    public PlaylistEntity getEntidade() {
        return entidade;
    }

    public void setEntidade(PlaylistEntity entidade) {
        this.entidade = entidade;
    }

    public PlaylistEntity getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(PlaylistEntity selecionado) {
        this.selecionado = selecionado;
    }
}
