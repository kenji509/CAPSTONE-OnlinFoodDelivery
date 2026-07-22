package com.example.capstone.strategy;

import com.example.capstone.model.OrderItem;
import java.util.List;

public class StandardPricingStrategy implements PricingStrategy {

    @Override
    public double calculateTotal(List<OrderItem> items) {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
}