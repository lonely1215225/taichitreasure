package edu.hunau.factory;

import edu.hunau.service.AccountService;
import edu.hunau.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.lang.reflect.Proxy;

//@Component
//public class BeanFactory {
//
//    private TransactionManager transactionManager;
//
//    @Autowired
//    public void setTransactionManager(TransactionManager transactionManager) {
//        this.transactionManager = transactionManager;
//    }
//
//    @Bean(name = "accountService")//此处是代理AccountService,由于有两个AccountService，所以此处需要起名字
//    public AccountService getAccountService(AccountService accountService) {
//        return (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
//                accountService.getClass().getInterfaces(),
//                (o, method, objects) -> {
//                    try {
//                        Object obj= null;
//                        transactionManager.beganTransactioin();
//                        obj = method.invoke(accountService, objects);
//                        transactionManager.commit();
//                        System.out.println("成功提交！！！");
//                        return obj;
//                    } catch (Exception e) {
//                        System.out.println("回滚了，有错误！！！");
//                        transactionManager.rollback();
//                        throw new RuntimeException(e);
//                    } finally {
//                        transactionManager.close();
//                    }
//                });
//    }
//}
