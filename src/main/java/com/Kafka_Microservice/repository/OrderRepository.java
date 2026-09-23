package com.Kafka_Microservice.repository;

import com.Kafka_Microservice.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Integer> {
}
