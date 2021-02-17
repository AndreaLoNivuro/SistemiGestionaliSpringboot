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
	
	@Autowired
	private InvoiceDetailService invoiceDetailService;
	
	@Autowired
	private InvoiceSummaryService invoiceSummaryService;

	public Response<InvoiceMaster> createInvoiceMaster(InvoiceMaster invoiceMaster) {

		Response<InvoiceMaster> response = new Response<InvoiceMaster>();

		try {

			response.setResult(this.invoicesMasterRepository.save(invoiceMaster));

		} catch (Exception e) {

			response.setError("InvoiceMaster non creato");

		}

		return response;

	}


	public Response<String> deleteInvoiceMaster(InvoiceMaster invoiceMaster) {

		Response<String> response = new Response<String>();

		try {

			this.invoicesMasterRepository.delete(invoiceMaster);
			
			this.invoiceDetailService.deleteInvoiceDetailByCodInvoice(invoiceMaster.getCodInvoice());
			
			this.invoiceSummaryService.deleteInvoiceSummaryByCodInvoice(invoiceMaster.getCodInvoice());

			response.setResult("Invoice eliminata.");

		} catch (Exception e) {

			response.setError("Invoice non eliminata.");

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

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}

}
