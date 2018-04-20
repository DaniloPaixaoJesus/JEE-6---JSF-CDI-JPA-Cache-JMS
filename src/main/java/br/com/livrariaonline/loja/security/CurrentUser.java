package br.com.livrariaonline.loja.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.livrariaonline.loja.daos.SecurityDao;
import br.com.livrariaonline.loja.models.SystemUser;

@Model
public class CurrentUser {

	@Inject
	private HttpServletRequest request;

	@Inject
	private SecurityDao securityDao;

	private SystemUser systemUser;

	@PostConstruct
	private void loadSystemUser() {

		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			String email = request.getUserPrincipal().getName();
			systemUser = securityDao.findByEmail(email);
		}
	}
	
	public boolean hasRole(String name) {
        return request.isUserInRole(name);
    }
	
	public SystemUser get() {
        return systemUser;
    }
	
	public String logout(){
		this.request.getSession().invalidate();
		return "/livros/lista.xhtml?faces-redirect=true";
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public SecurityDao getSecurityDao() {
		return securityDao;
	}

	public void setSecurityDao(SecurityDao securityDao) {
		this.securityDao = securityDao;
	}

	public SystemUser getPrincipal() {
		return systemUser;
	}

	public void setPrincipal(SystemUser principal) {
		this.systemUser = principal;
	}
}
