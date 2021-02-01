package it.jac.sistemi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "item")
@Data
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_item")
	private int codItem;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "measure")
	private int measure;

	@Column(name = "type")
	private String type;
	
	@Column(name = "producer")
	private String producer;
	
	@Override
	public String toString() {
		return "Item [cod item = " + codItem + ", description = " + description + ", price = " + price + "]";
	}

}
