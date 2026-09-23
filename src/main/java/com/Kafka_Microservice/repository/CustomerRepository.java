package com.Kafka_Microservice.repository;

import com.Kafka_Microservice.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
}
