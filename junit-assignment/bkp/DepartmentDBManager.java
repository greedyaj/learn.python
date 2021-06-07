package com.ptc.assignment.junit.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ptc.assignment.junit.model.Department;
import com.ptc.assignment.junit.model.HRDepartment;
import com.ptc.assignment.junit.model.RnDDepartment;
import com.ptc.assignment.junit.model.SalesDepartment;

public class DepartmentDBManager extends AbstractDBManager {

    public DepartmentDBManager() throws IOException {
        super();
    }
    
    public boolean create(Department dept) {
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();) 
        {
            System.out.println("inserting department");          
            String sql = String.format("INSERT INTO department (id, name) " + 
                "VALUES ('%s', '%s')", String.valueOf(dept.getOrgId()), String.valueOf(dept.getName()));
            if (stmt.executeUpdate(sql) > 0) {
                System.out.println("inserted department");
                return true;
            }
            else {
                System.out.println("failed to insert department");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Department get(int id) {
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM department WHERE id='%s'", String.valueOf(id)));) 
        {	
            if (!rs.next()) {
                Department.Type deptType = Department.Type.valueOf(rs.getString("name"));
                Department department = null;
                switch (deptType) {
                    case HR: department = new HRDepartment(Integer.valueOf(rs.getString("id")));
                        break;
                    case R_AND_D: department = new RnDDepartment(Integer.valueOf(rs.getString("id")));
                        break;
                    case SALES: department = new SalesDepartment(Integer.valueOf(rs.getString("id")));
                        break;
                }
                return department;
            } else {
                throw new IllegalArgumentException(String.format("Department with id [%s] not found", String.valueOf(id)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Department getByName(Department.Type name) {
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM department WHERE name='%s'", String.valueOf(name)));) 
        {	
            if (!rs.next()) {
                Department.Type deptType = Department.Type.valueOf(rs.getString("name"));
                Department department = null;
                switch (deptType) {
                    case HR: department = new HRDepartment(Integer.valueOf(rs.getString("id")));
                        break;
                    case R_AND_D: department = new RnDDepartment(Integer.valueOf(rs.getString("id")));
                        break;
                    case SALES: department = new SalesDepartment(Integer.valueOf(rs.getString("id")));
                        break;
                }
                return department;
            } else {
                throw new IllegalArgumentException(String.format("Department with name [%s] not found", String.valueOf(name)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public void update(Department addr) {
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();)
        {
            System.out.println("updating address");          
            String sql = String.format("UPDATE department SET city='%s', state='%s', country='%s', postalcode='%s', street='%s' WHERE id='%s'" 
                , addr.getCity(), addr.getState(), addr.getCountry(), addr.getStreet(), addr.getPostalCode(), addr.getI);
            stmt.executeUpdate(sql);
            System.out.println("updated address");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public boolean delete(int id) {
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();)
        {
            String sql = String.format("DELETE FROM department WHERE id='%s'", String.valueOf(id));
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println("successfull deleted department with id -" + id);
                return false;
            } else {
                System.out.println("failed to deleted department with id -" + id);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
