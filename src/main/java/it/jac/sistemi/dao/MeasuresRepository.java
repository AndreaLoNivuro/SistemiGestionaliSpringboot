package it.jac.sistemi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.jac.sistemi.entity.Measure;

@Repository
public interface MeasuresRepository extends CrudRepository<Measure, String> {
	
	//public List<CandidateAnswer> findByIdCandidate(int idCandidate);
	
	//public void deleteByIdCandidate(int idCandidate);
	
}