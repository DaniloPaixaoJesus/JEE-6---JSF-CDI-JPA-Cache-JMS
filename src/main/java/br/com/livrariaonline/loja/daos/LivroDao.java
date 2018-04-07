package br.com.livrariaonline.loja.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.QueryHints;

import br.com.livrariaonline.loja.models.Livro;

@Stateful
public class LivroDao {

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager manager;
	
	public void salvar(Livro livro) {
		manager.persist(livro);
	}
	
	public List<Livro> listar() {
		String jpql = "select distinct(l) from Livro l "
				+ " join fetch l.autores";
		
		return manager.createQuery(jpql, Livro.class).getResultList();
	}

	public List<Livro> ultimosLancamentos() {
		String jpql = "select l from Livro l order by l.id desc";
		return manager.createQuery(jpql, Livro.class)
				.setMaxResults(5)
				.setHint(QueryHints.HINT_CACHEABLE, true)
				.setHint(QueryHints.HINT_CACHE_REGION, "home")
				.getResultList();
	}

	public List<Livro> demaisLivros() {
		String jpql = "select l from Livro l order by l.id desc";
		return manager.createQuery(jpql, Livro.class)
				.setFirstResult(5)
				.setHint(QueryHints.HINT_CACHEABLE, true)
				.setHint(QueryHints.HINT_CACHE_REGION, "home")
				.getResultList();
	}

	public Livro buscarPorId(Integer id) {
//		return manager.find(Livro.class, id);
		
		String jpql = "select l from Livro l join fetch l.autores "
				+ "where l.id = :id";
		return manager.createQuery(jpql, Livro.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public void limpaCache() {
	    Cache cache = manager.getEntityManagerFactory().getCache();
	    cache.evict(Livro.class);
	    cache.evict(Livro.class, 1L);
	    cache.evictAll();
	    
	    SessionFactory factory = manager.getEntityManagerFactory().unwrap(SessionFactory.class);
	    factory.getCache().evictAllRegions();
	    factory.getCache().evictQueryRegion("home");//who is in "home" will be clear
	    //.setHint(QueryHints.HINT_CACHE_REGION, "home")
	    //it is not JPA, it is HIBERNATE

	    //independente de limpar programaticamente, o cache sempre sera limpo apos insert, update ou delete
	}
	
}











