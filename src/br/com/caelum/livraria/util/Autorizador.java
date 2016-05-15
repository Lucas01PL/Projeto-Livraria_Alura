package br.com.caelum.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.view.facelets.FaceletContext;

import br.com.caelum.livraria.modelo.Usuario;

public class Autorizador implements PhaseListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent evento) {
		FacesContext context = evento.getFacesContext();		
		String nomePagina = context.getViewRoot().getViewId();
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		if(nomePagina.equals("/login.xhtml"))
		{
			return;
		}
		else
		{
			if(usuarioLogado != null)
			{
				return;
			}
			else
			{
				NavigationHandler handler = context.getApplication().getNavigationHandler();
				handler.handleNavigation(context, null, "login?faces-redirect=true");
				context.renderResponse();
			}
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
