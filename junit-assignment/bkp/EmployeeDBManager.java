package com.ptc.assignment.junit.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ptc.assignment.junit.model.AbstractEmployee;
import com.ptc.assignment.junit.model.Address;
import com.ptc.assignment.junit.model.Department;
import com.ptc.assignment.junit.model.Employee;
import com.ptc.assignment.junit.model.Manager;

public class EmployeeDBManager extends AbstractDBManager {

    private DepartmentDBManager departmentDBManager;
    private AddressDBManager addressDBManager;

    public EmployeeDBManager() throws IOException {
        super();
        this.departmentDBManager = new DepartmentDBManager();
        this.addressDBManager = new AddressDBManager();
    }
    
    public boolean create(AbstractEmployee employee) {
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();) 
        {
            Department department = this.departmentDBManager.getByName(employee.getDepartment().getName());
            if (employee instanceof Employee) {
                Employee e = (Employee) employee;
                if (e.getManager() == null) {
                    throw new IllegalArgumentException("Employee Manager is not specified");
                } else if (!(get(e.getManager().getId()) instanceof Manager)) {
                    throw new IllegalArgumentException("Employee Manager specified is not a Manager");
                }
            }

            if (employee.getPermanentAddress() != null) {
                this.addressDBManager.create(employee.getPermanentAddress());
            }
            

            System.out.println("inserting employee");      
            String sql = String.format("INSERT INTO employee (salary, deptId, email, phone, name) " + 
                "VALUES ('%s', '%s')", String.valueOf(employee.getSalary()), department.getOrgId(), 
                employee.getEmailAddress(), employee.getPhoneNumber(), employee.getName());
            if (stmt.executeUpdate(sql) > 0) {
                System.out.println("inserted employee");
                return true;
            }
            else {
                System.out.println("failed to insert employee");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public AbstractEmployee get(int id) {
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM employee WHERE id='%s'", String.valueOf(id)));) 
        {	
            Employee employee = null;
            
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(AbstractEmployee employee) {
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();)
        {
            /*System.out.println("updating address");          
            String sql = String.format("UPDATE employee SET city='%s', state='%s', country='%s', postalcode='%s', street='%s' WHERE id='%s'" 
                , employee.getCity(), addr.getState(), addr.getCountry(), addr.getStreet(), addr.getPostalCode(), addr.getI);
            stmt.executeUpdate(sql);
            System.out.println("updated address");*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(int id) {
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();)
        {
            String sql = String.format("DELETE FROM employee WHERE id='%s'", String.valueOf(id));
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println("successfull deleted employee with id -" + id);
                return false;
            } else {
                System.out.println("failed to deleted employee with id -" + id);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
