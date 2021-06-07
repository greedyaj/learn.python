package com.ptc.assignment.junit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.ptc.assignment.junit.model.AbstractEmployee;
import com.ptc.assignment.junit.model.Address;
import com.ptc.assignment.junit.model.Department;
import com.ptc.assignment.junit.model.Employee;
import com.ptc.assignment.junit.model.HRDepartment;
import com.ptc.assignment.junit.model.Manager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeServiceTest {

    private static Manager manager;
    private Employee employee;

    private static DepartmentService departmentService = new DepartmentService();
    private static EmployeeService employeeService = new EmployeeService();


    @BeforeClass
    public static void createManager() {
        Department department = departmentService.create(new HRDepartment(null)); 
        Address address = new Address();
        address.setCity("Pune");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setPostalCode("411001");
        address.setStreet("Bund Garden Road");

        manager = new Manager();
        manager.setName("Munde");
        manager.setPhoneNumber("12345");
        manager.setEmailAddress("abc@abc.com");
        manager.setSalary(900000);
        manager.setDepartment(department);
        manager.setPermanentAddress(address);
        manager.setPresentAddress(address);

        manager =  (Manager) employeeService.create(manager);
    }


    @Before
    public void createEmployee() {
        Department department = departmentService.create(new HRDepartment(null)); 
        Address address = new Address();
        address.setCity("Pune");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setPostalCode("4110014");
        address.setStreet("Kharadi byass");

        employee = new Employee();
        employee.setName("Ajit");
        employee.setPhoneNumber("67890");
        employee.setEmailAddress("xyz@xyz.com");
        employee.setSalary(400000);
        employee.setDepartment(department);
        employee.setPermanentAddress(address);
        employee.setPresentAddress(address);
        employee.setManager(manager);

        employee =  (Employee) employeeService.create(employee);
    }

    @Test
    public void testCreate() {
        assertNotNull(manager);
        assertNotNull(manager.getId());
        assertNotNull(employee);
        assertNotNull(employee.getId());
    }

    @Test
    public void testDelete() {
        employeeService.delete(employee.getId());
    }

    @Test
    public void testGet() {
        AbstractEmployee retreivedEmployee = employeeService.get(employee.getId());
        assertEquals(employee, retreivedEmployee);
    }

    @Test
    public void testUpdate() {
        employee.setName("Rupali");
        employeeService.update(employee);
    }

    @Test
    public void testDelete_ManagerHavingSubordinates() {
        try {
            employeeService.delete(manager.getId());
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
}
