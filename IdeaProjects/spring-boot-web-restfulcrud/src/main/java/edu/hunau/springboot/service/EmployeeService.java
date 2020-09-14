package edu.hunau.springboot.service;

import java.math.BigDecimal;
import java.util.List;

import edu.hunau.springboot.entity.Department;
import edu.hunau.springboot.entity.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeService {

	public Page<Employee> pageEmployee(int page, int pageSize);
	
	public void saveEmployee(Employee emp);
	
	public Employee getEmployee(int empId);
	
	/**
	 * spring boot jpa实现动态查询
	 * 1：根据员工姓名进行模糊查询
	 * 2： 根据员工的工资区间进行区间查询
	 * 3：根据员工的职务进行查询
	 * 4：根据员工的部门进行查询
	 */
	public List<Employee> findEmployee(String empName,
                                       BigDecimal fromSalary, BigDecimal toSalary, String jobTitle, Department dept);

	void deleteEmployee(Integer id);

	List<String> getJobTitles();


}
