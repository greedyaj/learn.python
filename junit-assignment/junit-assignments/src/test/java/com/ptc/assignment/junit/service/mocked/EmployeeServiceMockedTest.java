package com.ptc.assignment.junit.service.mocked;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.ptc.assignment.junit.model.AbstractEmployee;
import com.ptc.assignment.junit.model.Address;
import com.ptc.assignment.junit.model.Department;
import com.ptc.assignment.junit.model.Employee;
import com.ptc.assignment.junit.model.HRDepartment;
import com.ptc.assignment.junit.model.Manager;
import com.ptc.assignment.junit.persistence.DBManager;
import com.ptc.assignment.junit.persistence.entities.EmployeeEntity;
import com.ptc.assignment.junit.service.EmployeeService;

import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class EmployeeServiceMockedTest {

    @Mocked
    protected DBManager mockedDBManager;

    private static int counter;

    protected Manager manager;
    protected Employee employee;

    protected EmployeeEntity managerEntity;
    protected EmployeeEntity employeeEntity;


    // Ignoring some tests due to Issue with JMockit when used with Java 11
    // https://github.com/jmockit/jmockit1/issues/534
    // https://github.com/floscher/josm/commit/a2eb34264be6ffb9e9cb0f2da56183fe6c82d459

    @Before
    public void createManager() {
        try {
            Department department = new HRDepartment(++counter);
            Address address = new Address();
            address.setId(++counter);
            address.setCity("Pune");
            address.setState("Maharashtra");
            address.setCountry("India");
            address.setPostalCode("411001");
            address.setStreet("Bund Garden Road");

            manager = new Manager();
            manager.setId(++counter);
            manager.setName("Munde");
            manager.setPhoneNumber("12345");
            manager.setEmailAddress("abc@abc.com");
            manager.setSalary(900000);
            manager.setDepartment(department);
            manager.setPermanentAddress(address);
            manager.setPresentAddress(address);

            managerEntity = EmployeeEntity.convert(manager);

            address = new Address();
            address.setId(++counter);
            address.setCity("Pune");
            address.setState("Maharashtra");
            address.setCountry("India");
            address.setPostalCode("4110014");
            address.setStreet("Kharadi byass");

            employee = new Employee();
            employee.setId(++counter);
            employee.setName("Rupali");
            employee.setPhoneNumber("67890");
            employee.setEmailAddress("xyz@xyz.com");
            employee.setSalary(400000);
            employee.setDepartment(department);
            employee.setPermanentAddress(address);
            employee.setPresentAddress(address);
            employee.setManager(manager);

            employeeEntity = EmployeeEntity.convert(employee);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCreate() {
        new Expectations() {{
            mockedDBManager.persist(withAny(employeeEntity));
            result = employeeEntity;
            times = 1;
        }};
        EmployeeService service = new EmployeeService(mockedDBManager);
        AbstractEmployee createdEmployee = service.create(employee);

        assertNotNull(createdEmployee);
        assertEquals(employee, createdEmployee);
    }

    @Test
    @Ignore
    public void testDelete() {
        new Expectations() {
            {
                mockedDBManager.find(EmployeeEntity.class, employeeEntity.getId());
                result = employeeEntity;
                times = 1;
            }
            {
                mockedDBManager.remove(EmployeeEntity.class, employeeEntity.getId());
                result = null;
                times = 1;
            }
        };
        EmployeeService service = new EmployeeService(mockedDBManager);
        service.delete(employee.getId());
    }

    @Test
    @Ignore
    public void testGet() {
        new Expectations() {{
            mockedDBManager.find(EmployeeEntity.class, employeeEntity.getId());
            result = employeeEntity;
            times = 1;
        }};
        EmployeeService service = new EmployeeService(mockedDBManager);
        AbstractEmployee retrievedEmployee = service.get(employee.getId());
        assertEquals(employee, retrievedEmployee);
    }

    @Test
    @Ignore
    public void testUpdate() {
        new Expectations() {
            {
                mockedDBManager.find(EmployeeEntity.class, employeeEntity.getId());
                result = employeeEntity;
                times = 1;
            }
            {
                mockedDBManager.merge(employeeEntity);
                result = null;
                times = 1;
            }
        };
        EmployeeService service = new EmployeeService(mockedDBManager);
        employee.setName("Rupali Pawar");
        service.update(employee);
    }

    @Test
    @Ignore
    public void testDelete_ManagerHavingSubordinates() {
        new Expectations() {{
                mockedDBManager.find(EmployeeEntity.class, managerEntity.getId());
                result = managerEntity;
                times = 1;
            }
            {
                mockedDBManager.findAll(EmployeeEntity.class);
                result = List.of(employeeEntity);
                times = 1;
            }
        };
        EmployeeService service = new EmployeeService(mockedDBManager);
        try {
            service.delete(manager.getId());
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
}
