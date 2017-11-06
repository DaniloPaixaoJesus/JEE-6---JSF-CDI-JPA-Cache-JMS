package br.com.livrariaonline.loja.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.livrariaonline.loja.daos.AutorDao;
import br.com.livrariaonline.loja.models.Autor;

@Named
@RequestScoped
public class AdminAutoresBean {
	
	private Autor autor = new Autor();
	
	@Inject
	private AutorDao autorDao;
	
	@Transactional
	public String salvar() {
		autorDao.salvar(autor);
		autor = new Autor();
		return "/livros/lista?faces-redirect=true";
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}


}
