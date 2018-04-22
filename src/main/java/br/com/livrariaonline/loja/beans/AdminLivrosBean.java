package br.com.livrariaonline.loja.beans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.livrariaonline.loja.daos.AutorDao;
import br.com.livrariaonline.loja.daos.LivroDao;
import br.com.livrariaonline.loja.models.Autor;
import br.com.livrariaonline.loja.models.Livro;

@Model
public class AdminLivrosBean{
	
	private Livro livro = new Livro();
	
	@Inject
	private LivroDao dao;
	
	@Inject
	private AutorDao autorDao;
	
	//produzido pelo FacesContextProducer, pois nao ha integracao entre o cdi e o jsf
	@Inject
	private FacesContext context; 
	
	private Part capaLivro;
	
	@PostConstruct
	public void init(){
		 Map<String,String> params = context.getExternalContext().getRequestParameterMap();
		  String id = params.get("id");
		  if(id != null){
			  livro = dao.buscarPorId(new Integer(id));
		  }
	}
	
	public String salvar() {
		
		if(livro.getId() == null){
			return create();
		}else{
			return update();
		} 
	}
	
	@Transactional
	public String create() {
		dao.create(livro);		
		context.getExternalContext().getFlash().setKeepMessages(true);//mantem as mensagens mesmo com o faces-redirect
		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso!"));
		return "/livros/lista?faces-redirect=true"; 
	}
	
	@Transactional
	public String update() {
		dao.update(livro);		
		context.getExternalContext().getFlash().setKeepMessages(true);//mantem as mensagens mesmo com o faces-redirect
		context.addMessage(null, new FacesMessage("Livro atualizado com sucesso!"));
		return "/livros/lista?faces-redirect=true"; 
	}

	public List<Autor> getAutores() {
		return autorDao.listar();
	}
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}

}
