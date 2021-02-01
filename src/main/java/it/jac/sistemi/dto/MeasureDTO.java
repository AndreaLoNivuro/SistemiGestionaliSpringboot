package it.jac.sistemi.dto;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Measure;
import lombok.Data;

@Data
public class MeasureDTO {

	private int codMeasure;

	private int description;

	private int symbol;

	private String note;

	public static MeasureDTO build(Measure measure) {

		MeasureDTO result = new MeasureDTO();
		BeanUtils.copyProperties(measure, result);

		return result;
	}

}
