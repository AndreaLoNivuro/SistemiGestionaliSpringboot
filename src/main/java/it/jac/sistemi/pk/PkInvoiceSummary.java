package it.jac.sistemi.pk;

import java.io.Serializable;

import lombok.Data;

@Data
public  class PkInvoiceSummary implements Serializable {

	private int codInvoice;
	
	private int totalAmount;
	

}