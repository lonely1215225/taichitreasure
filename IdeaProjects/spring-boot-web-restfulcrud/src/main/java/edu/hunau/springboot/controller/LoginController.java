package edu.hunau.springboot.controller;

import edu.hunau.springboot.entity.User;
import edu.hunau.springboot.exception.BaseException;
import edu.hunau.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/user/login")
    public String userLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session, Map<String, Object> map) {

        try {
            User user = userService.userLogin(username, password);
            session.setAttribute("user", user);
            return "redirect:/emps/beforeList";
        } catch (BaseException e) {
            map.put("msg", e.getMessage());
            return "index";
        }
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }


}
