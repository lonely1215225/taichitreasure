package edu.hunau.spring.dao;

import edu.hunau.spring.domain.Customer;
import edu.hunau.spring.domain.Order;

import java.util.List;

public interface CustomerDao {
    List<Order> findOrdersByCid(Integer id);

    void sumbitOrders(Long totalValues, Integer cid, String sql, Integer... oid);

    Customer findCustomerById(Integer cid);

    List<Customer> findCustomers();

    Long getOrdersValueByOids(String sql, Integer ...oid);

    List<Order> getAllOrders();

    List<Order> getSelectedOrders(String sql,Integer ... oid);
}
