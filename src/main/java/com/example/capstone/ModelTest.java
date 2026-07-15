package com.example.capstone;

import com.example.capstone.model.*;
import java.util.ArrayList;
import java.util.List;

public class ModelTest {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant("R1", "Jollibee", "Cebu City");
        MenuItem burger = new MenuItem("M1", "Chickenjoy", "Fried chicken", 89.0, "Main");
        restaurant.addMenuItem(burger);

        Customer customer = new Customer("C1", "Kenji", "kenji@email.com", "pass123", "0917xxxxxxx", "Dalaguete, Cebu");

        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(burger, 2, "No spicy"));

        Order order = customer.placeOrder(restaurant, items);

        System.out.println("Order total: " + order.calculateTotal());
    }
}