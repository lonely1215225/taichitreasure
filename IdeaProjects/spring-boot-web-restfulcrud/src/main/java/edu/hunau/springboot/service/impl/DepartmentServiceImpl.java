package edu.hunau.springboot.service.impl;

import java.util.List;

import edu.hunau.springboot.entity.Department;
import edu.hunau.springboot.repository.DepartmentRepository;
import edu.hunau.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;


@Service
public class DepartmentServiceImpl implements DepartmentService {
	

	private DepartmentRepository departmentRepository;

	@Autowired
	public void setDepartmentRepository(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	@Autowired
	private EntityManager entityManager;
	@Override
	public List<Department> listDepartment() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}
}
