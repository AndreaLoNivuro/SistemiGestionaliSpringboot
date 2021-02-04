package it.jac.sistemi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.jac.sistemi.entity.Item;

@Repository
public interface ItemsRepository extends CrudRepository<Item, String> {
	
	//public List<CandidateAnswer> findByIdCandidate(int idCandidate);
	
	//public void deleteByIdCandidate(int idCandidate);
	
}