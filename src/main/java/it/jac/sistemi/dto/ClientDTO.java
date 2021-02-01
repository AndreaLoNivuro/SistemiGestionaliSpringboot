package it.jac.sistemi.dto;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Client;
import lombok.Data;

@Data
public class ClientDTO {

	private int codClient;

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

	public static ClientDTO build(Client client) {

		ClientDTO result = new ClientDTO();
		BeanUtils.copyProperties(client, result);

		return result;
	}

}
