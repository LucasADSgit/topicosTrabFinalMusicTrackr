package br.upf.musictrackr.controller;

import br.upf.musictrackr.entity.ArtistaEntity;
import br.upf.musictrackr.facade.ArtistaFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ArtistaController implements Serializable {

    @EJB
    private ArtistaFacade facade;

    private ArtistaEntity entidade = new ArtistaEntity();

    public List<ArtistaEntity> getListarTodos() {
        return facade.findAll();
    }

    public String inserir() {
    try {
        facade.create(entidade);
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Artista salvo com sucesso!", null));
        entidade = new ArtistaEntity(); // limpa o formulário
    } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar artista: " + e.getMessage(), null));
        e.printStackTrace(); // log no console
    }
    return null;
}

    public String alterar() {
        facade.edit(entidade);
        entidade = new ArtistaEntity();
        return null;
    }

    public String excluir(ArtistaEntity e) {
        facade.remove(e);
        return null;
    }

    public ArtistaEntity getEntidade() {
        return entidade;
    }

    public void setEntidade(ArtistaEntity entidade) {
        this.entidade = entidade;
    }
}
