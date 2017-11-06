package br.com.livrariaonline.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.livrariaonline.loja.models.Usuario;

public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;

	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}
	
}
