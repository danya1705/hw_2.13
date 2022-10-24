package pro.sky.java.course2.hw_2_13.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.hw_2_13.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/find")
    public String showFindedEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName).toString();
    }

    @GetMapping("/add")
    public String addEmployeeToList(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam int departmentId,
            @RequestParam double salary) {
        return employeeService.addEmployee(firstName, lastName, departmentId, salary).toString();
    }

    @GetMapping("/remove")
    public String removeEmployeeFromList(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.removeEmployee(firstName, lastName).toString();
    }

    @GetMapping("")
    public String printList() {
        return employeeService.getEmployeeList().toString();
    }


}
