package it.jac.sistemi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.sistemi.dao.ItemsRepository;
import it.jac.sistemi.dto.ItemDTO;
import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.Item;

@Service
public class ItemService {

	private static Logger log = LoggerFactory.getLogger(ItemService.class);
	
	@Autowired
	private ItemsRepository itemsRepository;
	
	
	public Response<Item> createItem(Item item) {

		Response<Item> response = new Response<Item>();

		try {

			response.setResult(this.itemsRepository.save(item));
			
			log.info("Item creato/modificato.");

		} catch (Exception e) {

			response.setError("Item non creato");
			
			log.info("Item non creato/modificato.");

		}

		return response;

	}


	public Response<String> deleteItem(Item item) {

		Response<String> response = new Response<String>();
		
		try {

			this.itemsRepository.delete(item);			

			response.setResult("Item eliminato.");
			
			log.info("Item eliminato.");

		} catch (Exception e) {

			response.setError("Item non eliminato.");
			
			log.info("Item non eliminato.");

		}

		return response;

	}


	public Response<List<ItemDTO>> findAllItems() {

		Response<List<ItemDTO>> response = new Response<List<ItemDTO>>();

		List<ItemDTO> result = new ArrayList<>();

		try {

			Iterator<Item> iterator = this.itemsRepository.findAll().iterator();

			while(iterator.hasNext()) {

				Item item = iterator.next();
				result.add(ItemDTO.build(item));

			}

			response.setResult(result);
			
			log.info("Lista Items.");

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");
			
			log.info("Items non trovati.");

		}

		return response;

	}
	
}
