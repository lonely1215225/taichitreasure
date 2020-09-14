package edu.hunau.crowd.service.api;

import com.github.pagehelper.PageInfo;
import edu.hunau.crowd.entity.Admin;
import edu.hunau.crowd.entity.Role;

import java.util.List;

public interface AdminService {
    void saveAdmin(Admin admin);

    List<Admin> getAll();


    Admin getAdminByLoginAccount(String loginAccount, String password);
    Admin getAdminByLoginAccount(String loginAccount);

    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    void removeAdminById(Integer adminId);

    Admin getAdminById(Integer adminId);

    void update(Admin admin);


    void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList);
}
