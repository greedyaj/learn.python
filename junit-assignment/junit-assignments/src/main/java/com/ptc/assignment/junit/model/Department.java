package com.ptc.assignment.junit.model;

public interface Department {
    public Integer getOrgId();
    public Type getName();

    public static enum Type {
        R_AND_D,
        SALES,
        HR,
    }
}
