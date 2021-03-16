package it.jac.sistemi.dto;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Payment;
import lombok.Data;

@Data
public class PaymentDTO {

	private String codPayment;

	private String description;

	private String note;

	public static PaymentDTO build(Payment payment) {

		PaymentDTO result = new PaymentDTO();
		BeanUtils.copyProperties(payment, result);

		return result;
	}

}
