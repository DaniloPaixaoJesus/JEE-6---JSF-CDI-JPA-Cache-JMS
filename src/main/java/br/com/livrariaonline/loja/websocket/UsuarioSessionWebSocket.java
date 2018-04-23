package br.com.livrariaonline.loja.websocket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

@ApplicationScoped
public class UsuarioSessionWebSocket {

	List<Session> sessions = new ArrayList<Session>();
	
	public List<Session> getUsuarios() {
		return Collections.unmodifiableList(sessions);
    }
	
	public void add(Session session){
		sessions.add(session);
	}
	
	public void remove(Session session){
		sessions.remove(session);
	}
	
	
}
