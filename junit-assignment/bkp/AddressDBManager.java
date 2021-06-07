package com.ptc.assignment.junit.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ptc.assignment.junit.model.Address;

public class AddressDBManager extends AbstractDBManager {

    public AddressDBManager() throws IOException {
        super();
    }
    
    public boolean create(Address addr) {
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();) 
        {
            System.out.println("inserting address");          
            String sql = String.format("INSERT INTO address (city, state, country, street, postalcode) " + 
                "VALUES ('%s', '%s', '%s', '%s', '%s')", addr.getCity(), addr.getState(), addr.getCountry(), addr.getStreet(), addr.getPostalCode());
            if (stmt.executeUpdate(sql) > 0) {
                System.out.println("inserted address");
                return true;
            }
            else {
                System.out.println("failed to insert address");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Address get(int id) {
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM address WHERE id='%s'", String.valueOf(id)));) 
        {	
            if (rs.next()) {
                Address address = new Address();
                address.setId(Integer.parseInt(rs.getString("id")));
                address.setCity(rs.getString("city"));
                address.setState(rs.getString("state"));
                address.setCountry(rs.getString("country"));
                address.setStreet(rs.getString("street"));
                address.setPostalCode(rs.getString("postalcode"));
                return address;
            } else {
                throw new IllegalArgumentException(String.format("Address with id [%s] not found", String.valueOf(id)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Address addr) {
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();)
        {
            System.out.println("updating address");          
            String sql = String.format("UPDATE address SET city='%s', state='%s', country='%s', postalcode='%s', street='%s' WHERE id='%s'" 
                , addr.getCity(), addr.getState(), addr.getCountry(), addr.getStreet(), addr.getPostalCode(), addr.getId());
            stmt.executeUpdate(sql);
            System.out.println("updated address");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(int id) {
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();)
        {
            String sql = String.format("DELETE FROM address WHERE id='%s'", String.valueOf(id));
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println("successfull deleted address with id -" + id);
                return false;
            } else {
                System.out.println("failed to deleted address with id -" + id);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
