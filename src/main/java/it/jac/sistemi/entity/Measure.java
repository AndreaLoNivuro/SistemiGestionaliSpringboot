package it.jac.sistemi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "measure")
@Data
public class Measure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_measure")
	private int codMeasure;
	
	@Column(name = "description")
	private int description;
	
	@Column(name = "symbol")
	private int symbol;
	
	@Column(name = "note")
	private String note;

	@Override
	public String toString() {
		return "Measure [cod measure = " + codMeasure + ", description = " + description + ", symbol = " + symbol + "]";
	}

}
