package it.jac.sistemi.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Invoice;
import lombok.Data;

@Data
public class InvoiceDTO {

	private int codInvoice;

	private int client;

	private int codOrder;

	private Date date;

	public static InvoiceDTO build(Invoice invoice) {

		InvoiceDTO result = new InvoiceDTO();
		BeanUtils.copyProperties(invoice, result);

		return result;
	}

}
