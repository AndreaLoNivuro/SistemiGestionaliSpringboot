package it.jac.sistemi.dto;


import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Item;
import lombok.Data;

@Data
public class ItemDTO {

	private int codItem;

	private String description;

	private float price;

	private int measure;

	private String type;

	private String producer;

	public static ItemDTO build(Item item) {

		ItemDTO result = new ItemDTO();
		BeanUtils.copyProperties(item, result);

		return result;
	}

}
