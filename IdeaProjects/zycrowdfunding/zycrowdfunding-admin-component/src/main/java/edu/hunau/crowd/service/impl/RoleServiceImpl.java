package edu.hunau.crowd.service.impl;

import java.util.List;

import edu.hunau.crowd.entity.Role;
import edu.hunau.crowd.entity.RoleExample;
import edu.hunau.crowd.entity.RoleExample.Criteria;
import edu.hunau.crowd.mapper.RoleMapper;
import edu.hunau.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
		
		// 1.开启分页功能
		PageHelper.startPage(pageNum, pageSize);
		
		// 2.执行查询
		List<Role> roleList = roleMapper.selectRolesByKeyword(keyword);
		
		// 3.封装为PageInfo对象返回
		return new PageInfo<>(roleList);
	}

	@Override
	public void saveRole(Role role) {
		roleMapper.insert(role);
	}

	@Override
	public void updateRole(Role role) {
		roleMapper.updateByPrimaryKey(role);
	}

	@Override
	public void removeRole(List<Integer> roleIdList) {
		
		RoleExample example = new RoleExample();
		
		Criteria criteria = example.createCriteria();
		
		//delete from t_role where id in (5,8,12)
		criteria.andIdIn(roleIdList);
		
		roleMapper.deleteByExample(example);
	}
	@Override
	public List<Role> getAssignedRole(Integer adminId) {
		return roleMapper.getAssignedRole(adminId);
	}

	@Override
	public List<Role> getUnassignedRole(Integer adminId) {
		return roleMapper.getUnassignedRole(adminId);
	}
}
