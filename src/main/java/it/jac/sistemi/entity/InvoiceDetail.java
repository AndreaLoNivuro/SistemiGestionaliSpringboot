package it.jac.sistemi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import it.jac.sistemi.pk.PkInvoiceDetail;
import lombok.Data;

@Entity
@Table(name = "invoices_detail")
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
	private float quantity;
	
	@Column(name = "currency")
	private int currency;
	
	@Column(name = "vat")
	private float vat;
	
	@Column(name = "taxable")
	private float taxable;
	
	@Column(name = "discount")
	private float discount;
	
	@Column(name = "price")
	private float price;

	@Override
	public String toString() {
		return "Invoice detail [cod invoice = " + codInvoice + ", cod item = " + codItem + ", lot = " + lot + "]";
	}
	
}
