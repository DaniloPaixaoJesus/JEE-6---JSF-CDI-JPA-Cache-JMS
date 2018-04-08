package br.com.livrariaonline.loja.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.livrariaonline.loja.daos.LivroDao;
import br.com.livrariaonline.loja.models.Livro;

/**
 * REST API - Book Resource
 * @author danilo
 *
 */
@Path("livro")
public class LivroResource {
	
	@Inject
    private LivroDao dao;

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Livro> ultimosLancamentos() {
	    return dao.ultimosLancamentos();
	}

}
