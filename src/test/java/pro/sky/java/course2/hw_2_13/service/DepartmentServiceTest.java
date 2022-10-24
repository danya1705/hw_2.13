package pro.sky.java.course2.hw_2_13.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.hw_2_13.exception.DepartmentIsEmpty;
import pro.sky.java.course2.hw_2_13.model.Employee;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void createTestList() {
        List<Employee> employees = List.of(
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
                        "Darth",
                        "Vader",
                        2,
                        5000.0)
        );
        when(employeeService.getEmployeeList())
                .thenReturn(employees);
    }

    @Test
    void shouldReturnDepartmentIsEmptyWhenTryToGetMaxSalaryInEmptyDepartment() {
        assertThatExceptionOfType(DepartmentIsEmpty.class)
                .isThrownBy(() -> departmentService.getMaxSalaryByDepartment(3));
    }

    @ParameterizedTest
    @MethodSource("employeesWithMaxSalary")
    void getMaxSalaryByDepartmentPositiveTest(int id, Employee expected) {
        assertThat(departmentService.getMaxSalaryByDepartment(id))
                .isEqualTo(expected);
    }

    @Test
    void shouldReturnDepartmentIsEmptyWhenTryToGetMinSalaryInEmptyDepartment() {
        assertThatExceptionOfType(DepartmentIsEmpty.class)
                .isThrownBy(() -> departmentService.getMinSalaryByDepartment(3));
    }

    @ParameterizedTest
    @MethodSource("employeesWithMinSalary")
    void getMinSalaryByDepartmentPositiveTest(int id, Employee expected) {
        assertThat(departmentService.getMinSalaryByDepartment(id))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("employeesByDepartments")
    void findAllByDepartmentPositiveTest(int id, List<Employee> expected) {
        assertThat(departmentService.findAllByDepartment(id))
                .containsExactlyElementsOf(expected);
    }

    public static Stream<Arguments> employeesWithMaxSalary() {
        return Stream.of(
                Arguments.of(1, new Employee("Petr", "Petrov", 1, 2000.0)),
                Arguments.of(2, new Employee("Darth", "Vader", 2, 5000.0))
        );
    }

    public static Stream<Arguments> employeesWithMinSalary() {
        return Stream.of(
                Arguments.of(1, new Employee("Ivan", "Ivanov", 1, 1000.0)),
                Arguments.of(2, new Employee("Sidor", "Sidorov", 2, 3000.0))
        );
    }

    public static Stream<Arguments> employeesByDepartments() {
        return Stream.of(
                Arguments.of(1, List.of(
                        new Employee("Ivan", "Ivanov", 1, 1000.0),
                        new Employee("Petr", "Petrov", 1, 2000.0))),
                Arguments.of(2, List.of(
                        new Employee("Sidor", "Sidorov", 2, 3000.0),
                        new Employee("Lena", "Lenina", 2, 4000.0),
                        new Employee("Darth", "Vader", 2, 5000.0))),
                Arguments.of(3, List.of()));
    }
}