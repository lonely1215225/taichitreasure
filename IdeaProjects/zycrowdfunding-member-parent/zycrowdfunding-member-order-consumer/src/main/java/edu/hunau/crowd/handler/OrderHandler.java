package edu.hunau.crowd.handler;

import edu.hunau.crowd.api.MySQLRemoteService;
import edu.hunau.crowd.constant.CrowdConstant;
import edu.hunau.crowd.entity.vo.AddressVO;
import edu.hunau.crowd.entity.vo.MemberLoginVO;
import edu.hunau.crowd.entity.vo.OrderProjectVO;
import edu.hunau.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderHandler {

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/save/address")
    public String saveAddress(AddressVO addressVO, HttpSession session) {

        // 1.执行地址信息的保存
        //ResultEntity<String> resultEntity = mySQLRemoteService.saveAddressRemote(addressVO);


        // 2.从Session域获取orderProjectVO对象
        OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute("orderProjectVO");

        // 3.从orderProjectVO对象中获取returnCount
        Integer returnCount = orderProjectVO.getReturnCount();

        // 4.重定向到指定地址，重新进入确认订单页面
        return "redirect:http://www.crowd.com/order/confirm/order/"+returnCount;
    }

    @RequestMapping("/confirm/order/{returnCount}")
    public String showConfirmOrderInfo(
            @PathVariable("returnCount") Integer returnCount,
            HttpSession session) {

        // 1.把接收到的回报数量合并到Session域
        OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute("orderProjectVO");

        orderProjectVO.setReturnCount(returnCount);

        session.setAttribute("orderProjectVO", orderProjectVO);

        // 2.获取当前已登录用户的id
        MemberLoginVO memberLoginVO = (MemberLoginVO) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER);

        Integer memberId = memberLoginVO.getId();

        // 3.查询目前的收货地址数据
        //ResultEntity<List<AddressVO>> resultEntity = mySQLRemoteService.getAddressVORemote(memberId);

//        if(ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
//            List<AddressVO> list = resultEntity.getData();
//            session.setAttribute("addressVOList", list);
//        }

        return "confirm_order";
    }


    @RequestMapping("/confirm/return/info/{projectId}/{returnId}")
    public String showReturnConfirmInfo(@PathVariable("projectId") Integer projectId,
                                        @PathVariable("returnId") Integer returnId,
                                        HttpSession session){
       ResultEntity<OrderProjectVO> voResultEntity=mySQLRemoteService.getOrderProjectVORemote(projectId,returnId);
       if (ResultEntity.SUCCESS.equals(voResultEntity.getResult())){
           OrderProjectVO orderProjectVO = voResultEntity.getData();
           session.setAttribute("orderProjectVO",orderProjectVO);
       }
        return "confirm_return";
    }
}
