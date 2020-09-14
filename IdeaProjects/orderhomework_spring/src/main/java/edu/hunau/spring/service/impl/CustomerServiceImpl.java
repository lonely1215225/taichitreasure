package edu.hunau.spring.service.impl;

import edu.hunau.spring.dao.impl.CustomerDaoImpl;
import edu.hunau.spring.domain.Customer;
import edu.hunau.spring.domain.Order;
import edu.hunau.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)//只读型事务的配置
//@Service
public class CustomerServiceImpl implements CustomerService {


    private CustomerDaoImpl dao;

    @Autowired
    public void setDao(CustomerDaoImpl dao) {
        this.dao = dao;
    }

    @Override
    public List<Order> findOrdersById(Integer id) {
        return dao.findOrdersByCid(id);
    }

    @Override
    public Customer findCustomerById(Integer cid) {
        return dao.findCustomerById(cid);
    }

    @Override
    public List<Customer> findCustomers() {
        return dao.findCustomers();
    }

    @Override
    public List<Order> selectedOrders(Integer... oid) {
        StringBuilder sql = new StringBuilder("select * from myorder where id = ? ");
        for (int i = 1; i < oid.length; i++)
            sql.append("or id = ? ");
        return dao.getSelectedOrders(sql.toString(), oid);
    }

    //需要的是读写型事务配置
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void sumbitOrders(Integer cid, Integer... oid) {
        //获得提交订单人的信息
        Customer customer = dao.findCustomerById(cid);
        double money = customer.getMoney();
        if (null != customer)
            System.out.println("当前提交订单人信息为：" + customer);
        else
            throw new RuntimeException("查无此老板");

        //获得要提交的每个订单的总金额

        List<Order> selectedOrders = selectedOrders(oid);//验证输入订单编号合法性
        if (oid.length>selectedOrders.size())
            throw new RuntimeException("输入订单编号有误，请根据cid选择自己的订单");
        System.out.println("提交订单信息：");
        selectedOrders.forEach((i) -> {
            System.out.println(i);
        });

        StringBuilder sql = new StringBuilder("select sum(value) from myorder where id = ? ");
        for (int i = 1; i < oid.length; i++)
            sql.append("or id = ? ");
        Long totalValues = dao.getOrdersValueByOids(sql.toString(), oid);
        if (null==totalValues)
            throw new RuntimeException("输入了错误的订单编号");

        System.out.println("提交订单总金额为：" + totalValues);

        //提交订单
        StringBuilder del = new StringBuilder("delete from myorder where id = ?");
        for (int i = 1; i < oid.length; i++)
            del.append(" or id = ? ");
        dao.sumbitOrders(totalValues, cid, del.toString(), oid);
        System.out.println("订单提交进行中......");


        //为了体现事务，这里判断提交订单后，订单人的金额是否小于0
        customer = dao.findCustomerById(cid);
        if (customer.getMoney() <= 0)
            throw new RuntimeException(customer.getName() + "老板，您的金额不足\n您当前金额为："+money);
        else {
            System.out.println("订单提交成功");
            System.out.println("当前提交订单人信息为：" + customer);
            System.out.println("所有订单：");
            List<Order> allOrders = getAllOrders();
            allOrders.forEach((i) -> {
                System.out.println(i);
            });
        }
//比较订单人的money和所提交订单的订单总额value进行比较
//        if (customer.getMoney()<totalValues){
//            System.out.println("not enough");
//            throw new RuntimeException(customer.getName()+"老板，您的金额不足");
//        }else {
//            System.out.println("enought,进行扣款");
//            //提交订单
//            StringBuilder del = new StringBuilder("delete from myorder where id = ?");
//            for (int i = 1; i <oid.length; i++)
//                sql.append("or id = ? ");
//            dao.sumbitOrders(totalValues,cid,del.toString(),oid);
//        }
    }

    @Override
    public List<Order> getAllOrders() {
        return dao.getAllOrders();
    }
}
