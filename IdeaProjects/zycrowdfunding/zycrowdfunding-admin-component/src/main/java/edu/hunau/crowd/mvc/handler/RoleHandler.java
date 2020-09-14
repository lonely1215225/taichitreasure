package edu.hunau.crowd.mvc.handler;

import java.util.List;

import edu.hunau.crowd.entity.Role;
import edu.hunau.crowd.service.api.RoleService;
import edu.hunau.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;

@RestController
public class RoleHandler {
	
	@Autowired
	private RoleService roleService;
	@RequestMapping("/role/remove/by/role/id/array")
	public ResultEntity<String> removeByRoleIdArray(@RequestBody List<Integer> roleIdList) {
		
		roleService.removeRole(roleIdList);
		
		return ResultEntity.successWithoutData();
	}
	@RequestMapping("/role/update")
	public ResultEntity<String> updateRole(Role role) {
		
		roleService.updateRole(role);
		
		return ResultEntity.successWithoutData();
	}
	@RequestMapping("/role/save")
	public ResultEntity<String> saveRole(Role role) {
		
		roleService.saveRole(role);
		
		return ResultEntity.successWithoutData();
	}

	@PreAuthorize("hasRole('部长')")
	@RequestMapping(value = "/role/get/page/info")
	public ResultEntity<PageInfo<Role>> getPageInfo(
				@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
				@RequestParam(value="pageSize", defaultValue="5") Integer pageSize,
				@RequestParam(value="keyword", defaultValue="") String keyword
			) {
		
		// 调用Service方法获取分页数据
		PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
		
		// 封装到ResultEntity对象中返回（如果上面的操作抛出异常，交给异常映射机制处理）
		return ResultEntity.successWithData(pageInfo);
	}

}
