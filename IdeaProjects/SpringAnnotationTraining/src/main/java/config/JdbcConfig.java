package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class JdbcConfig {

    @Value("${url}")
    private String url;

    @Value("${driver}")
    private String driver;

    @Value("${jdbc.username}")
    private String username;

    @Value("${password}")
    private String password;

    @Bean(name = "runner")
    @Scope("prototype")
    public QueryRunner createRunner(DataSource dataSource) {
        System.out.println("need dataSource");
        return new QueryRunner(dataSource);
    }

    @Bean(name = "dataSource")
    public DataSource createDataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        System.out.println("get dataSource");
        try {
            dataSource.setJdbcUrl(url);
            dataSource.setDriverClass(driver);
            dataSource.setUser(username);
            dataSource.setPassword(password);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
