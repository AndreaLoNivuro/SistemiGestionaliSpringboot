package it.jac.sistemi.dto;
import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.InvoiceDetail;
import lombok.Data;

@Data
public class InvoiceDetailDTO {

	private int codInvoice;

	private int codItem;

	private String lot;

	private float quantity;

	private int currency;

	private float vat;

	private float taxable;

	private float discount;

	private float price;

	public static InvoiceDetailDTO build(InvoiceDetail invoiceDetail) {

		InvoiceDetailDTO result = new InvoiceDetailDTO();
		BeanUtils.copyProperties(invoiceDetail, result);

		return result;
	}

}
