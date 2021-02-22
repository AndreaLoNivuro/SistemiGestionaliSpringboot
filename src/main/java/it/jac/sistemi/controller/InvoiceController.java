package it.jac.sistemi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.sistemi.dto.Response;
import it.jac.sistemi.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	
	private static Logger log = LoggerFactory.getLogger(InvoiceMasterController.class);

	@Autowired
	private InvoiceService invoiceService;
	
	@PostMapping(path = "/delete")
	public Response<?> deleteInvoice(
			@RequestBody int codInvoice
			) {

		log.info("Richiesta di delete Invoice.");

		return invoiceService.deleteInvoice(codInvoice);
		
	}

}
