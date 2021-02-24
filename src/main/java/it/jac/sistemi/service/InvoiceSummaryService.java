package it.jac.sistemi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.sistemi.dao.InvoicesSummaryRepository;
import it.jac.sistemi.dto.InvoiceSummaryDTO;
import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.InvoiceSummary;

@Service
public class InvoiceSummaryService {
	
	private static Logger log = LoggerFactory.getLogger(InvoiceSummaryService.class);

	@Autowired
	private InvoicesSummaryRepository invoicesSummaryRepository;

	public Response<InvoiceSummary> createInvoiceSummary(InvoiceSummary invoiceSummary, int codInvoice) {

		Response<InvoiceSummary> response = new Response<InvoiceSummary>();

		try {
			
			invoiceSummary.setCodInvoice(codInvoice);

			response.setResult(this.invoicesSummaryRepository.save(invoiceSummary));
			
			log.info("Invoice Summary creato/modificato.");

		} catch (Exception e) {

			response.setError("Invoice Summary non creato");
			
			log.info("Invoice Summary non creato/modificato.");

		}

		return response;
		
	}


	public Response<String> deleteInvoiceSummaryByCodInvoice(int codInvoice) {

		Response<String> response = new Response<String>();

		try {

			this.invoicesSummaryRepository.deleteById(codInvoice);

			response.setResult("Invoice Summary eliminato.");

		} catch (Exception e) {

			response.setError("Invoice Summary non eliminato.");

		}

		return response;

	}
	
	public Response<String> deleteInvoiceSummary(InvoiceSummary invoiceSummary) {

		Response<String> response = new Response<String>();

		try {

			this.invoicesSummaryRepository.delete(invoiceSummary);		

			response.setResult("Invoice Summary eliminato.");
			
			log.info("Invoice Summary eliminato.");

		} catch (Exception e) {

			response.setError("Invoice Summary non eliminato.");
			
			log.info("Invoice Summary non eliminato.");

		}

		return response;

	}


	public Response<List<InvoiceSummaryDTO>> findAllInvoicesSummary() {

		Response<List<InvoiceSummaryDTO>> response = new Response<List<InvoiceSummaryDTO>>();

		List<InvoiceSummaryDTO> result = new ArrayList<>();

		try {

			Iterator<InvoiceSummary> iterator = this.invoicesSummaryRepository.findAll().iterator();

			while(iterator.hasNext()) {

				InvoiceSummary invoiceSummary = iterator.next();
				result.add(InvoiceSummaryDTO.build(invoiceSummary));

			}

			response.setResult(result);
			
			log.info("Lista Invoices Summary.");

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");
			
			log.info("Invoices Summary non trovati.");

		}

		return response;

	}

}
