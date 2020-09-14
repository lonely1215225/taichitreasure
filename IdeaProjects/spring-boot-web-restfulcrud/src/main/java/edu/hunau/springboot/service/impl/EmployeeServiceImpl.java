package edu.hunau.springboot.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import edu.hunau.springboot.entity.Department;
import edu.hunau.springboot.entity.Employee;
import edu.hunau.springboot.repository.EmployeeRepository;
import edu.hunau.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private EntityManager entityManager;
    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<Employee> pageEmployee(int page, int pageSize) {
        //指定排序方式，和按empId来排序
        Sort sort = Sort.by(Sort.Direction.DESC, "empId");

        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public void saveEmployee(Employee emp) {
        // TODO Auto-generated method stub
        employeeRepository.save(emp);
    }

    @Override
    public Employee getEmployee(int empId) {
        // TODO Auto-generated method stub
        return employeeRepository.findById(empId).orElse(null);
    }

    /**
     * 作业2：自己完成按工资区间来查询完成
     */
    @Override
    public List<Employee> findEmployee(String empName, BigDecimal fromSalary, BigDecimal toSalary, String jobTitle,
                                       Department dept) {
        // TODO Auto-generated method stub
        CriteriaBuilder builder = entityManager.getEntityManagerFactory()
                .createEntityManager().getCriteriaBuilder();

        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);

        Root<Employee> root = criteria.from(Employee.class);
        criteria.select(root);

        Predicate pall = null;

        //加查询条件
        if (dept != null) {
            Predicate p1 = builder.equal(root.get("department"), dept);
            if (p1 != null) {
                pall = p1;
            }
        }



        if (empName != null) {
            Predicate p2 = builder.like(root.get("empName"), "%" + empName + "%");
            if (p2 != null && pall != null) {
                pall = builder.and(pall, p2);
            } else if (p2 != null && pall == null) {
                pall = p2;
            }
        }
        Predicate p3=builder.between(root.get("salary"),fromSalary,toSalary);
        if (pall!=null)
        pall=builder.and(pall,p3);
        else pall=p3;
        if (pall != null) {
            criteria.where(pall);
        }

        System.out.println("=================");
        return entityManager.getEntityManagerFactory().createEntityManager()
                .createQuery(criteria).getResultList();
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<String> getJobTitles() {
        Query query = entityManager.createNativeQuery("SELECT DISTINCT(job_title) FROM employee");
        List<String> list = query.getResultList();
        return list;
    }



}
