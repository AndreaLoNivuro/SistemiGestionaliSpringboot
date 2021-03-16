package it.jac.sistemi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.jac.sistemi.entity.Payment;

@Repository
public interface PaymentsRepository extends CrudRepository<Payment, String> {
	
}