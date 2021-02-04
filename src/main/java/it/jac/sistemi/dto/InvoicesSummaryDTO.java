package it.jac.sistemi.dto;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.InvoicesSummary;
import lombok.Data;

@Data
public class InvoicesSummaryDTO {

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

	public static InvoicesSummaryDTO build(InvoicesSummary invoiceSummary) {

		InvoicesSummaryDTO result = new InvoicesSummaryDTO();
		BeanUtils.copyProperties(invoiceSummary, result);

		return result;
	}

}
