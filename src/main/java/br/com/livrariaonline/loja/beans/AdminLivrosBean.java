package br.com.livrariaonline.loja.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.livrariaonline.loja.daos.LivroDao;
import br.com.livrariaonline.loja.models.Livro;

@Named
@RequestScoped
public class AdminLivrosBean {
	
	private Livro livro = new Livro();
	
	@Inject
	private LivroDao dao;

	@Transactional
	public void salvar() {
		dao.salvar(livro);
		System.out.println("Livro Cadastrado: " + livro);
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
}
