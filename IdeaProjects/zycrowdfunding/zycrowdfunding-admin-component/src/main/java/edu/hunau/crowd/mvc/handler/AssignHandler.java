package edu.hunau.crowd.mvc.handler;

import edu.hunau.crowd.entity.Auth;
import edu.hunau.crowd.entity.Role;
import edu.hunau.crowd.service.api.AdminService;
import edu.hunau.crowd.service.api.AuthService;
import edu.hunau.crowd.service.api.RoleService;
import edu.hunau.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class AssignHandler {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthService authService;

    @RequestMapping("/assign/to/assign/role/page")
    public String getRoleByAdmin(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyWord") String keyword,
            Model model
    ) {
        List<Role> assignedRoles = roleService.getAssignedRole(adminId);
        List<Role> unassignedRoles = roleService.getUnassignedRole(adminId);
        model.addAttribute("assignedRoles", assignedRoles);
        model.addAttribute("unassignedRoles", unassignedRoles);
        return "assign-role";
    }

    @RequestMapping("/assign/do/role/assign")
    public String saveAdminRoleRelationship(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword,
// 我们允许用户在页面上取消所有已分配角色再提交表单，所以可以不提供 roleIdList 请求参数 // 设置 required=false 表示这个请求参数不是必须的
            @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList) {
        adminService.saveAdminRoleRelationship(adminId, roleIdList);
        return "redirect:/admin/get/page?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    @ResponseBody
    @RequestMapping("/assign/do/role/assign/auth")
    public ResultEntity<String> saveRoleAuthRelathinship(
            @RequestBody Map<String, List<Integer>> map) {

        authService.saveRoleAuthRelathinship(map);

        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/assign/get/assigned/auth/id/by/role/id")
    public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(
            @RequestParam("roleId") Integer roleId) {

        List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);

        return ResultEntity.successWithData(authIdList);
    }

    @ResponseBody
    @RequestMapping("/assign/get/all/auth")
    public ResultEntity<List<Auth>> getAllAuth() {

        List<Auth> authList = authService.getAll();

        return ResultEntity.successWithData(authList);
    }
}
