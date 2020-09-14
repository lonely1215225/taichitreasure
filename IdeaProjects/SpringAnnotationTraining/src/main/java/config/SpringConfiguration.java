package config;
import org.springframework.context.annotation.*;


@ComponentScan("edu.hunau")
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
@EnableAspectJAutoProxy
public class SpringConfiguration {
}
