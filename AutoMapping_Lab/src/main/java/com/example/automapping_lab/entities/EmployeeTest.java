package com.example.automapping_lab.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class EmployeeTest {
    private String firstName;

    private String lastName;

    private BigDecimal salary;

    private LocalDate birthday;

    private boolean onVacation;

    private Address address;
    private Set<EmployeeTest> subordinates;

    public EmployeeTest() {
        this.subordinates = new HashSet<>();
    }

    public EmployeeTest(String firstName, String lastName, BigDecimal salary, LocalDate birthday, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
        this.address = address;
    }

    public EmployeeTest(String firstName, String lastName, BigDecimal salary, LocalDate birthday, boolean onVacation, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
        this.onVacation = onVacation;
        this.address = address;
        this.subordinates = new HashSet<>();
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean isOnVacation() {
        return onVacation;
    }

    public void setOnVacation(boolean onVacation) {
        this.onVacation = onVacation;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public void addEmployee(EmployeeTest employee) {
        this.subordinates.add(employee);
    }

    public Set<EmployeeTest> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Set<EmployeeTest> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public String toString() {
        return String.format("   - %s %s %.2f", this.firstName, this.lastName, this.salary);
    }
}
