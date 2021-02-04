package it.jac.sistemi.dto;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.InvoicesDetail;
import lombok.Data;

@Data
public class InvoicesDetailDTO {

	private String codInvoice;

	private String codItem;

	private String lot;

	private float quantity;

	private float vat;

	private float taxable;

	private float discount;

	private float price;
	
	private Date expiry;

	public static InvoicesDetailDTO build(InvoicesDetail invoiceDetail) {

		InvoicesDetailDTO result = new InvoicesDetailDTO();
		BeanUtils.copyProperties(invoiceDetail, result);

		return result;
	}

}
