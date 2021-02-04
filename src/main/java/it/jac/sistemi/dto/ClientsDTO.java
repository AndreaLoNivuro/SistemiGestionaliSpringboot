package it.jac.sistemi.dto;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Clients;
import lombok.Data;

@Data
public class ClientsDTO {

	private String codClient;

	private String businessName;

	private String piva;

	private String fiscalCod;

	private String mail;

	private String tel;

	private String cel;

	private String city;

	private String address;

	private String province;

	private String cap;

	public static ClientsDTO build(Clients client) {

		ClientsDTO result = new ClientsDTO();
		BeanUtils.copyProperties(client, result);

		return result;
	}

}
