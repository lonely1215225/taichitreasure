package edu.hunau.test;

import config.SpringConfiguration;
import edu.hunau.domain.Account;
import edu.hunau.service.AccountService;
import edu.hunau.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {

    @Autowired//这里由于只有1个bean对象类型，自动按照类型注入，即去Spring ioc容器中找AccountService类
    private AccountService service;

    @Test
    public void testFindAll() {
        //ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //AccountServiceImpl service = context.getBean("accountService", AccountServiceImpl.class);
        String[] names = context.getBeanDefinitionNames();
        System.out.println(Arrays.asList(names));
//        List<Account> allAccount = service.findAllAccount();
//        allAccount.forEach((i) -> {
//            System.out.println(i);
//        });
    }

    @Test
    public void testFindById() {
//        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
       // ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfiguration.class);
      //  AccountServiceImpl service = context.getBean("accountService", AccountServiceImpl.class);
        Account account = service.findAccountById(5);
        System.out.println(account);

    }

    @Test
    public void testInsert() {
      //  ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
      //  AccountServiceImpl service = context.getBean("accountService", AccountServiceImpl.class);
        Account account = new Account();
        account.setId(3);
        account.setName("马化腾");
        account.setMoney(9999f);
        service.saveAccount(account);
    }

    @Test
    public void testUpdate() {
      //  ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
     //   AccountServiceImpl service = context.getBean("accountService", AccountServiceImpl.class);
        Account account = new Account();
        account.setId(3);
        account.setName("马到成功");
        account.setMoney(9999f);
        service.updateAccount(account);
    }

    @Test
    public void testDeleteById() {
      //  ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
     //   AccountServiceImpl service = context.getBean("accountService", AccountServiceImpl.class);
        service.deleteAccount(3);
    }
}
