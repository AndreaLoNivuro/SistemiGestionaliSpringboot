package it.jac.sistemi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {

	@Id
	@Column(name = "cod_category")
	private String codCategory;
	
	@Column(name = "description")
	private String description;

	@Column(name = "note")
	private String note;

	@Override
	public String toString() {
		return "Category [cod category = " + codCategory + ", description = " + description + "]";
	}

}
