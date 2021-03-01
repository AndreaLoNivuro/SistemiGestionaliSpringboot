package it.jac.sistemi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.sistemi.dao.InvoicesMasterRepository;
import it.jac.sistemi.dto.InvoiceMasterDTO;
import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.InvoiceMaster;

@Service
public class InvoiceMasterService {
	
	private static Logger log = LoggerFactory.getLogger(InvoiceMasterService.class);

	@Autowired
	private InvoicesMasterRepository invoicesMasterRepository;

	public Response<InvoiceMaster> createInvoiceMaster(InvoiceMaster invoiceMaster) {

		Response<InvoiceMaster> response = new Response<InvoiceMaster>();

		try {
			
			log.info(invoiceMaster.toString());

			response.setResult(this.invoicesMasterRepository.save(invoiceMaster));
			
			log.info("Invoice Master creata/modificata.");

		} catch (Exception e) {

			response.setError("InvoiceMaster non creato");
			
			log.info("Invoice Master non creata/modificata.");

		}

		return response;

	}

	public Response<List<InvoiceMasterDTO>> findAllInvoicesMaster() {

		Response<List<InvoiceMasterDTO>> response = new Response<List<InvoiceMasterDTO>>();

		List<InvoiceMasterDTO> result = new ArrayList<>();

		try {

			Iterator<InvoiceMaster> iterator = this.invoicesMasterRepository.findAll().iterator();

			while(iterator.hasNext()) {

				InvoiceMaster invoiceMaster = iterator.next();
				result.add(InvoiceMasterDTO.build(invoiceMaster));

			}

			response.setResult(result);
			
			log.info("Lista Invoices Master.");

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");
			
			log.info("Invoices Master non trovate.");

		}

		return response;

	}


	public Response<String> deleteInvoiceMasterByCodInvoice(int codInvoice) {

		Response<String> response = new Response<String>();

		try {

			this.invoicesMasterRepository.deleteById(codInvoice);

			response.setResult("Invoice Master eliminata.");
			
			log.info("Invoice Master eliminata.");

		} catch (Exception e) {

			response.setError("Invoice non eliminata.");
			
			log.info("Invoice non eliminata.");

		}

		return response;

	}


}
