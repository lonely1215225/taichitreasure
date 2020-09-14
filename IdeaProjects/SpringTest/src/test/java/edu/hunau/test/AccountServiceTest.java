package edu.hunau.test;

import edu.hunau.domain.Account;
import edu.hunau.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    AccountService service;//此处因为有两个AccountService，所以通过变量名来指示(或者用Qualifier)

    @Test
    public void transfer(){
        service.transfer("马云","李子柒",100f);
        //service.transfer("李子柒","马云",100f);
    }

    @Test
    public void testFindAll() {
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
