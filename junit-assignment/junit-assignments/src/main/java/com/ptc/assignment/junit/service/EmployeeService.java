package com.ptc.assignment.junit.service;

import com.ptc.assignment.junit.model.AbstractEmployee;
import com.ptc.assignment.junit.persistence.DBManager;
import com.ptc.assignment.junit.persistence.entities.EmployeeEntity;

public class EmployeeService {
    
    DBManager dbManager = new DBManager();

    public EmployeeService() {}

    public EmployeeService(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public AbstractEmployee create(AbstractEmployee employee) {
        try {
            EmployeeEntity employeeEntity = EmployeeEntity.convert(employee);
            EmployeeEntity createdEmployeeEntity = dbManager.persist(employeeEntity);
            AbstractEmployee createdEmployee = EmployeeEntity.convert(createdEmployeeEntity);
            
            return createdEmployee;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public AbstractEmployee get(int id) {
        try {
            EmployeeEntity employeeEntity = dbManager.find(EmployeeEntity.class, id);
            AbstractEmployee employee = EmployeeEntity.convert(employeeEntity);
            return employee;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(AbstractEmployee employee) {
        if (employee.getId() == null) {
            throw new IllegalArgumentException("id is null");
        }
        if (dbManager.find(EmployeeEntity.class, employee.getId()) == null) {
            throw new IllegalArgumentException("Employee with id " + employee.getId() + " is not found");
        }
        try {
            EmployeeEntity employeeEntity = EmployeeEntity.convert(employee);
            dbManager.merge(employeeEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        EmployeeEntity employeeEntity = dbManager.find(EmployeeEntity.class, id);
        if (employeeEntity == null) {
            throw new IllegalArgumentException("Employee with id " + id + "not found");
        }
        // if employee is a manager
        if (employeeEntity.getManager() == null) {
            for (EmployeeEntity eachEmployeeEntity : dbManager.findAll(EmployeeEntity.class)) {
                // if employee being deleted is a manager and has other employees under him/her, can't delete it
                if (eachEmployeeEntity.getManager() != null && 
                    eachEmployeeEntity.getManager().getId().equals(employeeEntity.getId()))
                {
                    throw new IllegalArgumentException("Employee with id " + id + "is a manager and has other employees under him/her. " +
                        "To delete this manager first delete/move the employees under him/her");
                }    
            }
        }
        dbManager.remove(EmployeeEntity.class, id);
    }
}
