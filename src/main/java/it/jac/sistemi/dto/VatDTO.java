package it.jac.sistemi.dto;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Vat;
import lombok.Data;

@Data
public class VatDTO {

	private String codVat;

	private float vat;

	private String note;

	public static VatDTO build(Vat vat) {

		VatDTO result = new VatDTO();
		BeanUtils.copyProperties(vat, result);

		return result;
	}

}
