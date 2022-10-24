package pro.sky.java.course2.hw_2_13.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.hw_2_13.service.DepartmentService;


@RestController
@RequestMapping("/employee/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public String getMaxSalaryByDepartment(@RequestParam int departmentId) {
        return departmentService.getMaxSalaryByDepartment(departmentId).toString();
    }

    @GetMapping("/min-salary")
    public String getMinSalaryByDepartment(@RequestParam int departmentId) {
        return departmentService.getMinSalaryByDepartment(departmentId).toString();
    }

    @GetMapping(value="/all", params = { "departmentId" })
    public String printAllByDepartment(@RequestParam(value = "departmentId") int departmentId) {
        return departmentService.findAllByDepartment(departmentId).toString();
    }

    @GetMapping("/all")
    public StringBuilder printAll() {
        return departmentService.printAllByDepartments();
    }
}
