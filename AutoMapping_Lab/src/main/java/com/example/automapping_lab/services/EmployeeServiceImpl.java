package com.example.automapping_lab.services;

import com.example.automapping_lab.entities.Employee;
import com.example.automapping_lab.entities.dtos.EmployeeManagerDTO;
import com.example.automapping_lab.repositories.AddressRepository;
import com.example.automapping_lab.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void save(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findOneById(int id) {
        return this.employeeRepository.findById(id);
    }

    @Override
    public List<EmployeeManagerDTO> findEmployeeBornBefore(int year) {
        LocalDate dateBefore = LocalDate.of(year, 1,1);
        return this.employeeRepository.findAllByBirthdayBeforeOrderBySalaryDesc(dateBefore);
    }

    @Override
    public EmployeeManagerDTO findOneByFirstAndLastName(Employee manager) {
        String firstName = manager.getFirstName();
        String lastName = manager.getLastName();
        return this.employeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
