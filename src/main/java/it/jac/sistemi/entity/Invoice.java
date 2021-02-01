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
@Table(name = "invoice")
@Data
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_invoice")
	private int codInvoice;
	
	@Column(name = "client")
	private int client;
	
	@Column(name = "cod_order")
	private int codOrder;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date;
	
	@Override
	public String toString() {
		return "Invoice [cod invoice = " + codInvoice + ", client = " + client + ", cod order = " + codOrder + "]";
	}

}
