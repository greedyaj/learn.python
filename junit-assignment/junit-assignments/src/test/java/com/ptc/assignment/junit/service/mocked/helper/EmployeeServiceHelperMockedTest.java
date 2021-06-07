package com.ptc.assignment.junit.service.mocked.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.ptc.assignment.junit.model.AbstractEmployee;
import com.ptc.assignment.junit.persistence.DBManager;
import com.ptc.assignment.junit.persistence.entities.AddressEntity;
import com.ptc.assignment.junit.persistence.entities.DepartmentEntity;
import com.ptc.assignment.junit.persistence.entities.EmployeeEntity;
import com.ptc.assignment.junit.service.helper.EmployeeServiceHelper;

import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;

public class EmployeeServiceHelperMockedTest {
    private static EmployeeEntity manager;
    
    @Mocked
    protected DBManager mockedDBManager;

    private static int counter;

    private EmployeeServiceHelper serviceHelper;

    public void createManager() {
        AddressEntity address = new AddressEntity();
        address.setId(++counter);
        address.setCity("Pune");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setPostalCode("411001");
        address.setStreet("Bund Garden Road");

        manager = new EmployeeEntity();
        manager.setId(++counter);
        manager.setName("Munde");
        manager.setPhoneNumber("12345");
        manager.setEmailAddress("abc@abc.com");
        manager.setSalary("900000");
        manager.setDepartment(new DepartmentEntity());
        manager.getDepartment().setId(++counter);
        manager.getDepartment().setName("HR");
        manager.setPermanentAddress(address);
        manager.setPresentAddress(address);
    }

    private EmployeeEntity createEmployee() {
        AddressEntity address = new AddressEntity();
        address.setId(++counter);
        address.setCity("Pune");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setPostalCode("4110014");
        address.setStreet("Kharadi byass");

        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(++counter);
        employee.setName("Rupali");
        employee.setPhoneNumber("67890");
        employee.setEmailAddress("xyz@xyz.com");
        employee.setSalary("400000");
        employee.setDepartment(manager.getDepartment());
        employee.setPermanentAddress(address);
        employee.setPresentAddress(address);
        employee.setManager(manager);

        return employee;
    }

    @Before
    public void setup() {
        createManager();
        List<EmployeeEntity> mockedEmployeeEntities = List.of(createEmployee(), createEmployee());
        new Expectations() {{
            mockedDBManager.findAll(EmployeeEntity.class); 
            result = mockedEmployeeEntities;
            times = 1;
        }};
        serviceHelper = new EmployeeServiceHelper(mockedDBManager);
    }

    @Test
    public void testGetEmployeesWithName() {
        List<AbstractEmployee> fetchedEmployees = serviceHelper.getEmployeesWithName("Rupali");
        assertTrue(fetchedEmployees.size() == 2);
        for (AbstractEmployee fetchedEmployee : fetchedEmployees) {
            assertEquals("Rupali", fetchedEmployee.getName());
        }
    }

    @Test
    public void testIsEmployeeWithNameExists() {
        assertTrue(serviceHelper.isEmployeeWithNameExists("Rupali"));
    }

    @Test
    public void testIsEmployeeWithNameDoesNotExists() {
        assertFalse(serviceHelper.isEmployeeWithNameExists("Unknown"));
    }
}
