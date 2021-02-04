package it.jac.sistemi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "clients")
@Data
public class Client {

	@Id
	@Column(name = "cod_client")
	private String codClient;
	
	@Column(name = "business_name")
	private String businessName;
	
	@Column(name = "piva")
	private String piva;
	
	@Column(name = "fiscal_cod")
	private String fiscalCod;

	@Column(name = "mail")
	private String mail;
	
	@Column(name = "tel")
	private String tel;
	
	@Column(name = "cel")
	private String cel;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "province")
	private String province;
	
	@Column(name = "cap")
	private String cap;
	
	@Override
	public String toString() {
		return "Client [cod client = " + codClient + ", business name = " + businessName + ", mail = " + mail + "]";
	}

}
