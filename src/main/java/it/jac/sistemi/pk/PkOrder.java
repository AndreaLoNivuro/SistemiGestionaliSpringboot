package it.jac.sistemi.pk;

import java.io.Serializable;

import lombok.Data;

@Data
public  class PkOrder implements Serializable {

	private int codOrder;
	
	private int codClient;
	

}