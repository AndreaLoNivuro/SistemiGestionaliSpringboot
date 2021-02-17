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
import it.jac.sistemi.entity.InvoiceSummary;
import it.jac.sistemi.service.InvoiceSummaryService;

@RestController
@RequestMapping("/invoice/summary")
public class InvoiceSummaryController {
	
	private static Logger log = LoggerFactory.getLogger(InvoiceSummaryController.class);

	@Autowired
	private InvoiceSummaryService invoiceSummaryService;
	
	@PostMapping("/create")
	public Response<?> createInvoiceSummary(
			@RequestBody InvoiceSummary invoiceSummary
			) {
		
		log.info("Richiesta di create Invoice Summary.");

		return invoiceSummaryService.createInvoiceSummary(invoiceSummary);

	}
	
	@GetMapping(path="/findAll")
	public Response<?> findAllInvoicesSummary() {
		
		log.info("Richiesta di find all Invoice Summary.");
		
		return invoiceSummaryService.findAllInvoicesSummary();
		
	}
	
	@PostMapping(path = "/delete")
	public Response<?> deleteInvoiceSummary(
			@RequestBody InvoiceSummary invoiceSummary
			) {

		log.info("Richiesta di delete Invoice Summary.");

		return invoiceSummaryService.deleteInvoiceSummary(invoiceSummary);
		
	}

}
