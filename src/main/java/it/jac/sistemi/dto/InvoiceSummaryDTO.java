package it.jac.sistemi.dto;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.InvoiceSummary;
import lombok.Data;

@Data
public class InvoiceSummaryDTO {

	private int codInvoice;

	private float totalAmount;
	
	private String payment;
	
	private int currency;
	
	private float vat;
	
	private float taxable;

	public static InvoiceSummaryDTO build(InvoiceSummary invoiceSummary) {

		InvoiceSummaryDTO result = new InvoiceSummaryDTO();
		BeanUtils.copyProperties(invoiceSummary, result);

		return result;
	}

}
