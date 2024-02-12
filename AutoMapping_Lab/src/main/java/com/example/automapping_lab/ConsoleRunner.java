package com.example.automapping_lab;

import com.example.automapping_lab.entities.Address;
import com.example.automapping_lab.entities.Employee;
import com.example.automapping_lab.entities.EmployeeTest;
import com.example.automapping_lab.entities.dtos.EmployeeDTO;
import com.example.automapping_lab.entities.dtos.EmployeeManagerDTO;
import com.example.automapping_lab.entities.dtos.ManagerDTO;
import com.example.automapping_lab.services.AddressService;
import com.example.automapping_lab.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final AddressService addressService;
    private final EmployeeService employeeService;

    @Autowired
    public ConsoleRunner(AddressService addressService, EmployeeService employeeService) {
        this.addressService = addressService;
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ModelMapper mapper = new ModelMapper();

//        addEmployeeAndManagerToDB(scanner);

//        1.	Simple Mapping
//        simpleMapping(mapper);

//        2.	Advanced Mapping
//        advanceMapping(mapper);

//        3.	Projection
        printAllEmployeesBornBeforeYear(mapper, scanner);


    }

    private void printAllEmployeesBornBeforeYear(ModelMapper mapper, Scanner scanner) {
        System.out.print("Insert the wanted year: ");
        int year = Integer.parseInt(scanner.nextLine());
        List<EmployeeManagerDTO> employees = this.employeeService.findEmployeeBornBefore(year);
        if (!employees.isEmpty()) {
            employees.stream().map(em -> mapper.map(em, EmployeeManagerDTO.class))
                    .forEach(System.out::println);
        } else {
            System.out.println("No founded Employees!");
        }
    }

    private static void advanceMapping(ModelMapper mapper) {
        EmployeeTest manager = new EmployeeTest(
                "Steve",
                "Jobbsen",
                new BigDecimal(8000),
                LocalDate.of(1969, 11, 11),
                true,
                new Address("Bulgaria", "Sofia"));

        EmployeeTest firsEmployee = new EmployeeTest(
                "Stephen",
                "Bjorn",
                new BigDecimal(4300),
                LocalDate.of(1987, 7, 15),
                true,
                new Address("Bulgaria", "Pleven"));

        EmployeeTest secondEmployee = new EmployeeTest(
                "Kirilyc",
                "Lefi",
                new BigDecimal(4400),
                LocalDate.of(1988, 6, 15),
                true,
                new Address("Bulgaria", "Pleven"));

        manager.addEmployee(firsEmployee);
        manager.addEmployee(secondEmployee);

        ManagerDTO managerDTO = mapper.map(manager, ManagerDTO.class);
        System.out.println(managerDTO);
    }

    private static void simpleMapping(ModelMapper mapper) {
        Address address = new Address("Bulgaria", "Sofia");

        EmployeeTest source = new EmployeeTest(
                "firsName",
                "lastName",
                new BigDecimal(50000),
                LocalDate.of(1986, 5, 23),
                address);

        EmployeeDTO employeeDTO = mapper.map(source, EmployeeDTO.class);
        System.out.println(employeeDTO);
    }


    private void addEmployeeAndManagerToDB(Scanner scanner) {
        System.out.print("Insert First name: ");
        String firsName = scanner.nextLine();
        System.out.print("Insert Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Insert salary: ");
        BigDecimal salary = new BigDecimal(scanner.nextLine());
        System.out.print("Insert date birthday: ");
        LocalDate birthday = LocalDate.parse(scanner.nextLine());

        System.out.print("Insert country: ");
        String country = scanner.nextLine();
        System.out.print("Insert city: ");
        String city = scanner.nextLine();
        Address address = new Address(country, city);

        Employee newManager = createEmployeeManagerToDB();

        EmployeeManagerDTO employeeManager = this.employeeService.findOneByFirstAndLastName(newManager);
        Employee employee;
        if (employeeManager != null) {
            employee = new Employee(firsName, lastName, salary, birthday, address);
        }else {
            employee = new Employee(firsName, lastName, salary, birthday, address, newManager);
        }

        this.employeeService.save(employee);
    }
    private Employee createEmployeeManagerToDB(){
        Employee newManager = new Employee(
                "Steve",
                "Jobbsen",
                new BigDecimal("6000.20"),
                LocalDate.of(1977, 10, 21),
                new Address("Germany", "Munch"),
                null);
        return newManager;
    }
}
