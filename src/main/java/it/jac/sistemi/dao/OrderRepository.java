package it.jac.sistemi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.jac.sistemi.entity.Order;
import it.jac.sistemi.pk.PkOrder;

@Repository
public interface OrderRepository extends CrudRepository<Order, PkOrder> {
	
	//public List<CandidateAnswer> findByIdCandidate(int idCandidate);
	
	//public void deleteByIdCandidate(int idCandidate);
	
}