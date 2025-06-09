package br.upf.musictrackr.controller;

import br.upf.musictrackr.entity.ArtistaEntity;
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
public class ArtistaController implements Serializable {

    @EJB
    private ArtistaFacade facade;

    private ArtistaEntity entidade = new ArtistaEntity(); // para cadastro
    private ArtistaEntity selecionado = new ArtistaEntity(); // para edição no pop-up

    public String inserir() {
        try {
            facade.create(entidade);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Artista salvo com sucesso!", null));
            entidade = new ArtistaEntity(); // limpa o form
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar artista: " + e.getMessage(), null));
        }
        return null;
    }

    public void prepararEdicao(ArtistaEntity artista) {
        this.selecionado = artista;
    }

    public String alterar() {
        try {
            facade.edit(selecionado);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Artista atualizado com sucesso!", null));
            selecionado = new ArtistaEntity(); // limpa o pop-up
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar artista: " + e.getMessage(), null));
        }
        return null;
    }

    public String excluir(ArtistaEntity artista) {
        facade.remove(artista);
        return null;
    }

    public List<ArtistaEntity> getListarTodos() {
        return facade.findAll();
    }

    public ArtistaEntity getEntidade() {
        return entidade;
    }

    public void setEntidade(ArtistaEntity entidade) {
        this.entidade = entidade;
    }

    public ArtistaEntity getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(ArtistaEntity selecionado) {
        this.selecionado = selecionado;
    }
}
