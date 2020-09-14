package edu.hunau.crowd.service.api;

import edu.hunau.crowd.entity.vo.DetailProjectVO;
import edu.hunau.crowd.entity.vo.PortalTypeVO;
import edu.hunau.crowd.entity.vo.ProjectVO;

import java.util.List;

public interface ProjectService {
    void saveProject(ProjectVO projectVO, Integer memberId);

    List<PortalTypeVO> getPortalTypeVO();
    DetailProjectVO getDetailProjectVO(Integer projectId);
}
