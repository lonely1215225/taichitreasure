package edu.hunau.springboot;

import com.alibaba.druid.pool.DruidDataSource;
import edu.hunau.springboot.entity.Department;
import edu.hunau.springboot.entity.Employee;
import edu.hunau.springboot.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@SpringBootTest
class SpringBootWebRestfulcrudApplicationTests {

    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Autowired
    EmployeeService employeeService;
    @Test
    public void test01() {
        Department dept = new Department();
        dept.setDeptId(1000);
        List<Employee> emps = employeeService.findEmployee("吴",
                new BigDecimal(2000), new BigDecimal(8000), "经理", dept);

        for(Employee emp : emps) {
            System.out.println(emp);
        }
    }

}
