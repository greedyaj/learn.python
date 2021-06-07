package com.ptc.assignment.junit.service;

import com.ptc.assignment.junit.model.Address;
import com.ptc.assignment.junit.persistence.DBManager;
import com.ptc.assignment.junit.persistence.entities.AddressEntity;

import org.apache.commons.beanutils.BeanUtils;

public class AddressService {
    
    DBManager dbManager = new DBManager();

    public AddressService() {}

    public AddressService(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public Address create(Address address) {
        try {
            AddressEntity addressEntity = new AddressEntity();
            BeanUtils.copyProperties(addressEntity, address);

            AddressEntity createAddressEntity = this.dbManager.persist(addressEntity);

            Address createAddress = new Address();
            BeanUtils.copyProperties(createAddress, createAddressEntity);

            return createAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Address get(int id) {
        try {
            AddressEntity addressEntity = this.dbManager.find(AddressEntity.class, id);
            Address address = new Address();
            BeanUtils.copyProperties(address, addressEntity);
            return address;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Address address) {
        if (address.getId() == null) {
            throw new IllegalArgumentException("id is null");
        }
        if (dbManager.find(AddressEntity.class, address.getId()) == null) {
            throw new IllegalArgumentException("Address with id " + address.getId() + " is not found");
        }
        try {
            AddressEntity addressEntity = new AddressEntity();
            BeanUtils.copyProperties(addressEntity, address);
            this.dbManager.merge(addressEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        this.dbManager.remove(AddressEntity.class, id);
    }
}
