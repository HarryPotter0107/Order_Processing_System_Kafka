package com.Kafka_Microservice.service;

import com.Kafka_Microservice.dto.request.OrderRequest;
import com.Kafka_Microservice.dto.response.OrderResponse;
import com.Kafka_Microservice.entity.Customer;
import com.Kafka_Microservice.entity.Order;
import com.Kafka_Microservice.enums.OrderStatus;
import com.Kafka_Microservice.enums.PaymentStatus;
import com.Kafka_Microservice.exceptions.CustomerNotFoundException;
import com.Kafka_Microservice.exceptions.OrderNotFoundException;
import com.Kafka_Microservice.repository.CustomerRepository;
import com.Kafka_Microservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;

    public OrderResponse createOrder(OrderRequest orderRequest){
        Customer customer = customerRepository.findById(orderRequest.getCustId()).orElseThrow(()->new CustomerNotFoundException("Customer not found:- " +orderRequest.getCustId()));
        Order request = new Order();

        request.setProductId(orderRequest.getProductId());
        request.setProductName(orderRequest.getProductName());
        request.setQuantity(orderRequest.getQuantity());
        request.setAmount(orderRequest.getAmount());
        request.setDeliveryAddress(orderRequest.getDeliveryAddress());

        request.setCustomer(customer);

        request.setStatus(OrderStatus.CREATED);
        request.setPaymentStatus(PaymentStatus.PENDING);

        Order savedOrder = orderRepository.save(request);

        OrderResponse response = new OrderResponse();

        response.setOdrId(savedOrder.getOdrId());
        response.setCustId(savedOrder.getCustomer().getCustId());
        response.setProductName(savedOrder.getProductName());
        response.setAmount(savedOrder.getAmount());
        response.setOrderStatus(savedOrder.getStatus());
        response.setPaymentStatus(savedOrder.getPaymentStatus());

        return response;
    }

    public OrderResponse updateOrder(int odrId, OrderRequest orderRequest) {

        Order request = orderRepository.findById(odrId).orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + odrId));
        Customer customer = customerRepository.findById(orderRequest.getCustId()).orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + orderRequest.getCustId()));

        request.setProductId(orderRequest.getProductId());
        request.setProductName(orderRequest.getProductName());
        request.setQuantity(orderRequest.getQuantity());
        request.setAmount(orderRequest.getAmount());
        request.setDeliveryAddress(orderRequest.getDeliveryAddress());

        request.setCustomer(customer);

        Order savedOrder = orderRepository.save(request);

        OrderResponse response = new OrderResponse();

        response.setOdrId(savedOrder.getOdrId());
        response.setCustId(savedOrder.getCustomer().getCustId());
        response.setProductName(savedOrder.getProductName());
        response.setAmount(savedOrder.getAmount());
        response.setOrderStatus(savedOrder.getStatus());
        response.setPaymentStatus(savedOrder.getPaymentStatus());

        return response;
    }

    public List<OrderResponse> getAllOrders() {

        Iterable<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderList = new ArrayList<>();
        for (Order order : orders) {
            OrderResponse response = new OrderResponse();
            response.setOdrId(order.getOdrId());
            response.setCustId(order.getCustomer().getCustId());
            response.setProductName(order.getProductName());
            response.setAmount(order.getAmount());
            response.setOrderStatus(order.getStatus());
            response.setPaymentStatus(order.getPaymentStatus());
            orderList.add(response);
        }
        return orderList;
    }

    public OrderResponse findById(int id) {

        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id));
        OrderResponse response = new OrderResponse();
        response.setOdrId(order.getOdrId());
        response.setCustId(order.getCustomer().getCustId());
        response.setProductName(order.getProductName());
        response.setAmount(order.getAmount());
        response.setOrderStatus(order.getStatus());
        response.setPaymentStatus(order.getPaymentStatus());
        return response;
    }

    public void deleteOrder() {
        orderRepository.deleteAll();
        System.out.println("All order deleted");
    }

    public void deleteById(int id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id));
        orderRepository.delete(order);
        System.out.println(id + " :- Order deleted");
    }
}
