package it.jac.sistemi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.jac.sistemi.pk.PkInvoiceDetail;
import lombok.Data;

@Entity
@Table(name = "invoices_detail")
@IdClass(PkInvoiceDetail.class)
@Data
public  class InvoiceDetail {

	@Id
	@Column(name = "cod_invoice")
	private String codInvoice;
	
	@Id
	@Column(name = "line")
	private int line;

	@Id
	@Column(name = "cod_item")
	private String codItem;
	
	@Column(name = "description")
	private float description;
	
	@Column(name = "measure")
	private float measure;
	
	@Column(name = "quantity")
	private float quantity;
	
	@Column(name = "lot")
	private int lot;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiry")
	private Date expiry;
	
	@Column(name = "unit_price")
	private float unitPrice;
	
	@Column(name = "discount")
	private float discount;
	
	@Column(name = "total_discount")
	private float totalDiscount;
	
	@Column(name = "taxable")
	private float taxable;
	
	@Column(name = "cod_vat")
	private float codVat;
	
	@Column(name = "total_line")
	private float totalLine;

	@Override
	public String toString() {
		return "Invoice detail [cod invoice = " + codInvoice + ", cod item = " + codItem + ", lot = " + lot + "]";
	}
	
}
