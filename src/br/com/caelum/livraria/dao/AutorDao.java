package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Autor;

public class AutorDao implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Autor> daoAutor;

	@PostConstruct
	void init() 
	{
		this.daoAutor = new DAO<Autor>(em, Autor.class);
	}
	
	/**
	 * @param t
	 * @see br.com.caelum.livraria.dao.DAO#adiciona(java.lang.Object)
	 */
	public void adiciona(Autor t) {
		daoAutor.adiciona(t);
	}

	/**
	 * @param t
	 * @see br.com.caelum.livraria.dao.DAO#remove(java.lang.Object)
	 */
	public void remove(Autor t) {
		daoAutor.remove(t);
	}

	/**
	 * @param t
	 * @see br.com.caelum.livraria.dao.DAO#atualiza(java.lang.Object)
	 */
	public void atualiza(Autor t) {
		daoAutor.atualiza(t);
	}

	/**
	 * @return
	 * @see br.com.caelum.livraria.dao.DAO#listaTodos()
	 */
	public List<Autor> listaTodos() {
		return daoAutor.listaTodos();
	}

	/**
	 * @param id
	 * @return
	 * @see br.com.caelum.livraria.dao.DAO#buscaPorId(java.lang.Integer)
	 */
	public Autor buscaPorId(Integer id) {
		return daoAutor.buscaPorId(id);
	}
	
	

}
