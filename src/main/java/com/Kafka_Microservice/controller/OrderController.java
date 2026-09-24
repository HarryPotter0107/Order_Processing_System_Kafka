package com.Kafka_Microservice.controller;

import com.Kafka_Microservice.dto.request.OrderRequest;
import com.Kafka_Microservice.dto.response.OrderResponse;
import com.Kafka_Microservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable int id,@RequestBody OrderRequest request) {
        OrderResponse response = orderService.updateOrder(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> response = orderService.getAllOrders();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable int id) {
        OrderResponse response = orderService.findById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteOrderById(@PathVariable int id) {
        orderService.deleteById(id);
        return new ResponseEntity<>("Order deleted successfully",HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String>deleteAllOrders() {
        orderService.deleteOrder();
        return new ResponseEntity<>("All orders deleted successfully",HttpStatus.OK);
    }
}
