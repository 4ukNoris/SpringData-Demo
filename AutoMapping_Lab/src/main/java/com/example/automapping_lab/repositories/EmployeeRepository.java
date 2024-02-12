package com.example.automapping_lab.repositories;

import com.example.automapping_lab.entities.Employee;
import com.example.automapping_lab.entities.dtos.EmployeeManagerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<EmployeeManagerDTO> findAllByBirthdayBeforeOrderBySalaryDesc (LocalDate dateBefore);

    EmployeeManagerDTO findByFirstNameAndLastName(String firstName, String lastName);
}
