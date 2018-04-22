package br.com.livrariaonline.loja.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.livrariaonline.loja.models.Autor;

public class AutorDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4737674273492515888L;
	@PersistenceContext
	private EntityManager manager;

	public List<Autor> listar() {
		return manager.createQuery(
				"select a from Autor a", Autor.class)
				.getResultList();
	}
	
	public void salvar(Autor autor){
		manager.persist(autor);
	}
	
}






