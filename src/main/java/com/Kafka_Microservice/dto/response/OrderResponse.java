package com.Kafka_Microservice.dto.response;

import com.Kafka_Microservice.enums.OrderStatus;
import com.Kafka_Microservice.enums.PaymentStatus;

public class OrderResponse {
    private int odrId;
    private int custId;
    private String productName;
    private double amount;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;

    public int getOdrId() {
        return odrId;
    }

    public void setOdrId(int odrId) {
        this.odrId = odrId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
