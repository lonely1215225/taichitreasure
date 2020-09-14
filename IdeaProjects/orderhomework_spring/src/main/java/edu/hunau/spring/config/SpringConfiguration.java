package edu.hunau.spring.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("edu.hunau.spring")
//@Import({JdbcConfig.class,TransactionConfig.class})
//@PropertySource("jdbc.properties")
//@EnableTransactionManagement
@EnableAspectJAutoProxy
public class SpringConfiguration {
}
