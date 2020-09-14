package edu.hunau.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

public class TransactionConfig {

    @Bean("transactionManager")
    public DataSourceTransactionManager createTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean("transactionTemplate")
    public TransactionTemplate createTransactionTemplate(DataSourceTransactionManager transactionManager){
        return new TransactionTemplate(transactionManager);
    }
}
