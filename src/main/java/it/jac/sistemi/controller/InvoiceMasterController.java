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
import it.jac.sistemi.entity.Client;
import it.jac.sistemi.entity.InvoiceMaster;
import it.jac.sistemi.service.InvoiceMasterService;

@RestController
@RequestMapping("/invoice/master")
public class InvoiceMasterController {
	
	private static Logger log = LoggerFactory.getLogger(InvoiceMasterController.class);

	@Autowired
	private InvoiceMasterService invoiceMasterService;
	
	@PostMapping("/create")
	public Response<?> createClient(
			@RequestBody InvoiceMaster invoiceMaster
			) {
		
		log.info("richiesta di create Invoice Master.");

		return invoiceMasterService.createInvoiceMaster(invoiceMaster);

	}
	
	@GetMapping(path="/findAll")
	public Response<?> findAllClients() {
		
		log.info("richiesta di find all Invoice Master.");
		
		return invoiceMasterService.findAllInvoicesMaster();
		
	}
	
	@PostMapping(path = "/delete")
	public Response<?> deleteInvoiceMaster(
			@RequestBody InvoiceMaster invoiceMaster
			) {

		log.info("Richiesta di delete Invoice Master.");

		return invoiceMasterService.deleteInvoiceMaster(invoiceMaster);
		
	}

}
