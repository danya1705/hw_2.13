package pro.sky.java.course2.hw_2_13.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import pro.sky.java.course2.hw_2_13.exception.BadNamingException;
import pro.sky.java.course2.hw_2_13.exception.EmployeeAlreadyAddedException;
import pro.sky.java.course2.hw_2_13.exception.EmployeeNotFoundException;
import pro.sky.java.course2.hw_2_13.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employeeList;

    public EmployeeService() {
        employeeList = new ArrayList<>(
                List.of(
                        new Employee(
                                "Ivan",
                                "Ivanov",
                                1,
                                1000.0),
                        new Employee(
                                "Petr",
                                "Petrov",
                                1,
                                2000.0),
                        new Employee(
                                "Sidor",
                                "Sidorov",
                                2,
                                3000.0),
                        new Employee(
                                "Lena",
                                "Lenina",
                                2,
                                4000.0),
                        new Employee(
                                "Dart",
                                "Veider",
                                2,
                                5000.0)
                )
        );
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public Employee addEmployee(String firstName, String lastName, int departmentId, double salary) {

        checkNames(firstName, lastName);

        Employee employee = new Employee(
                StringUtils.capitalize(StringUtils.lowerCase(firstName)),
                StringUtils.capitalize(StringUtils.lowerCase(lastName)),
                departmentId,
                salary);

        if (this.employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        } else {
            this.employeeList.add(employee);
            return employee;
        }
    }

    public Employee findEmployee(String firstName, String lastName) {

        Employee employee = new Employee(firstName, lastName);

        if (this.employeeList.contains(employee)) {
            return this.employeeList.get(this.employeeList.indexOf(employee));
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    public Employee removeEmployee(String firstName, String lastName) {

        Employee employee = new Employee(firstName, lastName);

        if (this.employeeList.contains(employee)) {
            this.employeeList.remove(employee);
            return employee;
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    public static void checkNames(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new BadNamingException();
        }
    }
}
