package com.Kafka_Microservice.service;

import com.Kafka_Microservice.dto.request.CustomerRequest;
import com.Kafka_Microservice.dto.response.CustomerResponse;
import com.Kafka_Microservice.entity.Customer;
import com.Kafka_Microservice.exceptions.CustomerNotFoundException;
import com.Kafka_Microservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponse createCustomer(CustomerRequest CustomerRequest){
        Customer request = new Customer();
        request.setName(CustomerRequest.getName());
        request.setEmail(CustomerRequest.getEmail());
        request.setAddress(CustomerRequest.getAddress());
        Customer savedCustomer = customerRepository.save(request);
        CustomerResponse response = new CustomerResponse();
        response.setCustId(savedCustomer.getCustId());
        response.setName(savedCustomer.getName());
        response.setEmail(savedCustomer.getEmail());
        response.setAddress(savedCustomer.getAddress());
        return response;
    }

    public CustomerResponse updateCustomer(CustomerRequest CustomerRequest,int id){
        Customer request = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found:- " + id));
        request.setName(CustomerRequest.getName());
        request.setEmail(CustomerRequest.getEmail());
        request.setAddress(CustomerRequest.getAddress());
        Customer savedCustomer = customerRepository.save(request);
        CustomerResponse response = new CustomerResponse();
        response.setCustId(savedCustomer.getCustId());
        response.setName(savedCustomer.getName());
        response.setEmail(savedCustomer.getEmail());
        response.setAddress(savedCustomer.getAddress());
        return response;
    }

    public List<CustomerResponse> getAllCustomers() {

        Iterable<Customer> customers = customerRepository.findAll();

        List<CustomerResponse> customerList = new ArrayList<>();

        for (Customer customer : customers) {

            CustomerResponse response = new CustomerResponse();

            response.setCustId(customer.getCustId());
            response.setName(customer.getName());
            response.setEmail(customer.getEmail());
            response.setAddress(customer.getAddress());

            customerList.add(response);
        }

        return customerList;
    }

    public CustomerResponse findById(int id){
        Customer request = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found:- " + id));
        CustomerResponse response = new CustomerResponse();
        response.setCustId(request.getCustId());
        response.setName(request.getName());
        response.setEmail(request.getEmail());
        response.setAddress(request.getAddress());
        return response;
    }

    public void deleteCustomer() {
        customerRepository.deleteAll();
        System.out.println("All customer deleted");
    }

    public void deleteById(int id){
        Customer request = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found:- " + id));
        customerRepository.delete(request);
        System.out.println("Customer deleted:- "+id);
    }
}
