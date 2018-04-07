package br.com.livrariaonline.loja.service;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.livrariaonline.loja.daos.CompraDao;
import br.com.livrariaonline.loja.models.Compra;

/**
 * class manage payment service
 * this service is expose using url "payment" on @Path annotation
 *  relative URI path indicating where the Java class will be hosted
 * @author danilo
 *
 */
@Path("/pagamento")
public class PagamentoService {

	@Context
	private ServletContext context;
	
	@Inject
	private CompraDao compraDao;
	@Inject
	private PagamentoGateway pagamentoGateway;
	
	private static ExecutorService executor = Executors.newFixedThreadPool(50);
	
	/**
	 * should be here only one VERB in the class: GET, POST, PUT, DELETE, ETC
	 * assync method example
	 * @param argUuid
	 * @return
	 */
	/*@POST
	public void pagarAssync(@Suspended final AsyncResponse ar, @QueryParam("uuid") String argUuid) {
		Compra compra = compraDao.buscaPorUuid(argUuid);
		
		executor.submit(new Runnable() {
			@Override
			public void run() {
				try {
					String resposta = pagamentoGateway.pagar(compra.getTotal());
					System.out.println(resposta);
					
					URI responseUri = UriBuilder.fromPath("http://localhost:8080" + 
							context.getContextPath() + "/index.xhtml")
							.queryParam("msg", "Compra Realizada com Sucesso")
							.build();
					Response response = Response.seeOther(responseUri).build();
					ar.resume(response);
				} catch (Exception e) {
					ar.resume(new WebApplicationException(e));
				}
			}
		});
	}
	*/
	
	/**
	 * should be here only one VERB in the class: GET, POST, PUT, DELETE, ETC
	 * @param argUuid
	 * @return
	 */
	@POST
	public Response pagar(@QueryParam("uuid") String argUuid) {
		Compra compra = compraDao.buscaPorUuid(argUuid);
		String resposta = pagamentoGateway.pagar(compra.getTotal());
		System.out.println(resposta);
		
		URI responseUri = UriBuilder.fromPath("http://localhost:8080" + 
				context.getContextPath() + "/index.xhtml")
				.queryParam("msg", "Compra Realizada com Sucesso")
				.build();
		Response response = Response.seeOther(responseUri).build();
		return response;
		
	}
	
}








