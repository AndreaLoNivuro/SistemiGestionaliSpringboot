package it.jac.sistemi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.jac.sistemi.entity.InvoicesDetail;
import it.jac.sistemi.pk.PkInvoiceDetail;

@Repository
public interface InvoicesDetailRepository extends CrudRepository<InvoicesDetail, PkInvoiceDetail> {
	
	//public List<CandidateAnswer> findByIdCandidate(int idCandidate);
	
	//public void deleteByIdCandidate(int idCandidate);
	
}