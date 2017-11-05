package br.com.livrariaonline.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.livrariaonline.loja.models.Livro;

public class LivroDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Livro livro) {
		manager.persist(livro);
	}
	
}











