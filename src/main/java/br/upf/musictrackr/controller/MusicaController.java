package br.upf.musictrackr.controller;

import br.upf.musictrackr.entity.MusicaEntity;
import br.upf.musictrackr.entity.ArtistaEntity;
import br.upf.musictrackr.facade.MusicaFacade;
import br.upf.musictrackr.facade.ArtistaFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class MusicaController implements Serializable {

    @EJB
    private MusicaFacade musicaFacade;

    @EJB
    private ArtistaFacade artistaFacade;

    private MusicaEntity entidade = new MusicaEntity(); // cadastro
    private MusicaEntity selecionado = new MusicaEntity(); // edição

    public String inserir() {
        try {
            musicaFacade.create(entidade);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Música salva com sucesso!", null));
            entidade = new MusicaEntity(); // limpa o form
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar música: " + e.getMessage(), null));
        }
        return null;
    }

    public void prepararEdicao(MusicaEntity musica) {
        this.selecionado = musica;
    }

    public String alterar() {
        try {
            musicaFacade.edit(selecionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Música atualizada com sucesso!", null));
            selecionado = new MusicaEntity(); // limpa o pop-up
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar música: " + e.getMessage(), null));
        }
        return null;
    }

    public String excluir(MusicaEntity m) {
        musicaFacade.remove(m);
        return null;
    }

    public List<MusicaEntity> getListarTodos() {
        return musicaFacade.findAll();
    }

    public List<ArtistaEntity> getListaArtistas() {
        return artistaFacade.findAll();
    }

    public MusicaEntity getEntidade() {
        return entidade;
    }

    public void setEntidade(MusicaEntity entidade) {
        this.entidade = entidade;
    }

    public MusicaEntity getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(MusicaEntity selecionado) {
        this.selecionado = selecionado;
    }
}
