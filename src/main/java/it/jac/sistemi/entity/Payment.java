package it.jac.sistemi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "payments")
@Data
public class Payment {

	@Id
	@Column(name = "cod_category")
	private String codPayment;
	
	@Column(name = "description")
	private String description;

	@Column(name = "note")
	private String note;

	@Override
	public String toString() {
		return "Category [cod category = " + codPayment + ", description = " + description + "]";
	}

}
