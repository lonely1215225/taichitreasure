package edu.hunau.crowd;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import edu.hunau.crowd.entity.Admin;
import edu.hunau.crowd.entity.Menu;
import edu.hunau.crowd.entity.Role;
import edu.hunau.crowd.mapper.AdminMapper;
import edu.hunau.crowd.service.api.AdminService;
import edu.hunau.crowd.service.api.MenuService;
import edu.hunau.crowd.service.api.RoleService;
import edu.hunau.crowd.service.impl.AdminServiceImpl;
import edu.hunau.crowd.service.impl.RoleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {


    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    public void test(){
//        for (int i = 0; i < 10; i++) {
//            Admin admin=new Admin(null,"alone121"+i,"123456","taichiman"+i,i+"lonely@qq.com",null);
//            adminService.saveAdmin(admin);
//        }
        boolean b = bCryptPasswordEncoder.matches("123456", "$2a$10$WjAVcitw.lqenjbsbDEOWO8WGw8.yuT3LF16G1JxSiQNsTfGI/ZKi");
        System.out.println(b);
//        String[] names = applicationContext.getBeanDefinitionNames();
//        for (String name:names) System.out.println(name);
    }
    @Test
    public void testTX(){
        List<Menu> all = menuService.getAll();
        Menu root = null;
        Map<Integer, Menu> menuMap = new HashMap<>();
        for (Menu menu : all) {
            Integer pid = menu.getPid();
            if (pid == null) {
                root = menu;
            }
            Integer id = menu.getId();
            menuMap.put(id, menu);
            Menu father;
            if ((father = menuMap.get(pid))!=null) {
                father.getChildren().add(menu);
            }
        }
    }
}
