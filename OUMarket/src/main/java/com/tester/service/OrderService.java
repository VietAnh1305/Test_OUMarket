/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tester.service;

import com.tester.pojo.Order;
import com.tester.pojo.OrderDetail;
import com.tester.pojo.sub.CartItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface OrderService {
    List<Order> getOrders(Map<String, Object> params);
    List<OrderDetail> getOrderDetails(Order order);
    int addOrder(Order o, List<OrderDetail> details);
    int addOrder(Order o, ArrayList<CartItem> cartItems);
    public Map<Integer, Integer> countOrdersInOneMonth();
    public Map<Integer, Float> countOrderByBranchInLastMonth();
}
