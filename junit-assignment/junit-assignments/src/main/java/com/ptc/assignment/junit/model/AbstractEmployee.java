package com.ptc.assignment.junit.model;

import java.util.List;

public abstract class AbstractEmployee implements Person {
 
    private Integer id;
    private String name;
    private Address presentAddress;
    private Address permanentAddress;
    private String emailAddress;
    private String phoneNumber;
    private double salary;
    private Department department;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getPresentAddress() {
        return presentAddress;
    }
    public void setPresentAddress(Address presentAddress) {
        this.presentAddress = presentAddress;
    }
    public Address getPermanentAddress() {
        return permanentAddress;
    }
    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public List<Address> getAddresses() {
        return List.of(presentAddress, permanentAddress);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((department == null) ? 0 : department.hashCode());
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((permanentAddress == null) ? 0 : permanentAddress.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + ((presentAddress == null) ? 0 : presentAddress.hashCode());
        long temp;
        temp = Double.doubleToLongBits(salary);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractEmployee other = (AbstractEmployee) obj;
        if (department == null) {
            if (other.department != null)
                return false;
        } else if (!department.equals(other.department))
            return false;
        if (emailAddress == null) {
            if (other.emailAddress != null)
                return false;
        } else if (!emailAddress.equals(other.emailAddress))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (permanentAddress == null) {
            if (other.permanentAddress != null)
                return false;
        } else if (!permanentAddress.equals(other.permanentAddress))
            return false;
        if (phoneNumber == null) {
            if (other.phoneNumber != null)
                return false;
        } else if (!phoneNumber.equals(other.phoneNumber))
            return false;
        if (presentAddress == null) {
            if (other.presentAddress != null)
                return false;
        } else if (!presentAddress.equals(other.presentAddress))
            return false;
        if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
            return false;
        return true;
    }

    
}
