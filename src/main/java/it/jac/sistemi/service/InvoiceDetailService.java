package it.jac.sistemi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.sistemi.dao.InvoicesDetailRepository;
import it.jac.sistemi.dto.InvoiceDetailDTO;
import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.InvoiceDetail;

@Service
public class InvoiceDetailService {

	private static Logger log = LoggerFactory.getLogger(InvoiceDetailService.class);

	@Autowired
	private InvoicesDetailRepository invoicesDetailRepository;

	public Response<List<InvoiceDetail>> createInvoiceDetail(List<InvoiceDetail> invoiceDetailList, int codInvoice) {

		Response<List<InvoiceDetail>> response = new Response<List<InvoiceDetail>>();

		List<InvoiceDetail> invoiceDetailSaved = new ArrayList<InvoiceDetail>();
		
		try {
			
			deleteInvoiceDetailByCodInvoice(codInvoice);
			
			for (InvoiceDetail invoiceDetail : invoiceDetailList) {
				invoiceDetail.setCodInvoice(codInvoice);
				invoiceDetail.setLine(invoiceDetailList.hashCode());
				invoiceDetailSaved.add(this.invoicesDetailRepository.save(invoiceDetail));
			}
			
			response.setResult(invoiceDetailSaved);
			
			log.info("Invoice Detail creato/modificato.");

		} catch (Exception e) {

			response.setError("Invoice Detail non creato");
			
			log.info("Invoice Detail non creato/modificato.");

		}

		return response;

	}


	public Response<String> deleteInvoiceDetailByCodInvoice(int codInvoice) {

		log.info("Richiesta di delete invoice Detail by codInvoice.");
		Response<String> response = new Response<String>();

		try {
			
			if (this.invoicesDetailRepository.findByCodInvoice(codInvoice)!= null) {
				for (InvoiceDetail invoiceDetail: this.invoicesDetailRepository.findByCodInvoice(codInvoice)) {
					this.invoicesDetailRepository.delete(invoiceDetail);
				}
			} else {
				log.info("Nessuna riga.");
			}	

			response.setResult("Invoice Detail eliminato.");
			log.info("Invoice Detail eliminato.");

		} catch (Exception e) {

			response.setError("Invoice Detail non eliminato.");
			log.info("Invoice Detail non eliminato.");

		}

		return response;

	}
	
	public Response<String> deleteInvoiceDetail(InvoiceDetail invoiceDetail) {

		Response<String> response = new Response<String>();

		try {

			this.invoicesDetailRepository.delete(invoiceDetail);			

			response.setResult("Invoice Detail eliminato.");
			
			log.info("Invoice Detail eliminato.");

		} catch (Exception e) {

			response.setError("Invoice Detail non eliminato.");
			
			log.info("Invoice Detail non eliminato.");

		}

		return response;

	}


	public Response<List<InvoiceDetailDTO>> findAllInvoicesDetail() {

		Response<List<InvoiceDetailDTO>> response = new Response<List<InvoiceDetailDTO>>();

		List<InvoiceDetailDTO> result = new ArrayList<>();

		try {

			Iterator<InvoiceDetail> iterator = this.invoicesDetailRepository.findAll().iterator();

			while(iterator.hasNext()) {

				InvoiceDetail invoiceDetail = iterator.next();
				result.add(InvoiceDetailDTO.build(invoiceDetail));

			}

			response.setResult(result);
			
			log.info("Lista Invoices Detail.");

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");
			
			log.info("Invoices Detail non trovati.");

		}

		return response;

	}

}
