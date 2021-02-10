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
import it.jac.sistemi.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	private static Logger log = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private ClientService clientService;
	
	@PostMapping("/create")
	public Response<?> createClient(
			@RequestBody Client client
			) {
		
		log.info("richiesta di create.");

		return clientService.createClient(client);

	}
	
	@GetMapping(path="/findAll")
	public Response<?> findAllClients() {
		
		log.info("richiesta di find all.");
		
		return clientService.findAllClients();
		
	}
	
	@PostMapping(path = "/delete")
	public Response<?> deleteClient(
			@RequestBody Client client
			) {

		log.info("Richiesta di delete.");

		return clientService.deleteClient(client);
		
	}

}
