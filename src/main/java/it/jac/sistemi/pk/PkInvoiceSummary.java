package it.jac.sistemi.pk;

import java.io.Serializable;

import lombok.Data;

@Data
public  class PkInvoiceSummary implements Serializable {

	private String codInvoice;
	
	private String totalAmount;
	

}