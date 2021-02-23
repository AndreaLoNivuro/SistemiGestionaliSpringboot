package it.jac.sistemi.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.sistemi.dao.InvoicesDetailRepository;
import it.jac.sistemi.dao.InvoicesMasterRepository;
import it.jac.sistemi.dao.InvoicesSummaryRepository;
import it.jac.sistemi.dao.ItemsRepository;
import it.jac.sistemi.dao.VatRepository;
import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.InvoiceDetail;
import it.jac.sistemi.entity.InvoiceSummary;
import it.jac.sistemi.entity.Item;
import it.jac.sistemi.entity.Vat;

@Service
public class InvoiceService {

	private static Logger log = LoggerFactory.getLogger(InvoiceService.class);

	@Autowired
	private InvoicesMasterRepository invoicesMasterRepository;

	@Autowired
	private InvoicesDetailRepository invoicesDetailRepository;

	@Autowired
	private InvoicesSummaryRepository invoicesSummaryRepository;

	@Autowired
	private VatRepository vatRepository;

	@Autowired
	private ItemsRepository itemRepository;

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

	public Response<InvoiceDetail> lineCalculations(InvoiceDetail invoiceDetail) {

		Response<InvoiceDetail> response = new Response<InvoiceDetail>();

		try {

			Item item = this.itemRepository.findById(invoiceDetail.getCodItem()).get();
			Vat vat = this.vatRepository.findById(item.getVat()).get();

			invoiceDetail.setDescription(item.getDescription());
			invoiceDetail.setMeasure(item.getMeasure());
			invoiceDetail.setCodVat(item.getVat());
			invoiceDetail.setUnitPrice(item.getPrice());

			invoiceDetail.setTotalDiscount(
					(invoiceDetail.getQuantity()*invoiceDetail.getUnitPrice())*invoiceDetail.getDiscount()/100
					);

			invoiceDetail.setTaxable(
					(invoiceDetail.getQuantity()*invoiceDetail.getUnitPrice())-invoiceDetail.getTotalDiscount()
					);

			invoiceDetail.setTotalVat(
					(invoiceDetail.getTaxable()*vat.getVat())/100
					);

			invoiceDetail.setTotalLine(
					invoiceDetail.getTaxable()+invoiceDetail.getTotalVat()
					);

			response.setResult(invoiceDetail);

			log.info("Calcoli Invoice Detail.");

		} catch (Exception e) {

			response.setError("Invoice non eliminata.");

			log.info("Invoice non eliminata.");

		}

		return response;
	}

	public Response<InvoiceSummary> summaryCalculations(int codInvoice) {

		Response<InvoiceSummary> response = new Response<InvoiceSummary>();

		List<InvoiceDetail> invoiceDetailList = new ArrayList<InvoiceDetail>();

		InvoiceSummary invoiceSummary = new InvoiceSummary();

		float totalProducts = 0;
		float totalServices = 0;
		float tailDiscount = 0;
		float totalTileDiscount = 0;
		float totalLineDiscount = 0;
		float totalDiscount = 0;
		float taxable = 0;
		float totalVat = 0;
		float totalAmount = 0;

		try {

			invoiceDetailList = this.invoicesDetailRepository.findByCodInvoice(codInvoice);

			for (InvoiceDetail invoiceDetail: invoiceDetailList) {
				Item item = this.itemRepository.findById(invoiceDetail.getCodItem()).get();

				if (item.getType().equalsIgnoreCase("Servizio")) {
					totalServices += (invoiceDetail.getQuantity()*invoiceDetail.getUnitPrice());
				} else {
					totalProducts += (invoiceDetail.getQuantity()*invoiceDetail.getUnitPrice());
				}

				totalLineDiscount += invoiceDetail.getTotalDiscount();
				taxable += invoiceDetail.getTaxable();
				totalVat += invoiceDetail.getTotalVat();
				

			}

			invoiceSummary.setCodInvoice(codInvoice);
			invoiceSummary.setTotalProducts(totalProducts);
			invoiceSummary.setTotalServices(totalServices);
			invoiceSummary.setTailDiscount(tailDiscount);
			invoiceSummary.setTotalTileDiscount(totalTileDiscount);
			invoiceSummary.setTotalLineDiscount(totalLineDiscount);
			invoiceSummary.setTotalDiscount(totalTileDiscount + totalLineDiscount);
			invoiceSummary.setTotalVat(totalVat);
			invoiceSummary.setTaxable(taxable);
			invoiceSummary.setTotalAmount(totalAmount);

			response.setResult(invoiceSummary);

			log.info("Provisional Invoice Summary calcolato.");

		} catch (Exception e) {

			response.setError("Provisional Invoice non calcolato.");

			log.info("Provisional Invoice Summary non calcolato.");

		}

		return response;
	}
}
