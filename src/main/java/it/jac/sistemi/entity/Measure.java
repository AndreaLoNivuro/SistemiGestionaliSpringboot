package it.jac.sistemi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "measures")
@Data
public class Measure {

	@Id
	@Column(name = "cod_measure")
	private String codMeasure;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "symbol", length = 3)
	private String symbol;
	
	@Column(name = "note")
	private String note;

	@Override
	public String toString() {
		return "Measure [cod measure = " + codMeasure + ", description = " + description + ", symbol = " + symbol + "]";
	}

}
