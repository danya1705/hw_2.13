package pro.sky.java.course2.hw_2_13.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.hw_2_13.exception.BadNamingException;
import pro.sky.java.course2.hw_2_13.exception.EmployeeAlreadyAddedException;
import pro.sky.java.course2.hw_2_13.exception.EmployeeNotFoundException;
import pro.sky.java.course2.hw_2_13.model.Employee;

import static org.assertj.core.api.Assertions.*;

class EmployeeServiceTest {

    private final EmployeeService employeeservice = new EmployeeService();

    @AfterEach
    void cleanListAfterTest() {
        employeeservice.getEmployeeList().clear();
    }

    @Test
    void shouldReturnEmployeeAlreadyAddedExceptionWhenTryToAddAlreadyAdded() {

        Employee expected = new Employee("Ivan", "Ivanov", 1, 405.00);

        assertThat(employeeservice.getEmployeeList())
                .isEmpty();
        employeeservice.addEmployee(expected.getFirstName(), expected.getLastName(), expected.getDepartmentId(), expected.getSalary());
        assertThat(employeeservice.getEmployeeList())
                .hasSize(1)
                .containsExactly(expected);
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeservice.addEmployee(expected.getFirstName(), expected.getLastName(), expected.getDepartmentId(), expected.getSalary()));
    }

    @Test
    void addEmployeePositiveTest() {

        Employee expected = new Employee("Ivan", "Ivanov", 1, 405.00);

        assertThat(employeeservice.getEmployeeList())
                .isEmpty();
        employeeservice.addEmployee(expected.getFirstName(), expected.getLastName(), expected.getDepartmentId(), expected.getSalary());
        assertThat(employeeservice.getEmployeeList())
                .hasSize(1)
                .containsExactly(expected);
    }

    @Test
    void shouldReturnEmployeeNotFoundExceptionWhenTryToFindNotExisting() {

        Employee expected = new Employee("Ivan", "Ivanov", 1, 405.00);

        assertThat(employeeservice.getEmployeeList())
                .isEmpty();
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeservice.findEmployee("Petr", "Petrov"));
        employeeservice.addEmployee(expected.getFirstName(), expected.getLastName(), expected.getDepartmentId(), expected.getSalary());
        assertThat(employeeservice.getEmployeeList())
                .hasSize(1)
                .containsExactly(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeservice.findEmployee("Petr", "Petrov"));
    }

    @Test
    void findEmployeePositiveTest() {

        Employee expected = new Employee("Ivan", "Ivanov", 1, 405.00);

        assertThat(employeeservice.getEmployeeList())
                .isEmpty();
        employeeservice.addEmployee(expected.getFirstName(), expected.getLastName(), expected.getDepartmentId(), expected.getSalary());
        assertThat(employeeservice.getEmployeeList())
                .hasSize(1)
                .containsExactly(expected);
        assertThat(employeeservice.findEmployee(expected.getFirstName(), expected.getLastName()))
                .isEqualTo(expected);
    }

    @Test
    void shouldReturnEmployeeNotFoundExceptionWhenTryToRemoveNotExisting() {

        Employee expected = new Employee("Ivan", "Ivanov", 1, 405.00);

        assertThat(employeeservice.getEmployeeList())
                .isEmpty();
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeservice.removeEmployee("Petr", "Petrov"));
        employeeservice.addEmployee(expected.getFirstName(), expected.getLastName(), expected.getDepartmentId(), expected.getSalary());
        assertThat(employeeservice.getEmployeeList())
                .hasSize(1)
                .containsExactly(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeservice.removeEmployee("Petr", "Petrov"));
    }


    @Test
    void removeEmployeePositiveTest() {

        Employee expected = new Employee("Ivan", "Ivanov", 1, 405.00);

        assertThat(employeeservice.getEmployeeList()).isEmpty();
        employeeservice.addEmployee(expected.getFirstName(), expected.getLastName(), expected.getDepartmentId(), expected.getSalary());
        assertThat(employeeservice.getEmployeeList())
                .hasSize(1)
                .containsExactly(expected);

        assertThat(employeeservice.removeEmployee(expected.getFirstName(), expected.getLastName()))
                .isEqualTo(expected);
        assertThat(employeeservice.getEmployeeList())
                .isEmpty();
    }

    @Test
    void shouldReturnBadNamingExceptionWhenTryToAddBadName() {
        assertThatExceptionOfType(BadNamingException.class).
                isThrownBy(() -> EmployeeService.checkNames("Anna-Maria", "Ivanova"));
        assertThatExceptionOfType(BadNamingException.class).
                isThrownBy(() -> EmployeeService.checkNames("Anna", "Ivanova2"));
        assertThatExceptionOfType(BadNamingException.class).
                isThrownBy(() -> EmployeeService.checkNames(null, "Ivanova"));
        assertThatExceptionOfType(BadNamingException.class).
                isThrownBy(() -> EmployeeService.checkNames("Anna", null));
    }
}