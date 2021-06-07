package com.ptc.assignment.junit.service;

import com.ptc.assignment.junit.model.Department;
import com.ptc.assignment.junit.model.HRDepartment;
import com.ptc.assignment.junit.model.RnDDepartment;
import com.ptc.assignment.junit.model.SalesDepartment;
import com.ptc.assignment.junit.persistence.DBManager;
import com.ptc.assignment.junit.persistence.entities.DepartmentEntity;
import com.ptc.assignment.junit.persistence.entities.EmployeeEntity;


public class DepartmentService {
    
    DBManager dbManager = new DBManager();

    public DepartmentService() {}

    public DepartmentService(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public Department create(Department department) {
        try {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setId(department.getOrgId());
            departmentEntity.setName(department.getName().toString());

            DepartmentEntity createdDepAddressEntity = dbManager.persist(departmentEntity);

            Department createdDepartment = null;
            switch (Department.Type.valueOf(createdDepAddressEntity.getName())) {
                case HR: createdDepartment = new HRDepartment(createdDepAddressEntity.getId());
                    break;
                case SALES: createdDepartment = new SalesDepartment(createdDepAddressEntity.getId());
                    break;
                case R_AND_D: createdDepartment = new RnDDepartment(createdDepAddressEntity.getId());
                    break;
            }
            return createdDepartment;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Department get(int id) {
        try {
            DepartmentEntity departmentEntity = dbManager.find(DepartmentEntity.class, id);
            Department department = null;
            switch (Department.Type.valueOf(departmentEntity.getName())) {
                case HR: department = new HRDepartment(departmentEntity.getId());
                    break;
                case SALES: department = new SalesDepartment(departmentEntity.getId());
                    break;
                case R_AND_D: department = new RnDDepartment(departmentEntity.getId());
                    break;
            }
            return department;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Department department) {
        if (department.getOrgId() == null) {
            throw new IllegalArgumentException("id is null");
        }
        if (dbManager.find(DepartmentEntity.class, department.getOrgId()) == null) {
            throw new IllegalArgumentException("Department with id " + department.getOrgId() + " is not found");
        }
        try {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setId(department.getOrgId());
            departmentEntity.setName(department.getName().toString());

            dbManager.merge(departmentEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        DepartmentEntity departmentEntity = dbManager.find(DepartmentEntity.class, id);
        if (departmentEntity == null) {
            throw new IllegalArgumentException("Department with id " + id + "not found");
        }
        
        for (EmployeeEntity eachEmployeeEntity : dbManager.findAll(EmployeeEntity.class)) {
            // if department being deleted has employees, can't delete it
            if (eachEmployeeEntity.getDepartment() != null && 
                eachEmployeeEntity.getDepartment().getId().equals(departmentEntity.getId()))
            {
                throw new IllegalArgumentException("Department with id " + id + " has employees under it. " +
                    "To delete this department first delete/move the employees under it.");
            }    
        }
        
        dbManager.remove(DepartmentEntity.class, id);
    }
}
