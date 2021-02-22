package it.jac.sistemi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.sistemi.dao.InvoicesDetailRepository;
import it.jac.sistemi.dao.InvoicesMasterRepository;
import it.jac.sistemi.dao.InvoicesSummaryRepository;
import it.jac.sistemi.dto.Response;

@Service
public class InvoiceService {
	
	private static Logger log = LoggerFactory.getLogger(InvoiceService.class);

	@Autowired
	private InvoicesMasterRepository invoicesMasterRepository;
	
	@Autowired
	private InvoicesDetailRepository invoicesDetailRepository;
	
	@Autowired
	private InvoicesSummaryRepository invoicesSummaryRepository;

	public Response<String> deleteInvoice(int codInvoice) {
		Response<String> response = new Response<String>();

		try {

			this.invoicesMasterRepository.deleteById(codInvoice);
			
			this.invoicesDetailRepository.deleteByCodInvoice(codInvoice);
			
			this.invoicesSummaryRepository.deleteById(codInvoice);

			response.setResult("Invoice eliminata.");
			
			log.info("Invoice eliminata.");

		} catch (Exception e) {

			response.setError("Invoice non eliminata.");
			
			log.info("Invoice non eliminata.");

		}

		return response;
	}

}
