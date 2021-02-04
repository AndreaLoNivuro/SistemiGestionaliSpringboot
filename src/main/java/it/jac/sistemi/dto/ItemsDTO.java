package it.jac.sistemi.dto;


import javax.persistence.Column;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Items;
import lombok.Data;

@Data
public class ItemsDTO {

	private String codItem;

	private String description;

	private float price;

	private int measure;

	private String type;

	private String category;
	
	private String discount;
	
	private float vat;

	public static ItemsDTO build(Items item) {

		ItemsDTO result = new ItemsDTO();
		BeanUtils.copyProperties(item, result);

		return result;
	}

}
