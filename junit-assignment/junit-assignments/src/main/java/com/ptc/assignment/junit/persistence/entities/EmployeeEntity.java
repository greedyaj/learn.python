package com.ptc.assignment.junit.persistence.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.ptc.assignment.junit.model.AbstractEmployee;
import com.ptc.assignment.junit.model.Address;
import com.ptc.assignment.junit.model.Department;
import com.ptc.assignment.junit.model.Employee;
import com.ptc.assignment.junit.model.HRDepartment;
import com.ptc.assignment.junit.model.Manager;
import com.ptc.assignment.junit.model.RnDDepartment;
import com.ptc.assignment.junit.model.SalesDepartment;

import org.apache.commons.beanutils.BeanUtils;

@Entity
public class EmployeeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity presentAddress;
    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity permanentAddress;
    @Column
    private String emailAddress;
    @Column
    private String phoneNumber;
    @Column
    private String salary;
    @ManyToOne(cascade = {})
    private DepartmentEntity department;
    @ManyToOne(cascade = {})
    private EmployeeEntity manager;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public AddressEntity getPresentAddress() {
        return presentAddress;
    }
    public void setPresentAddress(AddressEntity presentAddress) {
        this.presentAddress = presentAddress;
    }
    public AddressEntity getPermanentAddress() {
        return permanentAddress;
    }
    public void setPermanentAddress(AddressEntity permanentAddress) {
        this.permanentAddress = permanentAddress;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }
    public DepartmentEntity getDepartment() {
        return department;
    }
    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
    public EmployeeEntity getManager() {
        return manager;
    }
    public void setManager(EmployeeEntity manager) {
        this.manager = manager;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        return id.equals(that.id);
    }

    @Override public int hashCode() {
        return id.hashCode();
    }

    public static AbstractEmployee convert(EmployeeEntity employeeEntity) throws Exception {
        AbstractEmployee e = null;
        if (employeeEntity.getManager() == null) {
            e = new Manager();
        } else {
            e = new Employee();
            ((Employee)e).setManager((Manager)convert(employeeEntity.getManager()));
        }
        e.setEmailAddress(employeeEntity.getEmailAddress());
        e.setName(employeeEntity.getName());
        e.setPhoneNumber(employeeEntity.getPhoneNumber());
        e.setSalary(Double.valueOf(employeeEntity.getSalary()));
        e.setId(employeeEntity.getId());

        e.setPermanentAddress(new Address());
        BeanUtils.copyProperties(e.getPermanentAddress(), employeeEntity.getPermanentAddress());

        e.setPresentAddress(new Address());
        BeanUtils.copyProperties(e.getPresentAddress(), employeeEntity.getPresentAddress());

        switch (Department.Type.valueOf(employeeEntity.getDepartment().getName())) {
            case HR: e.setDepartment(new HRDepartment(employeeEntity.getDepartment().getId()));
                break;
            case SALES: e.setDepartment(new SalesDepartment(employeeEntity.getDepartment().getId()));
                break;
            case R_AND_D: e.setDepartment(new RnDDepartment(employeeEntity.getDepartment().getId()));
                break;
        }
        return e;
    }


    public static EmployeeEntity convert(AbstractEmployee employee) throws Exception {
        EmployeeEntity e = new EmployeeEntity();
        if (employee instanceof Employee) {
            if (((Employee)employee).getManager() != null) {
                e.setManager(convert(((Employee)employee).getManager()));
            }
        }
        e.setEmailAddress(employee.getEmailAddress());
        e.setName(employee.getName());
        e.setPhoneNumber(employee.getPhoneNumber());
        e.setSalary(String.valueOf(employee.getSalary()));
        e.setId(employee.getId());

        e.setPermanentAddress(new AddressEntity());
        BeanUtils.copyProperties(e.getPermanentAddress(), employee.getPermanentAddress());

        e.setPresentAddress(new AddressEntity());
        BeanUtils.copyProperties(e.getPresentAddress(), employee.getPresentAddress());

        e.setDepartment(new DepartmentEntity());
        e.getDepartment().setId(employee.getDepartment().getOrgId());
        e.getDepartment().setName(employee.getDepartment().getName().toString());

        return e;
    }
}
