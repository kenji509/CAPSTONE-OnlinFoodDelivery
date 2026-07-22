package com.example.capstone.strategy;

import com.example.capstone.model.OrderItem;
import java.util.List;

public interface PricingStrategy {
    double calculateTotal(List<OrderItem> items);
}