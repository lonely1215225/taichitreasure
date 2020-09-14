package edu.hunau.test;

import edu.hunau.domain.Account;
import edu.hunau.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    AccountService service;//此处因为有两个AccountService，所以通过变量名来指示(或者用Qualifier)

    @Test
    public void transfer() {
        service.transfer("马云", "李子柒", 100f);
        //service.transfer("李子柒","马云",100f);
    }

    @Test
    public void analyseSource() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:bean.xml");
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    volatile int x = 0;

    public void lock() {
        while (!atomicInteger.compareAndSet(x, x++)) {
            LockSupport.park();
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+"waiting");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(x);
    }
    public void unlock(){
        System.out.println(x);
        x=0;
    }

    public void testLock() {
        this.lock();
        System.out.println(Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            this.unlock();
        }
    }

    public static void main(String[] args) {
        AccountServiceTest test=new AccountServiceTest();
        new Thread(()->{test.testLock();},"a").start();
        new Thread(()->{test.testLock();},"b").start();
        new Thread(()->{test.testLock();},"c").start();
    }
    @Test
    public void testFindAll() {
        //ApplicationContext
        List<Account> allAccount = service.findAllAccount();
        allAccount.forEach((i) -> {
            System.out.println(i);
        });
    }

    @Test
    public void testFindById() {

        Account account = service.findAccountById(2);
        System.out.println(account);
    }

    @Test
    public void testInsert() {
        Account account = new Account();
        account.setMoney(999f);
        account.setName("马云");
        service.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        Account account = new Account();
        account.setId(3);
        account.setName("马化腾");
        account.setMoney(9999f);
        service.updateAccount(account);
    }

    @Test
    public void testDeleteById() {
        service.deleteAccount(3);
    }
}
