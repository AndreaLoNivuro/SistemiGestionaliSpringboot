package it.jac.sistemi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "items")
@Data
public class Item {

	@Id
	@Column(name = "cod_item")
	private String codItem;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "measure")
	private int measure;

	@Column(name = "type")
	private String type;
	//prodotto o servizio
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "discount")
	private String discount;
	
	@Column(name = "vat")
	private float vat;
	
	@Override
	public String toString() {
		return "Item [cod item = " + codItem + ", description = " + description + ", price = " + price + "]";
	}

}
