package it.jac.sistemi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.Payment;
import it.jac.sistemi.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private static Logger log = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/create")
	public Response<?> createPayment(
			@RequestBody Payment payment
			) {
		
		log.info("Richiesta di create Payment.");

		return paymentService.createPayment(payment);

	}
	
	@GetMapping(path="/findAll")
	public Response<?> findAllPayments() {
		
		log.info("Richiesta di find all Payments.");
		
		return paymentService.findAllPayments();
		
	}
	
	@PostMapping(path = "/delete")
	public Response<?> deletePayment(
			@RequestBody Payment payment
			) {

		log.info("Richiesta di delete Payment.");

		return paymentService.deletePayment(payment);
		
	}

}
