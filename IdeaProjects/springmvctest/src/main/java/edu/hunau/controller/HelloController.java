package edu.hunau.controller;

import edu.hunau.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


@Controller
public class HelloController {

//    @RequestMapping(path = "helloWorld",method = RequestMethod.POST)
//    public String sayHello(String test){
//        System.out.println("success"+test);
//        return "success";
//    }

//    @GetMapping(path = "helloWorld")
//    public String sayHello(Model model, User user){
//        model.addAttribute("user",user);
//        return "success";
//    }

//    @RequestMapping(path = "/helloWorld")
//    public String requestBody(@RequestBody String body,Model model,@ModelAttribute("abc") User user,@CookieValue("JSESSIONID") String jid){
//        model.addAttribute("body",body);
//        model.addAttribute("user",user);
//        model.addAttribute("jid",jid);
//        System.out.println("后执行");
//        System.out.println(user);
//        System.out.println(jid);
//        return "success";
//    }


    @GetMapping(path = "hello/{id}/{name}/{date}")
    public String restful(@PathVariable Integer id, @PathVariable String name, @PathVariable Date date, Model model){
        model.addAttribute("user1",id+name+date);
        return "success";
    }

//    @ModelAttribute
//    public void modelAttribute(Integer id,Model model, Map<String,User> map){
//        System.out.println("==========");
//        User use = new User();
//        use.setId(id);
//        use.setName("咪咪");//这里的是数据库拿到的
//        use.setDate(new Date());
//        System.out.println(use);
//        model.addAttribute("user1",use);
//        map.put("abc",use);
//       // return user;
//    }

    //@ResponseBody
    @RequestMapping("/jsonTest")
    public String jsonTest(@RequestBody User username){
        System.out.println(username);
        return "success";
    }
}
