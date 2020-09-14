package edu.hunau.spring.service;

import edu.hunau.spring.domain.Customer;
import edu.hunau.spring.domain.Order;

import java.util.List;

public interface CustomerService {

    List<Order> findOrdersById(Integer id);

    Customer findCustomerById(Integer cid);

    List<Customer> findCustomers();

    void sumbitOrders(Integer cid,Integer ... oid);

    List<Order> getAllOrders();

    List<Order> selectedOrders(Integer ...oid);
}
