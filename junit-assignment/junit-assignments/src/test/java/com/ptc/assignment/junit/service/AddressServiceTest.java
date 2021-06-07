package com.ptc.assignment.junit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.ptc.assignment.junit.model.Address;

import org.junit.Before;
import org.junit.Test;

public class AddressServiceTest {
    
    private Address address;
    private AddressService service = new AddressService();

    @Before
    public void create() {
        address = new Address();
        address.setCity("Pune");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setPostalCode("411001");
        address.setStreet("Bund Garden Road");
        
        address = service.create(address);
    }

    @Test
    public void testCreate() {
        assertNotNull(address);
        assertNotNull(address.getId());
    }

    @Test
    public void testDelete() {
        service.delete(address.getId());
    }

    @Test
    public void testGet() {
        Address retrievedAddress = service.get(address.getId());
        assertEquals(address, retrievedAddress);
    }

    @Test
    public void testUpdate() {
        address.setPostalCode("411014");
        service.update(address);
    }
}
