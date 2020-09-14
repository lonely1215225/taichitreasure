package edu.hunau.ssha.controller;


import edu.hunau.ssha.dao.CustomerDao;
import edu.hunau.ssha.domain.Customer;
import edu.hunau.ssha.result.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CustomerController {
    private CustomerDao dao;
    @Autowired
    public void setDao(CustomerDao dao) {
        this.dao = dao;
    }

    @ResponseBody
    @RequestMapping("test")
    public ResultList test(){
        return ResultList.getBaseResult(666,"成功返回结果集",dao.queryAll());
    }
}
