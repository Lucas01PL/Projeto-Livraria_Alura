package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Venda;

public class VendaDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Venda> daoVenda;

	@PostConstruct
	void init() 
	{
		this.daoVenda = new DAO<Venda>(em, Venda.class);
	}
	
	public List<Venda> listaTodas() {
		return daoVenda.listaTodos();
	}
}
