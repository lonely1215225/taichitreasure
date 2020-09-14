package edu.hunau.crowd.handler;

import edu.hunau.crowd.api.MySQLRemoteService;
import edu.hunau.crowd.constant.CrowdConstant;
import edu.hunau.crowd.entity.vo.PortalTypeVO;
import edu.hunau.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PortalHandler {
    @Autowired
    private MySQLRemoteService mySQLRemoteService;
    @RequestMapping(value = "/")
    public String toIndex(Model model){
        ResultEntity<List<PortalTypeVO>> portalTypeVO = mySQLRemoteService.getPortalTypeVO();
        String result = portalTypeVO.getResult();
        if (ResultEntity.SUCCESS.equals(result)){
            List<PortalTypeVO> portalTypeVOList = portalTypeVO.getData();
            model.addAttribute(CrowdConstant.ATTR_NAME_PORTAL_DATA,portalTypeVOList);
        }
        return "index";
    }
}
