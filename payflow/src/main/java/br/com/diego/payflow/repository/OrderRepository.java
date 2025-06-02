package br.com.diego.payflow.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.diego.payflow.config.model.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {

	Page<OrderEntity> findAllByCustomerId(Long customerId, PageRequest pageRequest); 
	
}

