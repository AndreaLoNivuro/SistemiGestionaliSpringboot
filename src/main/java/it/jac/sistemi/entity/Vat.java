package it.jac.sistemi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "vat")
@Data
public class Vat {

	@Id
	@Column(name = "cod_vat")
	private String codVat;
	
	@Column(name = "vat")
	private float vat;

	@Column(name = "note")
	private String note;

	@Override
	public String toString() {
		return "Vat [cod vat = " + codVat + ", vat = " + vat + "]";
	}

}
