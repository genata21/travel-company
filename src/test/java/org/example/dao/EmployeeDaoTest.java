package org.example.dao;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.example.enums.Category;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeDaoTest {

    private final Company company = new Company(1, LocalDate.now(), "DHL", "TestCompany");
    private List<String> validate(Employee employee) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(employee)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    @Test
    public void whenInvalidEmployeeName_firstLetterNotCapital_thenAssertConstraintViolation() {
        Employee employee = new Employee(1, LocalDate.now(), "employee", Category.BUS_DRIVER, BigDecimal.valueOf(1000), company);
        List<String> messages = validate(employee);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Employee name must start with a capital letter."));
    }

    @Test
    public void whenInvalidCompanyName_nameMissing_thenAssertConstraintViolation() {
        Employee employee = new Employee(1, LocalDate.now(), Category.BUS_DRIVER, BigDecimal.valueOf(1000), company);
        List<String> messages = validate(employee);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Employee must have a name."));
    }

    @Test
    public void whenCategoryIsMissing_thenAssertConstraintViolation() {
        Employee employee = new Employee(1, LocalDate.now(), "Employee", BigDecimal.valueOf(1000), company);
        List<String> messages = validate(employee);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Category cannot be empty."));
    }

    @Test
    public void whenSalaryIsMissing_thenAssertConstraintViolation() {
        Employee employee = new Employee(1, LocalDate.now(), "Employee", Category.BUS_DRIVER, company);
        List<String> messages = validate(employee);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Employee must have salary"));
    }

    @Test
    public void whenSalaryIsNegative_thenAssertConstraintViolation() {
        Employee employee = new Employee(1, LocalDate.now(), "Employee", Category.BUS_DRIVER, BigDecimal.valueOf(-100), company);
        List<String> messages = validate(employee);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Salary can't be zero or negative."));
    }
}