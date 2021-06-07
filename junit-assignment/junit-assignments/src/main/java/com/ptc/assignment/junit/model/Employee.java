package com.ptc.assignment.junit.model;

public class Employee extends AbstractEmployee {
    
    private Manager manager;
    
    public Manager getManager() {
        return manager;
    }
    public void setManager(Manager manager) {
        this.manager = manager;
    }    
}
