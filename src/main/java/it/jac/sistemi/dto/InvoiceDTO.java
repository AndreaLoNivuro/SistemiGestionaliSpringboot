package it.jac.sistemi.dto;

import java.util.List;

import it.jac.sistemi.entity.InvoiceDetail;
import it.jac.sistemi.entity.InvoiceMaster;
import it.jac.sistemi.entity.InvoiceSummary;
import lombok.Data;

@Data
public class InvoiceDTO {
	InvoiceMaster invoiceMaster;
	List<InvoiceDetail> invoiceDetailList;
	InvoiceSummary invoiceSummary;

}
