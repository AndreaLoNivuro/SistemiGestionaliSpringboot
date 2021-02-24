package it.jac.sistemi.dto;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.InvoiceDetail;
import lombok.Data;

@Data
public class InvoiceDetailDTO {

	private int codInvoice;

	private int line;

	private String codItem;

	private String description;

	private String measure;

	private float quantity;

	private String lot;

	private Date expiry;

	private float unitPrice;

	private String discount;

	private float totalDiscount;

	private float taxable;

	private String codVat;
	
	private float totalVat;

	private float totalLine;

	public static InvoiceDetailDTO build(InvoiceDetail invoiceDetail) {

		InvoiceDetailDTO result = new InvoiceDetailDTO();
		BeanUtils.copyProperties(invoiceDetail, result);

		return result;
	}

}
