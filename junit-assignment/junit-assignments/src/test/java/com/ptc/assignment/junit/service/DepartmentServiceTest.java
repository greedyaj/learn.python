package com.ptc.assignment.junit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.ptc.assignment.junit.model.Department;
import com.ptc.assignment.junit.model.Employee;
import com.ptc.assignment.junit.model.HRDepartment;
import com.ptc.assignment.junit.model.SalesDepartment;

import org.junit.Before;
import org.junit.Test;

public class DepartmentServiceTest {

    private Department department;
    private DepartmentService service = new DepartmentService();
    private EmployeeService employeeService = new EmployeeService();

    @Before
    public void create() {
        Department salesDepartment = new SalesDepartment(null);
        department = service.create(salesDepartment);   
    }

    @Test
    public void testCreate() {
        assertNotNull(department);
        assertNotNull(department.getOrgId());
    }

    @Test
    public void testDelete() {
        service.delete(department.getOrgId());
    }

    @Test
    public void testGet() {
        Department retrieveDepartment = service.get(department.getOrgId());
        
        assertEquals(department.getOrgId(), retrieveDepartment.getOrgId());
        assertEquals(department.getName(), retrieveDepartment.getName());
    }

    @Test
    public void testUpdate() {
        Department udatedDepartment = new HRDepartment(department.getOrgId());
        service.update(udatedDepartment);
    }

    @Test
    public void testDelete_HavingEmployees() {
        Employee employee = new Employee();
        employee.setName("Rupali");
        employee.setPhoneNumber("67890");
        employee.setEmailAddress("xyz@xyz.com");
        employee.setSalary(400000);
        employee.setDepartment(department);

        employee =  (Employee) employeeService.create(employee);
        
        try {
            service.delete(department.getOrgId());
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
}
