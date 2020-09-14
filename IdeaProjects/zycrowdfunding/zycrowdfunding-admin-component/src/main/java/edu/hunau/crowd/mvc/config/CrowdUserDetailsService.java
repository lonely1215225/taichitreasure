package edu.hunau.crowd.mvc.config;

import edu.hunau.crowd.entity.Admin;
import edu.hunau.crowd.entity.Role;
import edu.hunau.crowd.service.api.AdminService;
import edu.hunau.crowd.service.api.AuthService;
import edu.hunau.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CrowdUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.getAdminByLoginAccount(username);
        Integer id = admin.getId();
        List<Role> roles = roleService.getAssignedRole(id);
        List<String> authNames = authService.getAssignedAuthNameByAdminId(id);

        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();

        for (Role role:roles){
            String roleName="ROLE_"+role.getName();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);
            grantedAuthorities.add(authority);
        }
        for (String authName:authNames){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(authName);
            grantedAuthorities.add(authority);
        }
//        User user = new User(admin.getLoginAcct(), admin.getUserPswd(), grantedAuthorities);
        SecurityAdmin securityAdmin=new SecurityAdmin(admin,grantedAuthorities);
        return securityAdmin;
    }
}
