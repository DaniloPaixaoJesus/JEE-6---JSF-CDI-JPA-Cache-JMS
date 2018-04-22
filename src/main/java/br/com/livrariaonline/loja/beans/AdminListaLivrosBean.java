package br.com.livrariaonline.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livrariaonline.loja.daos.LivroDao;
import br.com.livrariaonline.loja.models.Livro;

@Model
public class AdminListaLivrosBean {

	@Inject
	private LivroDao dao;
	
	private List<Livro> livros = new ArrayList<>();
	
	public List<Livro> getLivros() {
		this.livros = dao.listar();
		
		return livros;
	}
	
	public String remove(Livro livro){
		dao.remove(livro);
		return "/livros/lista?faces-redirect=true"; 
	}
	
	public String edit(Livro livro){
		return "/livros/form?faces-redirect=true&id="+livro.getId();
	}

}










