package com.ptc.assignment.junit.service.helper;

import java.util.ArrayList;
import java.util.List;

import com.ptc.assignment.junit.model.AbstractEmployee;
import com.ptc.assignment.junit.persistence.DBManager;
import com.ptc.assignment.junit.persistence.entities.EmployeeEntity;

public class EmployeeServiceHelper {

    DBManager dbManager = new DBManager();

    public EmployeeServiceHelper() {}

    public EmployeeServiceHelper(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean isEmployeeWithNameExists(String name) {
        if (name == null) {
            return false;
        }

        for (EmployeeEntity eachEmployeeEntity : dbManager.findAll(EmployeeEntity.class)) {
            if (name.equals(eachEmployeeEntity.getName())) {
                return true;
            }
        }
        return false;
    }

    public List<AbstractEmployee> getEmployeesWithName(String name) {
        if (name == null) {
            return List.of();
        }
        List<AbstractEmployee> employees = new ArrayList<>();
        try {
            for (EmployeeEntity eachEmployeeEntity : dbManager.findAll(EmployeeEntity.class)) {
                if (name.equals(eachEmployeeEntity.getName())) {
                    employees.add(EmployeeEntity.convert(eachEmployeeEntity));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return employees;
    }
}
