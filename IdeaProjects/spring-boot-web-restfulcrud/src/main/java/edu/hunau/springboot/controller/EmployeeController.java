package edu.hunau.springboot.controller;

import java.math.BigDecimal;
import java.util.List;

import edu.hunau.springboot.entity.Department;
import edu.hunau.springboot.entity.Employee;
import edu.hunau.springboot.service.DepartmentService;
import edu.hunau.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;

	/**
	 * 查询分页的员工
	 */
	@GetMapping("/emps/{page}")
	public String pageEmployee(Model model, @PathVariable("page") Integer page,
			@RequestParam(value="pageSize", required=false, defaultValue="10") Integer pageSize) {
		Page<Employee> emps = employeeService.pageEmployee(page, pageSize);
		model.addAttribute("emps", emps);
		model.addAttribute("page", page);

		return "emp/list";
	}
	@GetMapping("/emps/beforeList")
	public String beforePageEmployee(HttpSession session){
		List<String> jobTitles=employeeService.getJobTitles();
		session.setAttribute("jobTitles",jobTitles);
		List<Department> depts=departmentService.listDepartment();
		session.setAttribute("depts",depts);
		return "redirect:/emps/0";
	}
	/**
	 * 跳转到添加页面
	 */
	@GetMapping("/emp")
	public String toAdd() {
//		List<Department> depts = departmentService.listDepartment();
//		model.addAttribute("depts", depts);
		return "emp/add";
	}
	
	/**
	 * 员工的添加
	 */
	@PostMapping("/emp")
	public String addEmp(Employee employee) {
		employeeService.saveEmployee(employee);
		//redirect : 重定向到一个页面
		return "redirect:/emps/0";
	}
	
	/**
	 * 来到修改页面
	 */
	@GetMapping("/emp/{id}")
	public String toEditPage(@PathVariable("id") Integer id, Model model) {
		Employee emp = employeeService.getEmployee(id);
		model.addAttribute("emp", emp);
		
//		List<Department> depts = departmentService.listDepartment();
//		model.addAttribute("depts", depts);
		return "emp/add";
	}
	
	/**
	 * 员工修改页面
	 */
	@PutMapping("/emp")
	public String updateEmployee(Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/emps/0";
	}

	@DeleteMapping("/emp/{id}")
	public String deleteEmployee(@PathVariable Integer id){
		employeeService.deleteEmployee(id);
		return "redirect:/emps/0";
	}
	/**
	 * 作业：自己完成员工的修改和删除。
	 */

	@PostMapping("/emp/condition")
	public String rangeSearchOfSalary(Model model,String empName,BigDecimal minSalary,BigDecimal maxSalary,String jobTitle,Department department){
		System.out.println(department.getDeptName());
		List<Employee> employees=employeeService.findEmployee(empName,minSalary,maxSalary,jobTitle,department);
		model.addAttribute("emps",employees);
		return "/emp/list";
	}
}
