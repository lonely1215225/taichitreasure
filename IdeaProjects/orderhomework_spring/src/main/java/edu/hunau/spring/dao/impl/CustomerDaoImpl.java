package edu.hunau.spring.dao.impl;

import edu.hunau.spring.dao.CustomerDao;
import edu.hunau.spring.domain.Customer;
import edu.hunau.spring.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Order> findOrdersByCid(Integer id) {
        return jdbcTemplate.query("select * from myorder where cid= ? ",new BeanPropertyRowMapper<Order>(Order.class),id);
    }

    @Override
    public void sumbitOrders(Long totalValues,Integer cid, String sql,Integer... oid) {
        jdbcTemplate.update("update customer set money = money - ? where id = ?",totalValues,cid);

        jdbcTemplate.update(sql, (Object[]) oid);
    }

    @Override
    public Customer findCustomerById(Integer cid) {
        List<Customer> query = jdbcTemplate.query("select * from customer where id = ?", new BeanPropertyRowMapper<>(Customer.class), cid);
        return query==null?null:query.get(0);
    }

    @Override
    public List<Order> getSelectedOrders(String sql, Integer... oid) {
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Order.class), (Object[]) oid);
    }

    @Override
    public List<Customer> findCustomers() {
        return jdbcTemplate.query("select * from customer ",new BeanPropertyRowMapper<>(Customer.class));
    }


    @Override
    public Long getOrdersValueByOids(String sql,Integer[] oid) {
        Integer totalValues = jdbcTemplate.queryForObject(sql, Integer.class, (Object[]) oid);
        return totalValues.longValue();
    }

    @Override
    public List<Order> getAllOrders() {
        return jdbcTemplate.query("select * from myorder",new BeanPropertyRowMapper<>(Order.class));
    }
}
