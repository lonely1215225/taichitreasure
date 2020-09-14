package edu.hunau.spring;

import edu.hunau.spring.config.SpringConfiguration;
import edu.hunau.spring.domain.Order;
import edu.hunau.spring.service.CustomerService;
import edu.hunau.spring.service.impl.CustomerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class test {
//    @Test
//    public void test(){
//        /**
//         * cid:用户id
//         * oid:为变长参数，可选多个订单
//         */
//        service.sumbitOrders(1,9,11,12);
//    }
//
//    @Test
//    public void getAllOrders(){
//        List<Order> orders=service.getAllOrders();
//        System.out.println(orders);
//    }
//
//    @Test
//    public void getCustomer(){
//        System.out.println(service.findCustomers());
//    }

    @Test
    public void sourceLook(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
    }
}
