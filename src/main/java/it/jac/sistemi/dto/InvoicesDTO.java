package it.jac.sistemi.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Invoices;
import lombok.Data;

@Data
public class InvoicesDTO {

	private String codInvoice;

	private String client;
	
	private String payment;

	private String order;

	private Date date;

	public static InvoicesDTO build(Invoices invoice) {

		InvoicesDTO result = new InvoicesDTO();
		BeanUtils.copyProperties(invoice, result);

		return result;
	}

}
