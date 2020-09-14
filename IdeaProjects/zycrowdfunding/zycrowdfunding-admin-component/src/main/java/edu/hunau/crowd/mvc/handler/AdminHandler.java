package edu.hunau.crowd.mvc.handler;


import com.github.pagehelper.PageInfo;
import edu.hunau.crowd.constant.CrowdConstant;
import edu.hunau.crowd.entity.Admin;
import edu.hunau.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AdminHandler {
    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/do/login")
    public String login(@RequestParam("loginAccount") String loginAccount,
                        @RequestParam("password") String password, HttpSession session) {
        Admin admin = adminService.getAdminByLoginAccount(loginAccount, password);
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);
        String path = "redirect:/admin/to/mainPage";
        return path;

    }

    @GetMapping("/admin/do/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginAdmin");
        String path = "redirect:/admin/to/loginPage";
        return path;
    }

    @PreAuthorize("hasRole('经理') or hasAuthority('user:get')")
    @RequestMapping("admin/get/page")
    public String getAdminPage(Model model,
                               @RequestParam(value = "keyWord", defaultValue = "") String keyWord,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo pageInfo = adminService.getPageInfo(keyWord, pageNum, pageSize);
        model.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin-page";
    }

    @ResponseBody
    @PostMapping(value = "admin/remove")
    public void removeAdminById(Integer adminId) {
        adminService.removeAdminById(adminId);
    }

    @PreAuthorize("hasAuthority('user:save')")
    @RequestMapping("/admin/do/save")
    public String save(Admin admin) {
        adminService.saveAdmin(admin);
        String path = "redirect:/admin/get/page?pageNum=" + Integer.MAX_VALUE;
        return path;
    }

    @RequestMapping("/admin/to/editPage")
    public String toEditPage(
            @RequestParam("adminId") Integer adminId,
            Model model
    ) {

        // 1.根据adminId查询Admin对象
        Admin admin = adminService.getAdminById(adminId);

        // 2.将Admin对象存入模型
        model.addAttribute("admin", admin);

        return "admin-edit";
    }

    @RequestMapping("/admin/do/update")
    public String update(Admin admin, @RequestParam("pageNum") Integer pageNum,
                         @RequestParam("keyWord") String keyWord) {
        adminService.update(admin);
        String path="redirect:/admin/get/page?pageNum="+pageNum+"&keyword="+keyWord;
        return path;
    }

}
