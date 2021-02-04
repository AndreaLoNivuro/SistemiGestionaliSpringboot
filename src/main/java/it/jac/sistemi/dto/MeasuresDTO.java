package it.jac.sistemi.dto;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Measures;
import lombok.Data;

@Data
public class MeasuresDTO {

	private String codMeasure;

	private String description;

	private char symbol;

	private String note;

	public static MeasuresDTO build(Measures measure) {

		MeasuresDTO result = new MeasuresDTO();
		BeanUtils.copyProperties(measure, result);

		return result;
	}

}
