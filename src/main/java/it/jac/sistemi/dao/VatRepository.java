package it.jac.sistemi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.jac.sistemi.entity.Vat;

@Repository
public interface VatRepository extends CrudRepository<Vat, String> {
	
	//public List<CandidateAnswer> findByIdCandidate(int idCandidate);
	
	//public void deleteByIdCandidate(int idCandidate);
	
}