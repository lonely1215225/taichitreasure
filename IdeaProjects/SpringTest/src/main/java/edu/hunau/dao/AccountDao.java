package edu.hunau.dao;

import edu.hunau.domain.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountDao {
    List<Account> findAllAccount() ;

    Account findAccountById(Integer id);

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Integer accountId);

    Account findAccountByName(String name);
}
