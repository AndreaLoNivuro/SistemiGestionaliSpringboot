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
import it.jac.sistemi.entity.InvoiceDetail;
import it.jac.sistemi.service.InvoiceDetailService;

@RestController
@RequestMapping("/invoice/detail")
public class InvoiceDetailController {
	
	private static Logger log = LoggerFactory.getLogger(InvoiceDetailController.class);

	@Autowired
	private InvoiceDetailService invoiceDetailService;
	
	@PostMapping("/create")
	public Response<?> createInvoiceDetail(
			@RequestBody InvoiceDetail invoiceDetail
			) {
		
		log.info("Richiesta di create Invoice Detail.");

		return invoiceDetailService.createInvoiceDetail(invoiceDetail);

	}
	
	@GetMapping(path="/findAll")
	public Response<?> findAllInvoicesDetail() {
		
		log.info("Richiesta di find all Invoice Detail.");
		
		return invoiceDetailService.findAllInvoicesDetail();
		
	}
	
	@PostMapping(path = "/delete")
	public Response<?> deleteInvoiceDetail(
			@RequestBody InvoiceDetail invoiceDetail
			) {

		log.info("Richiesta di delete Invoice Detail.");

		return invoiceDetailService.deleteInvoiceDetail(invoiceDetail);
		
	}

}
