package it.jac.sistemi.dto;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Client;
import lombok.Data;

@Data
public class ClientDTO {

	private int codClient;

	private int businessName;

	private int piva;

	private String fiscalCod;

	private String mail;

	private String tel;

	private String cel;

	private int city;

	private int address;

	private int province;

	private int cap;

	public static ClientDTO build(Client client) {

		ClientDTO result = new ClientDTO();
		BeanUtils.copyProperties(client, result);

		return result;
	}

}
