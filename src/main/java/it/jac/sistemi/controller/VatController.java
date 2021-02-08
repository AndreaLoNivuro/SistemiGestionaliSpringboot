package it.jac.sistemi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.sistemi.service.VatService;

@RestController
@RequestMapping("/vat")
public class VatController {
	
	private static Logger log = LoggerFactory.getLogger(VatController.class);

	@Autowired
	private VatService vatService;

}
