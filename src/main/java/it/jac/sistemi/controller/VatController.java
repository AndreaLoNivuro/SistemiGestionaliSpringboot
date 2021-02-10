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
import it.jac.sistemi.entity.Item;
import it.jac.sistemi.entity.Vat;
import it.jac.sistemi.service.VatService;

@RestController
@RequestMapping("/vat")
public class VatController {
	
	private static Logger log = LoggerFactory.getLogger(VatController.class);

	@Autowired
	private VatService vatService;

	@PostMapping("/create")
	public Response<?> createVat(
			@RequestBody Vat vat
			) {
		
		log.info("richiesta di create.");

		return vatService.createVat(vat);

	}
	
	@GetMapping(path="/findAll")
	public Response<?> findAllVats() {
		
		log.info("richiesta di find all.");
		
		return vatService.findAllVat();
		
	}
	
	@PostMapping(path = "/delete")
	public Response<?> deleteVat(
			@RequestBody Vat vat
			) {

		log.info("Richiesta di delete.");

		return vatService.deleteVat(vat);
		
	}

}
