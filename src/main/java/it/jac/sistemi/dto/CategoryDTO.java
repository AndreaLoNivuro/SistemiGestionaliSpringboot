package it.jac.sistemi.dto;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Category;
import lombok.Data;

@Data
public class CategoryDTO {

	private String codCategory;

	private String description;

	private String note;

	public static CategoryDTO build(Category category) {

		CategoryDTO result = new CategoryDTO();
		BeanUtils.copyProperties(category, result);

		return result;
	}

}
