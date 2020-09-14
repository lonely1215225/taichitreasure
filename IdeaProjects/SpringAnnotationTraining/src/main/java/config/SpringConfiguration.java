package config;
import edu.hunau.condition.AccountCondition;
import edu.hunau.domain.Account;
import org.springframework.context.annotation.*;


//@ComponentScan("edu.hunau")
//@Import(JdbcConfig.class)
//@PropertySource("classpath:jdbcConfig.properties")
//@EnableAspectJAutoProxy
@Configuration
//@Import(MyImportBeanDefinitionRegistrar.class)
@ComponentScan("edu.hunau.domain")
public class SpringConfiguration {

    public Account account(){
        return new Account(88,"zzy",10000f);
    }
}
