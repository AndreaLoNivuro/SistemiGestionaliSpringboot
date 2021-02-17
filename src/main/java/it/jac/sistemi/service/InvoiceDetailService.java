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

	public Response<InvoiceDetail> createInvoiceDetail(InvoiceDetail invoiceDetail) {

		Response<InvoiceDetail> response = new Response<InvoiceDetail>();

		try {

			response.setResult(this.invoicesDetailRepository.save(invoiceDetail));

		} catch (Exception e) {

			response.setError("Invoice Detail non creato");

		}

		return response;

	}


	public Response<String> deleteInvoiceDetailByCodInvoice(int codInvoice) {

		Response<String> response = new Response<String>();

		try {

			this.invoicesDetailRepository.deleteByCodInvoice(codInvoice);			

			response.setResult("Invoice Detail eliminato perchè eliminata fattura.");

		} catch (Exception e) {

			response.setError("Invoice Detail non eliminato.");

		}

		return response;

	}
	
	public Response<String> deleteInvoiceDetail(InvoiceDetail invoiceDetail) {

		Response<String> response = new Response<String>();

		try {

			this.invoicesDetailRepository.delete(invoiceDetail);			

			response.setResult("Invoice Detail eliminato.");

		} catch (Exception e) {

			response.setError("Invoice Detail non eliminato.");

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

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}

}
