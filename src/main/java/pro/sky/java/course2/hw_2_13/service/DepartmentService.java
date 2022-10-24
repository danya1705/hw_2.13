package pro.sky.java.course2.hw_2_13.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.hw_2_13.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getMaxSalaryByDepartment(int departmentId) {
        return this.employeeService.getEmployeeList()
                .stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow();
    }

    public Employee getMinSalaryByDepartment(int departmentId) {
        return this.employeeService.getEmployeeList()
                .stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow();
    }

    public List<Employee> findAllByDepartment(int departmentId) {
        return this.employeeService.getEmployeeList()
                .stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .collect(Collectors.toList())
                ;
    }

    public StringBuilder printAllByDepartments() {
        StringBuilder result = new StringBuilder();
        for (Integer departmentId : employeeService.getEmployeeList()
                .stream()
                .map(Employee::getDepartmentId)
                .collect(Collectors.toSet())) {
            result.append("department #").append(departmentId).append("<br>");
            result.append(findAllByDepartment(departmentId).toString()).append("<br>---------------------<br>");
        }
        return result;
    }
}
