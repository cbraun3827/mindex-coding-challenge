package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {
    private String employeeId;
    private Integer salary;
    private Date effectiveDate;

    public String getEmployee(){
        return employeeId;
    }
    public void setEmployee(String employeeId) {
        this.employeeId = employeeId;
    }
    public Integer getSalary(){
        return salary;
    }
    public void setSalary(Integer salary){
        this.salary = salary;
    }
    public Date getEffectiveDate(){
        return effectiveDate;
    }
    public void setEffectiveDate(Date effectiveDate){
        this.effectiveDate = effectiveDate;
    }

}
