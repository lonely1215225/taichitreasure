package edu.hunau.crowd.api;

import edu.hunau.crowd.entity.po.MemberPO;
import edu.hunau.crowd.entity.vo.DetailProjectVO;
import edu.hunau.crowd.entity.vo.OrderProjectVO;
import edu.hunau.crowd.entity.vo.PortalTypeVO;
import edu.hunau.crowd.entity.vo.ProjectVO;
import edu.hunau.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("taiChiMan-crowd-mysql")
public interface MySQLRemoteService {
    @RequestMapping("/get/memberPo/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginAcct")String loginAcct);

    @RequestMapping("/auth/do/member/register")
    ResultEntity<String> saveMember(@RequestBody MemberPO memberPO);

    @RequestMapping("/save/project/vo/remote")
    ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId") Integer memberId);

    @RequestMapping("/get/portalType/vo/remote")
    ResultEntity<List<PortalTypeVO>> getPortalTypeVO();

    @RequestMapping("/get/project/detail/remote/{projectId}")
    ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId);

    @RequestMapping("get/order/project/vo/remote")
    ResultEntity<OrderProjectVO> getOrderProjectVORemote(@RequestParam("projectId") Integer projectId,
                                                         @RequestParam("returnId") Integer returnId);
}