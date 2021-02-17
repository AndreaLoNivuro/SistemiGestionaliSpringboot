package it.jac.sistemi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.jac.sistemi.entity.InvoiceDetail;
import it.jac.sistemi.pk.PkInvoiceDetail;

@Repository
public interface InvoicesDetailRepository extends CrudRepository<InvoiceDetail, PkInvoiceDetail> {
	
	public Void deleteByCodInvoice(int codInvoice);
	
}