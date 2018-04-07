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
	
	// execute a thread pool - avaiable on JEE 7
	// new concurrence api of java 7
	// with 50 thread
	// do not user thread directly; it is not recommended
	private static ExecutorService executor = Executors.newFixedThreadPool(50);
	
	/**
	 * should be here only one VERB in the class: GET, POST, PUT, DELETE, ETC
	 * assync method example
	 * @param argUuid
	 * @return
	 */
	public void pagarAssync(@Suspended final AsyncResponse asyncResponse, @QueryParam("uuid") String argUuid) {
		/*
		how notify server that run method finish ?
		concurrence java API integrated with jax-rs and cdi with AsyncResponse class
		@Suspended indicate that all execution will be exected on async way
		@Suspended remove this process of the MAIN server thread, and discharge this thread of the POOL
		use this technique always when you need call external app service
		similiar NODEJS do
		*/
		
		Compra compra = compraDao.buscaPorUuid(argUuid);
		
		//submit manage runnable objects
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
					//when run finish, notify the server -> server notify client(http response)
					asyncResponse.resume(response); //notify the server
				} catch (Exception e) {
					asyncResponse.resume(new WebApplicationException(e)); //notify the server
				}
			}
		});
	}
	
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
		
		//after payment redirec to index page
		
		//set up uri
		String protocol = "http";
		String server = "localhost";
		String port = "8080";
		
		//create a URI
		URI responseUri = UriBuilder.fromPath(protocol+"://"+server+":"+port + 
				context.getContextPath() + "/index.xhtml")
				.queryParam("msg", "Compra Realizada com Sucesso")
				.build();

		//Send response
		Response response = Response.seeOther(responseUri).build();
		return response;
		
	}
	
}








