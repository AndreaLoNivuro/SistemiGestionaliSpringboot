package it.jac.sistemi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.sistemi.dao.ItemsRepository;
import it.jac.sistemi.dao.VatRepository;
import it.jac.sistemi.dto.InvoiceDTO;
import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.InvoiceDetail;
import it.jac.sistemi.entity.InvoiceMaster;
import it.jac.sistemi.entity.InvoiceSummary;
import it.jac.sistemi.entity.Item;
import it.jac.sistemi.entity.Vat;

@Service
public class InvoiceService {

	private static Logger log = LoggerFactory.getLogger(InvoiceService.class);

	@Autowired
	private InvoiceMasterService invoiceMasterService;

	@Autowired
	private InvoiceDetailService invoiceDetailService;

	@Autowired
	private InvoiceSummaryService invoiceSummaryService;

	@Autowired
	private VatRepository vatRepository;

	@Autowired
	private ItemsRepository itemRepository;

	public Response<String> deleteInvoice(int codInvoice) {
		Response<String> response = new Response<String>();

		try {	

			this.invoiceMasterService.deleteInvoiceMasterByCodInvoice(codInvoice);
			this.invoiceDetailService.deleteInvoiceDetailByCodInvoice(codInvoice);
			this.invoiceSummaryService.deleteInvoiceSummaryByCodInvoice(codInvoice);

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

			//ricerca item selezionato
			Item item = this.itemRepository.findById(invoiceDetail.getCodItem()).get();

			//impostazione dei valori in caso non fossero stati modificati a frontend
			if (invoiceDetail.getDescription() != "") {
				invoiceDetail.setDescription(item.getDescription());
			}
			if (invoiceDetail.getMeasure() != "") {
				invoiceDetail.setMeasure(item.getMeasure());
			}
			if (invoiceDetail.getCodVat() != "") {
				invoiceDetail.setCodVat(item.getVat());
			}
			if (invoiceDetail.getUnitPrice() != 0) {
				invoiceDetail.setUnitPrice(item.getPrice());
			}

			//ricerca iva corrispondete
			Vat vat = this.vatRepository.findById(invoiceDetail.getCodVat()).get();

			//calcolo total price
			invoiceDetail.setTotalPrice(invoiceDetail.getQuantity()*invoiceDetail.getUnitPrice());

			//variabile per il calcolo dello sconto
			float total = invoiceDetail.getTotalPrice();
			float totalDiscount = 0;

			if (invoiceDetail.getDiscount() != "") {

				//rimozione spazzi dalla stringa di sconto
				String discountString = invoiceDetail.getDiscount().replaceAll("\\s+", "");

				//split della stringa di sconto
				String[] discountList = discountString.split("\\+");

				//ciclo che calcola lo sconto
				for (String discount: discountList) {
					totalDiscount += (total*Float.parseFloat(discount))/100;
					total -= totalDiscount;
				}
			}
			
			//impostazione del totale sconto e dell'imponibile
			invoiceDetail.setTotalDiscount(totalDiscount);
			invoiceDetail.setTaxable(total);

			//calcolo iva
			invoiceDetail.setTotalVat(
					(invoiceDetail.getTaxable()*vat.getVat())/100
					);
			
			//calcolo totale riga
			invoiceDetail.setTotalLine(
					invoiceDetail.getTaxable()+invoiceDetail.getTotalVat()
					);

			response.setResult(invoiceDetail);

			log.info("Calcoli Invoice Detail.");

		} catch (Exception e) {

			e.printStackTrace();

			response.setError("Calcoli invoice detail non riusciti.");

			log.info("Calcoli invoice detail non riusciti.");

		}

		return response;
	}

	public Response<InvoiceSummary> summaryCalculations(List<InvoiceDetail> invoiceDetailList) {

		Response<InvoiceSummary> response = new Response<InvoiceSummary>();

		InvoiceSummary invoiceSummary = new InvoiceSummary();

		log.info("lista");
		log.info(invoiceDetailList.toString());

		//inizializzazione valori
		float totalProducts = 0;
		float totalServices = 0;
		//float tailDiscount = 0;
		//float totalTileDiscount = 0;
		float totalLineDiscount = 0;
		float taxable = 0;
		float totalVat = 0;

		try {

			for (InvoiceDetail invoiceDetail: invoiceDetailList) {
				Item item = this.itemRepository.findById(invoiceDetail.getCodItem()).get();

				//calcolo total products o total service
				if (item.getType().equalsIgnoreCase("Servizio")) {
					totalServices += (invoiceDetail.getTotalPrice());
				} else {
					totalProducts += (invoiceDetail.getTotalPrice());
				}
				
				//somma valori per ogni dettaglio
				totalLineDiscount += invoiceDetail.getTotalDiscount();
				taxable += invoiceDetail.getTaxable();
				totalVat += invoiceDetail.getTotalVat();

			}

			invoiceSummary.setTotalProducts(totalProducts);
			invoiceSummary.setTotalServices(totalServices);
			//invoiceSummary.setTailDiscount(tailDiscount);
			//invoiceSummary.setTotalTileDiscount(totalTileDiscount);
			invoiceSummary.setTotalLineDiscount(totalLineDiscount);
			invoiceSummary.setTotalDiscount(/*totalTileDiscount + */totalLineDiscount);
			invoiceSummary.setTotalVat(totalVat);
			invoiceSummary.setTaxable(taxable);
			invoiceSummary.setTotalAmount(taxable+totalVat);

			log.info(invoiceSummary.toString());

			response.setResult(invoiceSummary);

			log.info("Provisional Invoice Summary calcolato.");

		} catch (Exception e) {

			response.setError("Provisional Invoice non calcolato.");

			log.info("Provisional Invoice Summary non calcolato.");

		}

		return response;
	}
	
	public Response<InvoiceDTO> tailDiscountCalculations(InvoiceDTO invoiceDTO) {

		Response<InvoiceDTO> response = new Response<InvoiceDTO>();

		log.info("tail discount");

		float totalLineDiscount = 0;
		float totalTailDiscount = 0;
		float taxable = 0;
		float totalVat = 0;
		float totalAmount = 0;

		try {

			for (InvoiceDetail invoiceDetail: invoiceDTO.getInvoiceDetailList()) {

				Vat vat = this.vatRepository.findById(invoiceDetail.getCodVat()).get();
				//calcolo total line discount summary
				totalLineDiscount += invoiceDetail.getTotalDiscount();

				//valori per il calcolo tail discount
				float total = invoiceDetail.getTaxable();
				float tailDiscount = 0;

				//calcolo tail discount riga
				if (invoiceDTO.getInvoiceSummary().getTailDiscount() != "") {

					//rimozione spazzi dalla stringa di sconto
					String discountString = invoiceDTO.getInvoiceSummary().getTailDiscount().replaceAll("\\s+", "");

					//split della stringa di sconto
					String[] discountList = discountString.split("\\+");

					//ciclo che calcola lo sconto
					for (String discount: discountList) {
						tailDiscount += (total*Float.parseFloat(discount))/100;
						total -= tailDiscount;
					}
				}
				
				//calcolo total discount riga
				invoiceDetail.setTotalDiscount(invoiceDetail.getTotalDiscount() + tailDiscount);
				//calcolo total tail discount summary
				totalTailDiscount += tailDiscount;

				//calcolo taxable riga 
				invoiceDetail.setTaxable(invoiceDetail.getTaxable() - tailDiscount);
				//calcolo total taxable summary
				taxable += invoiceDetail.getTaxable();

				//calcolo total vat riga
				invoiceDetail.setTotalVat((invoiceDetail.getTaxable()*vat.getVat())/100);
				//calcolo total vat summary
				totalVat += invoiceDetail.getTotalVat();

				//calcolo total riga
				invoiceDetail.setTotalLine(invoiceDetail.getTaxable()+invoiceDetail.getTotalVat());
				//calcolo total amount summary
				totalAmount += invoiceDetail.getTotalLine();

			}

			//set nuovi valori in summary
			invoiceDTO.getInvoiceSummary().setTotalTailDiscount(totalTailDiscount);
			invoiceDTO.getInvoiceSummary().setTotalDiscount(invoiceDTO.getInvoiceSummary().getTotalDiscount() + totalTailDiscount);
			invoiceDTO.getInvoiceSummary().setTaxable(taxable);
			invoiceDTO.getInvoiceSummary().setTotalVat(totalVat);
			invoiceDTO.getInvoiceSummary().setTotalAmount(totalAmount);

			response.setResult(invoiceDTO);

			log.info("Provisional tail discount calcolato.");

		} catch (Exception e) {

			response.setError("Provisional tail discount non calcolato.");

			log.info("Provisional tail discount non calcolato.");

		}

		return response;
	}

	public Response<InvoiceDTO> createInvoice(InvoiceDTO invoiceDTO) {

		Response<InvoiceDTO> response = new Response<InvoiceDTO>();

		try {

			InvoiceMaster invoiceMasterCreated = this.invoiceMasterService.createInvoiceMaster(invoiceDTO.getInvoiceMaster()).getResult();

			List<InvoiceDetail> invoiceDetalListCreated = this.invoiceDetailService.createInvoiceDetail(invoiceDTO.getInvoiceDetailList(), invoiceMasterCreated.getCodInvoice()).getResult();

			InvoiceSummary invoiceSummaryCreated = this.invoiceSummaryService.createInvoiceSummary(invoiceDTO.getInvoiceSummary(), invoiceMasterCreated.getCodInvoice()).getResult();

			InvoiceDTO invoiceDTOCreated = null;
			invoiceDTOCreated.setInvoiceMaster(invoiceMasterCreated);
			if (invoiceDetalListCreated != null) {
				invoiceDTOCreated.setInvoiceDetailList(invoiceDetalListCreated);
			}
			if (invoiceSummaryCreated != null) {
				invoiceDTOCreated.setInvoiceSummary(invoiceSummaryCreated);
			}

			response.setResult(invoiceDTOCreated);

			log.info("Invoice creata/modificata.");

		} catch (Exception e) {

			response.setError("Invoice non creata/modificata.");

			log.info("Invoice non creata/modificata.");

		}

		return response;
	}
}
