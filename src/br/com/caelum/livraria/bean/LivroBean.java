package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.tx.Log;
import br.com.caelum.livraria.tx.Transacional;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();

	private Integer autorId;

	private Integer livroId;

	private List<Livro> listaTodos;
	
	@Inject
	private LivroDao livroDao;
	
	@Inject
	private AutorDao autorDao;

	@Inject
	private FacesContext context;
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public Livro getLivro() {
		return livro;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}
	
	@Log
	public List<Livro> getLivros() {
		if(this.listaTodos == null)		
		{
			this.listaTodos = livroDao.listaTodos();
		}
		return listaTodos;
	}

	@Log
	public List<Autor> getAutores() {
		return autorDao.listaTodos();
	}

	@Log
	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	@Transacional
	public void gravarAutor() {
		Autor autor = autorDao.buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Escrito por: " + autor.getNome());
	}

	@Transacional
	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			context.addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}
		if (this.livro.getId() == null) {			
			livroDao.adiciona(this.livro);
			this.listaTodos = livroDao.listaTodos();
		} else {
			livroDao.atualiza(this.livro);
		}

		this.livro = new Livro();
	}

	public void carregar(Livro livro) {
		System.out.println("Carregando livro " + livro.getTitulo());
		this.livro = this.livroDao.buscaPorId(livro.getId());
	}

	@Transacional
	public void remover(Livro livro) {
		System.out.println("Removendo livro " + livro.getTitulo());
		livroDao.remove(livro);
	}
	
	@Transacional
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}

	public String formAutor() {
		System.out.println("Chamanda do formulário do Autor.");
		return "autor?faces-redirect=true";
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component,
			Object value) throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(
					"ISBN deveria começar com 1"));
		}

	}

	public void carregarLivroPelaId() {
		this.livro = livroDao.buscaPorId(this.livro.getId());
	}
	
	public boolean precoEhMenor(Object valorColuna, Object filtroDigitado, Locale locale) { // java.util.Locale

        String textoDigitado = (filtroDigitado == null) ? null : filtroDigitado.toString().trim();
        System.out.println("Filtrando pelo " + textoDigitado + ", Valor do elemento: " + valorColuna);
        if (textoDigitado == null || textoDigitado.equals("")) 
        {
            return true;
        }

        if (valorColuna == null) 
        {
            return false;
        }

        try {
            Double precoDigitado = Double.valueOf(textoDigitado);
            Double precoColuna = (Double) valorColuna;
            return precoColuna.compareTo(precoDigitado) < 0;

        } catch (NumberFormatException e) {
            return false;
        }
	}

}
