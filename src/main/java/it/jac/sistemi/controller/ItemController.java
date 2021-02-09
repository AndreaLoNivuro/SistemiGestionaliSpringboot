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
import it.jac.sistemi.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {

	private static Logger log = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private ItemService itemService;
	
	@GetMapping(path="/findAll")
	public Response<?> findAllItems() {
		
		log.info("Richiesta di find all Item.");
		
		return itemService.findAllItems();
		
	}

	@PostMapping(path="/create")
	public Response<?> createItem(@RequestBody Item item) {
		
		log.info("Richiesta di create Item.");

		return itemService.createItem(item);

	}
	
	@PostMapping(path = "/delete")
	public Response<?> deleteItem(@RequestBody Item item) {

		log.info("Richiesta di delete Item.");

		return itemService.deleteItem(item);
		
	}

}
