package it.jac.sistemi.pk;

import java.io.Serializable;

import lombok.Data;

@Data
public  class PkInvoiceDetail implements Serializable {

	private String codInvoice;
	
	private String codItem;
	
	private int line;	

}