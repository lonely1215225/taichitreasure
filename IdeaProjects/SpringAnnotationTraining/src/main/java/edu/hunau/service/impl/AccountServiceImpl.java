package edu.hunau.service.impl;

import edu.hunau.dao.AccountDao;
import edu.hunau.domain.Account;
import edu.hunau.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    @Autowired
    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    public Account findAccountById(Integer id){
        return accountDao.findAccountById(id);
    }


    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        Account account1 = findAccountById(account.getId());
        System.out.println(account1);
        //int i=1/0;
        accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer accountId) {
accountDao.deleteAccount(accountId);
    }
}
