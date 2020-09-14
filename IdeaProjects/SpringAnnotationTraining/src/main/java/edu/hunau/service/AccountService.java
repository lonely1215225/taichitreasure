package edu.hunau.service;

import edu.hunau.domain.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAllAccount();

    Account findAccountById(Integer id);

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Integer accountId);
}
