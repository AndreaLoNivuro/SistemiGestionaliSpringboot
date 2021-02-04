package it.jac.sistemi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.jac.sistemi.entity.InvoiceSummary;
import it.jac.sistemi.pk.PkInvoiceSummary;

@Repository
public interface InvoicesSummaryRepository extends CrudRepository<InvoiceSummary, PkInvoiceSummary> {
	
	//public List<CandidateAnswer> findByIdCandidate(int idCandidate);
	
	//public void deleteByIdCandidate(int idCandidate);
	
}