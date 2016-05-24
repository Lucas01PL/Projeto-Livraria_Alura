package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Livro;

public class LivroDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	private DAO<Livro> livroDao;
	
	@PostConstruct
	void init()
	{
		this.livroDao = new DAO<Livro>(em, Livro.class);
	}
	
	/**
	 * @param t
	 * @see br.com.caelum.livraria.dao.DAO#adiciona(java.lang.Object)
	 */
	public void adiciona(Livro t) {
		livroDao.adiciona(t);
	}

	/**
	 * @param t
	 * @see br.com.caelum.livraria.dao.DAO#remove(java.lang.Object)
	 */
	public void remove(Livro t) {
		livroDao.remove(t);
	}

	/**
	 * @param t
	 * @see br.com.caelum.livraria.dao.DAO#atualiza(java.lang.Object)
	 */
	public void atualiza(Livro t) {
		livroDao.atualiza(t);
	}

	/**
	 * @return
	 * @see br.com.caelum.livraria.dao.DAO#listaTodos()
	 */
	public List<Livro> listaTodos() {
		return livroDao.listaTodos();
	}

	/**
	 * @param id
	 * @return
	 * @see br.com.caelum.livraria.dao.DAO#buscaPorId(java.lang.Integer)
	 */
	public Livro buscaPorId(Integer id) {
		return livroDao.buscaPorId(id);
	}
	

}
