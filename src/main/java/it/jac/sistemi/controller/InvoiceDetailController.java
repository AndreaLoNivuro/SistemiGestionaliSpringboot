package it.jac.sistemi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.sistemi.dto.Response;
import it.jac.sistemi.service.InvoiceDetailService;

@RestController
@RequestMapping("/invoice/detail")
public class InvoiceDetailController {
	
	private static Logger log = LoggerFactory.getLogger(InvoiceDetailController.class);

	@Autowired
	private InvoiceDetailService invoiceDetailService;
	
	@GetMapping(path="/findAll")
	public Response<?> findAllInvoicesDetail() {
		
		log.info("Richiesta di find all Invoice Detail.");
		
		return invoiceDetailService.findAllInvoicesDetail();
		
	}
	
//	@PostMapping("/create")
//	public Response<?> createInvoiceDetail(
//			@RequestBody List<InvoiceDetail> invoiceDetailList,
//			@RequestBody int codInvoice
//			) {
//		
//		log.info("Richiesta di create Invoice Detail.");
//
//		return invoiceDetailService.createInvoiceDetail(invoiceDetailList, codInvoice);
//
//	}
//	
//	@PostMapping("/create")
//	public Response<?> createInvoiceDetail(
//			@RequestBody List<InvoiceDetail> invoiceDetailList
//			) {
//		
//		log.info("Richiesta di create Invoice Detail.");
//
//		return invoiceDetailService.createInvoiceDetail(invoiceDetailList);
//
//	}
//	
//	@PostMapping(path = "/delete")
//	public Response<?> deleteInvoiceDetail(
//			@RequestBody InvoiceDetail invoiceDetail
//			) {
//
//		log.info("Richiesta di delete Invoice Detail.");
//
//		return invoiceDetailService.deleteInvoiceDetail(invoiceDetail);
//		
//	}

}
