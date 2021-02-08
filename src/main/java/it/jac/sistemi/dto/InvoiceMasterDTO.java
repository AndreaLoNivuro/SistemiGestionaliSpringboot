package it.jac.sistemi.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.InvoiceMaster;
import lombok.Data;

@Data
public class InvoiceMasterDTO {

	private String codInvoice;

	private String client;
	
	private String payment;

	private String orderNumber;

	private Date date;

	public static InvoiceMasterDTO build(InvoiceMaster invoice) {

		InvoiceMasterDTO result = new InvoiceMasterDTO();
		BeanUtils.copyProperties(invoice, result);

		return result;
	}

}
