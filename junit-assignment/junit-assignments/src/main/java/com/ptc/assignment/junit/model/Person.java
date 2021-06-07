package com.ptc.assignment.junit.model;

import java.util.List;

public interface Person {
    public String getName();

    public String getEmailAddress();

    public String getPhoneNumber();

    public double getSalary();

    public List<Address> getAddresses();
}
