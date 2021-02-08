package it.jac.sistemi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.sistemi.service.InvoiceDetailService;

@RestController
@RequestMapping("/invoice/detail")
public class InvoiceDetailController {
	
	private static Logger log = LoggerFactory.getLogger(InvoiceDetailController.class);

	@Autowired
	private InvoiceDetailService invoiceDetailService;

}
