package it.jac.sistemi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "invoices_master")
@Data
public class InvoiceMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_invoice")
	private int codInvoice;
	
	@Column(name = "invoice_number")
	private String invoiceNumber;
	
	@Column(name = "client")
	private String client;
	
	@Column(name = "payment")
	private String payment;
	
	@Column(name = "order_number")
	private String orderNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date;
	
	@Override
	public String toString() {
		return "Invoice master [cod invoice = " + codInvoice + ", client = " + client + ", cod order = " + orderNumber + "]";
	}

}
