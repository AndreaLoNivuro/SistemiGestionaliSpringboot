package it.jac.sistemi.dto;

import org.springframework.beans.BeanUtils;

import it.jac.sistemi.entity.Order;
import lombok.Data;

@Data
public class OrderDTO {

	private int codOrder;

	private int codClient;

	public static OrderDTO build(Order order) {

		OrderDTO result = new OrderDTO();
		BeanUtils.copyProperties(order, result);

		return result;

	}
}
