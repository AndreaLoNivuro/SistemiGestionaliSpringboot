package it.jac.sistemi.dto;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.InvoiceDetail;
import lombok.Data;

@Data
public class InvoiceDetailDTO {

	private String codInvoice;

	private String codItem;

	private String lot;

	private float quantity;

	private float vat;

	private float taxable;

	private float discount;

	private float price;
	
	private Date expiry;

	public static InvoiceDetailDTO build(InvoiceDetail invoiceDetail) {

		InvoiceDetailDTO result = new InvoiceDetailDTO();
		BeanUtils.copyProperties(invoiceDetail, result);

		return result;
	}

}
