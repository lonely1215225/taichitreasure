package edu.hunau.springboot.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="department")
public class Department {


    @Id
    @Column(name="dept_id")
    private int deptId;

    @Column(name="dept_name", length=32, nullable=false)
    private String deptName;

    @OneToMany(mappedBy="department")
    private Set<Employee> emps = new HashSet<Employee>();

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Set<Employee> getEmps() {
        return emps;
    }

    public void setEmps(Set<Employee> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Department [deptId=" + deptId + ", deptName=" + deptName + "]";
    }


}
