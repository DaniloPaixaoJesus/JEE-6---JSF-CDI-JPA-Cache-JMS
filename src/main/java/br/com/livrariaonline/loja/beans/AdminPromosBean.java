package br.com.livrariaonline.loja.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livrariaonline.loja.models.Promo;
import br.com.livrariaonline.loja.websocket.PromosEndPoint;

@Model
public class AdminPromosBean {

    private Promo promo = new Promo();
    
    @Inject
    private PromosEndPoint promos;

    public void enviar() {
        promos.send(promo);        
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }
}
