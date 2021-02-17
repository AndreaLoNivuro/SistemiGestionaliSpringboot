package it.jac.sistemi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "invoices_summary")
@Data
public  class InvoiceSummary {

	@Id
	@Column(name = "cod_invoice")
	private String codInvoice;

	@Column(name = "total_amount")
	private float totalAmount;
	//netto
	
	@Column(name = "total_products")
	private float totalProducts;
	
	@Column(name = "total_services")
	private float totalServices;
	
	@Column(name = "total_discount")
	private float totalDiscount;
	
	@Column(name = "tail_discount")
	private float tailDiscount;
	
	@Column(name = "total_tile_discount")
	private float totalTileDiscount;
	
	@Column(name = "total_line_discount")
	private float totalLineDiscount;
	
	@Column(name = "total_vat")
	private float totalVat;
	
	@Column(name = "taxable")
	private float taxable;
	//imponibile

	@Override
	public String toString() {
		return "Invoice summary [cod invoice = " + codInvoice + ", total amount = " + totalAmount + "]";
	}
	
}
