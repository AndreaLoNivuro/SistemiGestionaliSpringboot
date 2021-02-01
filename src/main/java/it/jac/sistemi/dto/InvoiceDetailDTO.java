package it.jac.sistemi.dto;
import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.InvoiceDetail;
import lombok.Data;

@Data
public class InvoiceDetailDTO {

	private int codInvoice;

	private int codItem;

	private int lot;

	private int quantity;

	private int currency;

	private int vat;

	private int taxable;

	private int discount;

	private int price;

	public static InvoiceDetailDTO build(InvoiceDetail invoiceDetail) {

		InvoiceDetailDTO result = new InvoiceDetailDTO();
		BeanUtils.copyProperties(invoiceDetail, result);

		return result;
	}

}
