package it.jac.sistemi.dto;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Item;
import lombok.Data;

@Data
public class ItemDTO {

	private String codItem;

	private String description;

	private float price;

	private String measure;

	private String type;

	private String category;
	
	private float discount;
	
	private String vat;

	public static ItemDTO build(Item item) {

		ItemDTO result = new ItemDTO();
		BeanUtils.copyProperties(item, result);

		return result;
	}

}
