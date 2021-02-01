package it.jac.sistemi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "client")
@Data
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_client")
	private int codClient;
	
	@Column(name = "business_name")
	private int businessName;
	
	@Column(name = "piva")
	private int piva;
	
	@Column(name = "fiscal_cod")
	private String fiscalCod;

	@Column(name = "mail")
	private String mail;
	
	@Column(name = "tel")
	private String tel;
	
	@Column(name = "cel")
	private String cel;
	
	@Column(name = "city")
	private int city;
	
	@Column(name = "address")
	private int address;
	
	@Column(name = "province")
	private int province;
	
	@Column(name = "cap")
	private int cap;
	
	@Override
	public String toString() {
		return "Client [cod client = " + codClient + ", business name = " + businessName + ", mail = " + mail + "]";
	}

}
