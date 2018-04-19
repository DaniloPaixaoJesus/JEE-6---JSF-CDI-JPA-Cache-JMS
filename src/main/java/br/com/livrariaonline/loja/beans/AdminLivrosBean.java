package br.com.livrariaonline.loja.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.livrariaonline.loja.daos.AutorDao;
import br.com.livrariaonline.loja.daos.LivroDao;
import br.com.livrariaonline.loja.models.Autor;
import br.com.livrariaonline.loja.models.Livro;

@Model
public class AdminLivrosBean {
	
	private Livro livro = new Livro();
	
	@Inject
	private LivroDao dao;
	
	@Inject
	private AutorDao autorDao;
	
	//produzido pelo FacesContextProducer, pois nao ha integracao entre o cdi e o jsf
	@Inject
	private FacesContext context; 
	
	private Part capaLivro;
	
	@Transactional
	public String salvar() {
		
		//FileSaver fileSaver = new FileSaver();
		//String capaPath = fileSaver.write(capaLivro, "livros");
		//livro.setCapaPath(capaPath);
		dao.salvar(livro);
		
		context.getExternalContext().getFlash().setKeepMessages(true);//mantem as mensagens mesmo com o faces-redirect
		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso!"));
		
		//o xhtml no final nao e obrigatorio
		////faces-redirect para nao dar o forward
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
