package it.jac.sistemi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "order")
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_order")
	private int codOrder;

	@Column(name = "cod_client")
	private int codClient;

	@Override
	public String toString() {
		return "Order [cod client = " + codClient + ", cod Order = " + codOrder + "]";
	}
	
}
