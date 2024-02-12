package com.example.automapping_lab.services;

import com.example.automapping_lab.entities.Employee;
import com.example.automapping_lab.entities.dtos.EmployeeManagerDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void save(Employee employee);

    Optional<Employee> findOneById(int id);

    List<EmployeeManagerDTO> findEmployeeBornBefore(int year);

    EmployeeManagerDTO findOneByFirstAndLastName(Employee manager);
}
