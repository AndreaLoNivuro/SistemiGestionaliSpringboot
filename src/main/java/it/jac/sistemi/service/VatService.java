package it.jac.sistemi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.sistemi.dao.VatRepository;
import it.jac.sistemi.dto.VatDTO;
import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.Vat;

@Service
public class VatService {

	private static Logger log = LoggerFactory.getLogger(VatService.class);

	@Autowired
	private VatRepository vatRepository;

	public Response<Vat> createVat(Vat vat) {

		Response<Vat> response = new Response<Vat>();

		try {

			response.setResult(this.vatRepository.save(vat));
			
			log.info("Vat creata/modificata.");

		} catch (Exception e) {

			response.setError("Vat non creato");
			
			log.info("Vat non creata/modificata.");

		}

		return response;

	}


	public Response<String> deleteVat(Vat vat) {

		Response<String> response = new Response<String>();

		try {

			this.vatRepository.delete(vat);			

			response.setResult("Vat eliminata.");
			
			log.info("Vat eliminata.");

		} catch (Exception e) {

			response.setError("Vat non eliminata.");
			
			log.info("Vat non eliminata.");

		}

		return response;

	}


	public Response<List<VatDTO>> findAllVat() {

		Response<List<VatDTO>> response = new Response<List<VatDTO>>();

		List<VatDTO> result = new ArrayList<>();

		try {

			Iterator<Vat> iterator = this.vatRepository.findAll().iterator();

			while(iterator.hasNext()) {

				Vat vat = iterator.next();
				result.add(VatDTO.build(vat));

			}

			response.setResult(result);
			
			log.info("Lista Vats.");

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");
			
			log.info("Vats non trovate.");

		}

		return response;

	}

}
