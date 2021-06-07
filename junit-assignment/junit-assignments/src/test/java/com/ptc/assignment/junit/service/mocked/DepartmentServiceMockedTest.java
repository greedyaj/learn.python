package com.ptc.assignment.junit.service.mocked;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.ptc.assignment.junit.model.Department;
import com.ptc.assignment.junit.model.HRDepartment;
import com.ptc.assignment.junit.model.SalesDepartment;
import com.ptc.assignment.junit.persistence.DBManager;
import com.ptc.assignment.junit.persistence.entities.DepartmentEntity;
import com.ptc.assignment.junit.persistence.entities.EmployeeEntity;
import com.ptc.assignment.junit.service.DepartmentService;

import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DepartmentServiceMockedTest {

    @Mocked
    protected DBManager mockedDBManager;

    private static int counter;

    protected Department department;
    protected DepartmentEntity departmentEntity;

    @Before
    public void create() {
        department = new SalesDepartment(++counter);
        departmentEntity = new DepartmentEntity();

        departmentEntity.setId(department.getOrgId());
        departmentEntity.setName(department.getName().toString());
    }

    @Test
    public void testCreate() {
        new Expectations() {{
            mockedDBManager.persist(withAny(DepartmentEntity.class));
            result = departmentEntity;
            times = 1;
        }};
        DepartmentService service = new DepartmentService(mockedDBManager);
        Department createdDepartment = service.create(department);

        assertNotNull(createdDepartment);
        assertEquals(department.getOrgId(), createdDepartment.getOrgId());
        assertEquals(department.getName(), createdDepartment.getName());
    }

    @Test
    public void testDelete() {
        new Expectations() {{
            mockedDBManager.remove(DepartmentEntity.class, departmentEntity.getId());
            result = null;
            times = 1;
        }};
        DepartmentService service = new DepartmentService(mockedDBManager);
        service.delete(department.getOrgId());
    }

    @Test
    public void testGet() {
        new Expectations() {{
            mockedDBManager.find(DepartmentEntity.class, departmentEntity.getId());
            result = departmentEntity;
            times = 1;
        }};
        DepartmentService service = new DepartmentService(mockedDBManager);
        Department retrieveDepartment = service.get(department.getOrgId());

        assertNotNull(retrieveDepartment);
        assertEquals(department.getOrgId(), retrieveDepartment.getOrgId());
        assertEquals(department.getName(), retrieveDepartment.getName());
    }

    @Test
    public void testUpdate() {
        new Expectations() {
            {
                mockedDBManager.find(DepartmentEntity.class, departmentEntity.getId());
                result = departmentEntity;
                times = 1;
            }
            {
                mockedDBManager.merge(departmentEntity);
                result = departmentEntity;
                times = 1;
            }
        };
        DepartmentService service = new DepartmentService(mockedDBManager);
        Department udatedDepartment = new HRDepartment(department.getOrgId());
        service.update(udatedDepartment);
    }

    @Test
    public void testDelete_HavingEmployees() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setDepartment(departmentEntity);
        new Expectations() {
            {
                mockedDBManager.findAll(EmployeeEntity.class);
                result = List.of(employeeEntity);
                times = 1;
            }
        };
        DepartmentService service = new DepartmentService(mockedDBManager);
        try {
            service.delete(department.getOrgId());
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
}
