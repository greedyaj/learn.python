package com.ptc.assignment.junit.service.mocked;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.ptc.assignment.junit.model.Address;
import com.ptc.assignment.junit.persistence.DBManager;
import com.ptc.assignment.junit.persistence.entities.AddressEntity;
import com.ptc.assignment.junit.service.AddressService;

import mockit.Expectations;
import mockit.Mocked;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Before;
import org.junit.Test;

public class AddressServiceMockedTest {

    @Mocked
    protected DBManager mockedDBManager;

    private static int counter;

    protected AddressEntity addressEntity;
    private Address address;

    @Before
    public void create() {
        try {
            addressEntity = new AddressEntity();
            addressEntity.setId(++counter);
            addressEntity.setCity("Pune");
            addressEntity.setState("Maharashtra");
            addressEntity.setCountry("India");
            addressEntity.setPostalCode("411001");
            addressEntity.setStreet("Bund Garden Road");

            address = new Address();
            BeanUtils.copyProperties(address, addressEntity);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCreate() {
        new Expectations() {{
            mockedDBManager.persist(withAny(AddressEntity.class));
            result = addressEntity;
            times = 1;
        }};
        AddressService service = new AddressService(mockedDBManager);
        Address createdAddress = service.create(address);
        assertNotNull(createdAddress);
        assertEquals(createdAddress, address);
    }

    @Test
    public void testDelete() {
        new Expectations() {{
            mockedDBManager.remove(AddressEntity.class, addressEntity.getId());
            result = null;
            times = 1;
        }};
        AddressService service = new AddressService(mockedDBManager);
        service.delete(address.getId());
    }

    @Test
    public void testGet() {
        new Expectations() {{
            mockedDBManager.find(AddressEntity.class, addressEntity.getId());
            result = addressEntity;
            times = 1;
        }};
        AddressService service = new AddressService(mockedDBManager);
        Address retrievedAddress = service.get(address.getId());
        assertEquals(address, retrievedAddress);
    }

    @Test
    public void testUpdate() {
        new Expectations() {{
                mockedDBManager.find(AddressEntity.class, addressEntity.getId());
                result = addressEntity;
                times = 1;
            }
            {
                mockedDBManager.merge(addressEntity);
                result = null;
                times = 1;
            }
        };
        AddressService service = new AddressService(mockedDBManager);
        address.setPostalCode("411014");
        service.update(address);
    }
}
