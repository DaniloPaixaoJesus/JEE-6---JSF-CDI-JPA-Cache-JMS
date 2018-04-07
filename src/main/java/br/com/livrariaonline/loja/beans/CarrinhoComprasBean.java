package br.com.livrariaonline.loja.beans;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livrariaonline.loja.daos.LivroDao;
import br.com.livrariaonline.loja.models.CarrinhoCompras;
import br.com.livrariaonline.loja.models.CarrinhoItem;
import br.com.livrariaonline.loja.models.Livro;

/**
 * class manage shopping cart
 * list add remove items,
 * @author danilo
 *
 */
@Model
public class CarrinhoComprasBean {

	@Inject
	private LivroDao livroDao;
	
	@Inject
	private CarrinhoCompras carrinho; //SessionScoped class to manage shopping cart
	
	public String add(Integer id) {
		Livro livro = livroDao.buscarPorId(id);
		CarrinhoItem item = new CarrinhoItem(livro);
		carrinho.add(item);
		
		return "carrinho?faces-redirect=true";
	}
	
	public void remover(CarrinhoItem item) {
		carrinho.remover(item);
	}
	
	public List<CarrinhoItem> getItens() {
		return carrinho.getItens();
	}
	
}








