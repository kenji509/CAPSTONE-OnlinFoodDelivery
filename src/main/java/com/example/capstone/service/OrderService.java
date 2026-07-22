package com.example.capstone.service;

import com.example.capstone.dao.OrderDAO;
import com.example.capstone.model.Order;
import java.util.List;

public class OrderService {

    private final OrderDAO orderDAO = new OrderDAO();

    public boolean placeOrder(Order order, String itemsSummary) {
        return orderDAO.save(order, itemsSummary);
    }

    public boolean cancelOrder(Order order) {
        order.cancelOrder();
        return orderDAO.updateStatus(order.getOrderId(), order.getStatus());
    }

    public List<String> getPendingOrders() {
        return orderDAO.getPendingOrders();
    }

    public List<String> getOrderHistory(String customerId) {
        return orderDAO.getOrderHistory(customerId);
    }

    public boolean acceptOrder(String orderId, String riderId) {
        return orderDAO.acceptOrder(orderId, riderId);
    }
}