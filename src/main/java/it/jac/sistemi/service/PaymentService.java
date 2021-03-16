package it.jac.sistemi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.sistemi.dao.PaymentsRepository;
import it.jac.sistemi.dto.PaymentDTO;
import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.Payment;

@Service
public class PaymentService {

	private static Logger log = LoggerFactory.getLogger(PaymentService.class);

	@Autowired
	private PaymentsRepository paymentsRepository;

	public Response<Payment> createPayment(Payment payment) {

		Response<Payment> response = new Response<Payment>();

		try {

			response.setResult(this.paymentsRepository.save(payment));
			
			log.info("Payment creato/modificato.");

		} catch (Exception e) {

			response.setError("Payment non creato.");
			
			log.info("Payment non creato/modificato.");

		}

		return response;

	}


	public Response<String> deletePayment(Payment payment) {

		Response<String> response = new Response<String>();

		try {

			this.paymentsRepository.delete(payment);			

			response.setResult("Payment eliminato.");
			
			log.info("Payment eliminato.");

		} catch (Exception e) {

			response.setError("Payment non eliminato.");

			log.info("Payment non eliminato.");
			
		}

		return response;

	}


	public Response<List<PaymentDTO>> findAllPayments() {

		Response<List<PaymentDTO>> response = new Response<List<PaymentDTO>>();

		List<PaymentDTO> result = new ArrayList<>();

		try {

			Iterator<Payment> iterator = this.paymentsRepository.findAll().iterator();

			while(iterator.hasNext()) {

				Payment payment = iterator.next();
				result.add(PaymentDTO.build(payment));

			}

			response.setResult(result);
			
			log.info("Lista Payments.");

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");
			
			log.info("Payments non trovati.");

		}

		return response;

	}

}
