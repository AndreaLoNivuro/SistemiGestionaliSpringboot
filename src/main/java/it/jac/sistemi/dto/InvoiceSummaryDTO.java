package it.jac.sistemi.dto;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.InvoiceSummary;
import lombok.Data;

@Data
public class InvoiceSummaryDTO {

	private String codInvoice;

	private float totalAmount;
	
	private float totalProducts;
	
	private float totalServices;
	
	private float totalDiscount;
	
	private float tailDiscount;
	
	private float totalTileDiscount;
	
	private float totalLineDiscount;
	
	private float totalVat;
	
	private float taxable;

	public static InvoiceSummaryDTO build(InvoiceSummary invoiceSummary) {

		InvoiceSummaryDTO result = new InvoiceSummaryDTO();
		BeanUtils.copyProperties(invoiceSummary, result);

		return result;
	}

}
