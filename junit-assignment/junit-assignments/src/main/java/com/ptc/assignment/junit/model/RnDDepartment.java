package com.ptc.assignment.junit.model;

public class RnDDepartment implements Department {
    private Integer orgId;
    
    public RnDDepartment(Integer orgId) {
        this.orgId = orgId;
    }

    @Override
    public Integer getOrgId() {
        return orgId;
    }
    @Override
    public Type getName() {
        return Type.R_AND_D;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
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
        RnDDepartment other = (RnDDepartment) obj;
        if (orgId == null) {
            if (other.orgId != null)
                return false;
        } else if (!orgId.equals(other.orgId))
            return false;
        return true;
    }
}
