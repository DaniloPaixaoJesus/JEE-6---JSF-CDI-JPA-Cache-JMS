package br.com.livrariaonline.loja.beans;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import br.com.livrariaonline.loja.models.CarrinhoCompras;
import br.com.livrariaonline.loja.models.Compra;
import br.com.livrariaonline.loja.models.Usuario;
import br.com.livrariaonline.loja.service.PagamentoService;

/**
 * class to confirm purchase
 * @author danilo
 *
 */
@Model
public class CheckoutBean {

	private Usuario usuario = new Usuario();
	
	@Inject
	private CarrinhoCompras carrinho;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	PagamentoService pagamentoService;
	
	@Transactional
	public void finalizar() {
		Compra compra = new Compra();
		compra.setUsuario(usuario);
		carrinho.finalizar(compra);
		
		//after finish a purchase, it will be redirect to payment url
		//test the own rest api call
		String contextName = facesContext.getExternalContext().getRequestContextPath();
		HttpServletResponse response = (HttpServletResponse)facesContext.getExternalContext().getResponse();
		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);//status 307 temp redirect 
		response.setHeader("Location", contextName+"/services/pagamento?uuid="+compra.getUuid());
		
		//using service directly, see why doesn' work
		//pagamentoService.pagar(compra.getUuid());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}





