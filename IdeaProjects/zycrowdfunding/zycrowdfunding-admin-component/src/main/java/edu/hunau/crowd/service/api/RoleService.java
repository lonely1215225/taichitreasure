package edu.hunau.crowd.service.api;

import java.util.List;

import com.github.pagehelper.PageInfo;
import edu.hunau.crowd.entity.Role;

public interface RoleService {
	
	PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

	void saveRole(Role role);

	void updateRole(Role role);
	
	void removeRole(List<Integer> roleIdList);

	List<Role> getAssignedRole(Integer adminId);

	List<Role> getUnassignedRole(Integer adminId);

}
