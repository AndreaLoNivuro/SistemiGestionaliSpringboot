package it.jac.sistemi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.sistemi.dto.InvoiceDTO;
import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.InvoiceDetail;
import it.jac.sistemi.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	
	private static Logger log = LoggerFactory.getLogger(InvoiceController.class);

	@Autowired
	private InvoiceService invoiceService;
	
	@PostMapping(path = "/create")
	public Response<?> createInvoice(
			@RequestBody InvoiceDTO invoiceDTO
			) {

		log.info("Richiesta di create Invoice.");

		return invoiceService.createInvoice(invoiceDTO);
		
	}
	
	@PostMapping(path = "/delete")
	public Response<?> deleteInvoice(
			@RequestBody int codInvoice
			) {

		log.info("Richiesta di delete Invoice.");

		return invoiceService.deleteInvoice(codInvoice);
		
	}
	
	@PostMapping(path = "/provisionalCalcDetail")
	public Response<?> calculateInvoiceDetail(
			@RequestBody InvoiceDetail invoiceDetail
			) {

		log.info("Richiesta di calculate Invoice Detail.");

		return invoiceService.lineCalculations(invoiceDetail);
		
	}
	
	@PostMapping(path = "/provisionalCalcSummary")
	public Response<?> calculateInvoiceSummary(
			@RequestBody List<InvoiceDetail> invoiceDetailList
			) {

		log.info("Richiesta di calculate provisional Invoice Summary.");

		return invoiceService.summaryCalculations(invoiceDetailList);
		
	}
	
	@PostMapping(path = "/tailDiscountCalculations")
	public Response<?> tailDiscountCalculations(
			@RequestBody InvoiceDTO invoiceDTO
			) {

		log.info("Richiesta di calcuate tail discount.");

		return invoiceService.tailDiscountCalculations(invoiceDTO);
		
	}

}
