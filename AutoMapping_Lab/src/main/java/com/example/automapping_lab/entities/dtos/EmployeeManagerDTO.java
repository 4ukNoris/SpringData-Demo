package com.example.automapping_lab.entities.dtos;

import java.math.BigDecimal;

public class EmployeeManagerDTO {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private String managerLastName;

    public EmployeeManagerDTO(String firstName, String lastName, BigDecimal salary, String managerLastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerLastName = managerLastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f - Manager: %s",
                this.firstName, this.lastName, this.salary,
                this.managerLastName == null ? "[no manager]" : this.managerLastName);
    }
}
