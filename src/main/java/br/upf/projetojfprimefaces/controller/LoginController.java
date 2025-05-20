package br.upf.projetojfprimefaces.controller;

import br.upf.projetojfprimefaces.entity.PessoaEntity;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;

@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    private br.upf.projetojfprimefaces.facade.PessoaFacade ejbFacade;

    public LoginController() {
    }

    //objeto que representa uma pessoa
    private PessoaEntity pessoa;

    public void prepareAutenticarPessoa() {
        pessoa = new PessoaEntity();
    }

    /**
     * Método utilizado para inicializar métodos ao instanciar a classe...
     */
    @PostConstruct
    public void init() {
        prepareAutenticarPessoa();
    }

    /**
     * Método utilizado para validar login e senha.
     *
     * @return
     */
    public String validarLogin() {
        //criando o contexto
        FacesContext context = FacesContext.getCurrentInstance();
        //captura a sessão do contexto criado pelo JavaServerFaces
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

        PessoaEntity pessoaDB = ejbFacade.buscarPorEmail(pessoa.getEmail(), pessoa.getSenha());
        if ((pessoaDB != null && pessoaDB.getId() != null)) {
            //adicionando na sessão o atributo logado.
            //[pessoaLogada] é o nome do atributo na sessão e [pessoa]
            //é o objeto pessoa sendo enviada como valor
            session.setAttribute("pessoaLogada", pessoaDB);
            //caso as credenciais foram válidas, então direciona para página index
            return "/admin/pessoa.xhtml?faces-redirect=true";
        } else {
            //senão, exibe uma mensagem de falha...
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Falha no Login!",
                    "Email ou senha incorreto!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * Método utilizado para sair e invalidar a sessao.
     * @return 
     */
    public String logout() {
        //criando o contexto
        FacesContext context = FacesContext.getCurrentInstance();
        //captura a sessão do contexto criado pelo JavaServerFaces
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        //invalida a sessão
        session.invalidate();
        //?faces-redirect=true realiza o redirecionamento de página
        return "/login.xhtml?faces-redirect=true";
    }

}
















