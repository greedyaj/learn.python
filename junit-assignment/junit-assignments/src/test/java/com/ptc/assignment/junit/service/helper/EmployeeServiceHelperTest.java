package com.ptc.assignment.junit.service.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.ptc.assignment.junit.model.AbstractEmployee;
import com.ptc.assignment.junit.model.Address;
import com.ptc.assignment.junit.model.Department;
import com.ptc.assignment.junit.model.Employee;
import com.ptc.assignment.junit.model.HRDepartment;
import com.ptc.assignment.junit.model.Manager;
import com.ptc.assignment.junit.service.DepartmentService;
import com.ptc.assignment.junit.service.EmployeeService;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeServiceHelperTest {
    private static Manager manager;
    private List<Employee> employees = new ArrayList<>();

    private static DepartmentService departmentService = new DepartmentService();
    private static EmployeeService employeeService = new EmployeeService();
    private EmployeeServiceHelper employeeServiceHelper = new EmployeeServiceHelper();


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
    public void createEmployees() {
        employees.add(createEmployee());
        employees.add(createEmployee());
    }    

    private Employee createEmployee() {
        Department department = departmentService.create(new HRDepartment(null)); 
        Address address = new Address();
        address.setCity("Pune");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setPostalCode("4110014");
        address.setStreet("Kharadi byass");

        Employee employee = new Employee();
        employee.setName("Rupali");
        employee.setPhoneNumber("67890");
        employee.setEmailAddress("xyz@xyz.com");
        employee.setSalary(400000);
        employee.setDepartment(department);
        employee.setPermanentAddress(address);
        employee.setPresentAddress(address);
        employee.setManager(manager);

        employee =  (Employee) employeeService.create(employee);

        return employee;
    }
    
    @Test
    public void testGetEmployeesWithName() {
        List<AbstractEmployee> fetchedEmployees = employeeServiceHelper.getEmployeesWithName("Rupali");
        assertTrue(fetchedEmployees.size() >= 2);
        for (AbstractEmployee fetchedEmployee : fetchedEmployees) {
            assertEquals("Rupali", fetchedEmployee.getName());
        }
    }

    @Test
    public void testIsEmployeeWithNameExists() {
        assertTrue(employeeServiceHelper.isEmployeeWithNameExists("Rupali"));
    }

    @Test
    public void testIsEmployeeWithNameDoesNotExists() {
        assertFalse(employeeServiceHelper.isEmployeeWithNameExists("Unknown"));
    }
}
