package it.jac.sistemi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import it.jac.sistemi.pk.PkInvoiceDetail;
import lombok.Data;

@Entity
@Table(name = "invoice_detail")
@IdClass(PkInvoiceDetail.class)
@Data
public  class InvoiceDetail {

	@Id
	@Column(name = "cod_invoice")
	private int codInvoice;

	@Id
	@Column(name = "cod_item")
	private int codItem;
	
	@Id
	@Column(name = "lot")
	private String lot;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "currency")
	private int currency;
	
	@Column(name = "vat")
	private int vat;
	
	@Column(name = "taxable")
	private int taxable;
	
	@Column(name = "discount")
	private int discount;
	
	@Column(name = "price")
	private int price;

	@Override
	public String toString() {
		return "Invoice detail [cod invoice = " + codInvoice + ", cod item = " + codItem + ", lot = " + lot + "]";
	}
	
}
