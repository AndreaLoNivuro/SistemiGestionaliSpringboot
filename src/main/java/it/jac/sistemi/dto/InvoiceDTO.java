package it.jac.sistemi.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Invoice;
import lombok.Data;

@Data
public class InvoiceDTO {

	private String codInvoice;

	private String client;
	
	private String payment;

	private String order;

	private Date date;

	public static InvoiceDTO build(Invoice invoice) {

		InvoiceDTO result = new InvoiceDTO();
		BeanUtils.copyProperties(invoice, result);

		return result;
	}

}
