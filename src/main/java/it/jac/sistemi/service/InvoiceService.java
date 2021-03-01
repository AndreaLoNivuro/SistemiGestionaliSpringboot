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

			Item item = this.itemRepository.findById(invoiceDetail.getCodItem()).get();
			Vat vat = this.vatRepository.findById(item.getVat()).get();

			invoiceDetail.setDescription(item.getDescription());
			invoiceDetail.setMeasure(item.getMeasure());
			invoiceDetail.setCodVat(item.getVat());
			invoiceDetail.setUnitPrice(item.getPrice());

			invoiceDetail.setTotalDiscount(
					(invoiceDetail.getQuantity()*invoiceDetail.getUnitPrice())*Float.parseFloat(invoiceDetail.getDiscount())/100
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

	//	public Response<InvoiceDetail> lineCalculations(InvoiceDetail invoiceDetail) {
	//
	//		Response<InvoiceDetail> response = new Response<InvoiceDetail>();
	//		
	//		//List<String> discountList = new ArrayList<String>();
	//		try {
	//
	//			Item item = this.itemRepository.findById(invoiceDetail.getCodItem()).get();
	//			Vat vat = this.vatRepository.findById(item.getVat()).get();
	//
	//			invoiceDetail.setDescription(item.getDescription());
	//			invoiceDetail.setMeasure(item.getMeasure());
	//			invoiceDetail.setCodVat(item.getVat());
	//			invoiceDetail.setUnitPrice(item.getPrice());
	//			
	//			log.info("1");
	//			
	//			float total = invoiceDetail.getQuantity()*invoiceDetail.getUnitPrice();
	//			float totalDiscount = 0;
	//			
	//			if (invoiceDetail.getDiscount() != "") {
	//				log.info("2");
	////				for (String string : invoiceDetail.getDiscount().trim().split("+")) {
	////					discountList.add(string);
	////				}
	//				log.info(invoiceDetail.getDiscount());
	//				log.info(invoiceDetail.getDiscount().replaceAll("\\s+", "").toString());
	//				String discountString = invoiceDetail.getDiscount().replaceAll("\\s+", "");
	//				
	//				List<String> discountList = Splitter.on('+')
	//						.trimResults()
	//						.omitEmptyStrings()
	//						.splitToList(discountString);
	//				
	//				//String[] discountList = discountString.split("+");
	//				
	//					log.info(discountList.toString());
	//				for (String discount: discountList) {
	//					totalDiscount += (total*Float.parseFloat(discount))/100;
	//				}
	//			}
	//			log.info("3");
	//			invoiceDetail.setTotalDiscount(totalDiscount);
	//			log.info("4");
	//
	//			invoiceDetail.setTaxable(
	//					(invoiceDetail.getQuantity()*invoiceDetail.getUnitPrice())-invoiceDetail.getTotalDiscount()
	//					);
	//			log.info("5");
	//			invoiceDetail.setTotalVat(
	//					(invoiceDetail.getTaxable()*vat.getVat())/100
	//					);
	//			log.info("6");
	//			invoiceDetail.setTotalLine(
	//					invoiceDetail.getTaxable()+invoiceDetail.getTotalVat()
	//					);
	//
	//			response.setResult(invoiceDetail);
	//
	//			log.info("Calcoli Invoice Detail.");
	//
	//		} catch (Exception e) {
	//
	//			response.setError("Invoice non eliminata.");
	//
	//			log.info("Invoice non eliminata.");
	//
	//		}
	//
	//		return response;
	//	}

	public Response<InvoiceSummary> summaryCalculations(List<InvoiceDetail> invoiceDetailList) {

		Response<InvoiceSummary> response = new Response<InvoiceSummary>();

		InvoiceSummary invoiceSummary = new InvoiceSummary();

		log.info("lista");
		log.info(invoiceDetailList.toString());

		float totalProducts = 0;
		float totalServices = 0;
		float tailDiscount = 0;
		float totalTileDiscount = 0;
		float totalLineDiscount = 0;
		float taxable = 0;
		float totalVat = 0;

		try {

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

			invoiceSummary.setTotalProducts(totalProducts);
			invoiceSummary.setTotalServices(totalServices);
			invoiceSummary.setTailDiscount(tailDiscount);
			invoiceSummary.setTotalTileDiscount(totalTileDiscount);
			invoiceSummary.setTotalLineDiscount(totalLineDiscount);
			invoiceSummary.setTotalDiscount(totalTileDiscount + totalLineDiscount);
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

		float totalTileDiscount = 0;
		float taxable = 0;
		float totalVat = 0;
		float totalAmount = 0;

		try {

			for (InvoiceDetail invoiceDetail: invoiceDTO.getInvoiceDetailList()) {
				
				Vat vat = this.vatRepository.findById(invoiceDetail.getCodVat()).get();
				
				//calcolo total line discount summary
				//totalLineDiscount += invoiceDetail.getTotalDiscount();
				
				//calcolo tail discount riga
				float tailDiscount = invoiceDetail.getTaxable()*invoiceDTO.getInvoiceSummary().getTailDiscount()/100;
				//calcolo total discount riga
				invoiceDetail.setTotalDiscount(invoiceDetail.getTotalDiscount()+tailDiscount);
				//calcolo total tail discount summary
				totalTileDiscount += tailDiscount;
				
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
			invoiceDTO.getInvoiceSummary().setTotalTileDiscount(totalTileDiscount);
			invoiceDTO.getInvoiceSummary().setTotalDiscount(invoiceDTO.getInvoiceSummary().getTotalDiscount()+totalTileDiscount);
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
