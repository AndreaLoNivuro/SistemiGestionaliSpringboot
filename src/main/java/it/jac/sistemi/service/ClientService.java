package it.jac.sistemi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.sistemi.dao.ClientsRepository;
import it.jac.sistemi.dto.ClientDTO;
import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.Client;

@Service
public class ClientService {
	
	private static Logger log = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	private ClientsRepository clientsRepository;

	public Response<Client> createClient(Client client) {

		Response<Client> response = new Response<Client>();

		try {

			response.setResult(this.clientsRepository.save(client));

		} catch (Exception e) {

			response.setError("Client non creato");

		}

		return response;

	}


	public Response<String> deleteClient(Client client) {

		Response<String> response = new Response<String>();

		try {

			this.clientsRepository.delete(client);			

			response.setResult("Client eliminato.");

		} catch (Exception e) {

			response.setError("Client non eliminato.");

		}

		return response;

	}


	public Response<List<ClientDTO>> findAllClients() {

		Response<List<ClientDTO>> response = new Response<List<ClientDTO>>();

		List<ClientDTO> result = new ArrayList<>();

		try {

			Iterator<Client> iterator = this.clientsRepository.findAll().iterator();

			while(iterator.hasNext()) {

				Client client = iterator.next();
				result.add(ClientDTO.build(client));

			}

			response.setResult(result);

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}

}
