package edu.hunau.ssha.dao;

import edu.hunau.ssha.domain.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> queryAll();
}
