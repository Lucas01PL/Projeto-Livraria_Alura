package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	
	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private FacesContext context; 
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuarLogin() {
		System.out.println("fazendo login do usu�rio.");
		boolean isExiste = usuarioDao.existeUsuario(this.usuario);
		if(isExiste)
		{
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}
		else
		{
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Usu�rio n�o encontrado"));
			return "login?faces-redirect=true";
		}
	}
	
	public String deslogar() {
	    context.getExternalContext().getSessionMap().remove("usuarioLogado");
	    return "login?faces-redirect=true";
	}
	
}
