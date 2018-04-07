package br.com.livrariaonline.loja.models;

import java.math.BigDecimal;

/**
 * VO represent payment
 * @author danilo
 *
 */
public class Pagamento {

	private BigDecimal value;
	
	public Pagamento(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
