package org.example.dao;
import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.entity.Company;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;
public class CompanyDaoTest {
    private List<String> validate(Company company) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(company)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }
    @Test
    public void whenInvalidCompanyName_firstLetterNotCapital_thenAssertConstraintViolation() {
        Company company = new Company(1, LocalDate.now(), "dhl", "Test company", BigDecimal.valueOf(500));
        List<String> messages = validate(company);
        System.out.println(messages);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Company name must start with a capital letter."));
    }
    @Test
    public void whenInvalidCompanyName_nameMissing_thenAssertConstraintViolation() {
        Company company = new Company(1, LocalDate.now(), BigDecimal.valueOf(500), "Test company");
        List<String> messages = validate(company);
        System.out.println(messages);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Company must have a name."));
    }
    @Test
    public void whenInvalidCompanyName_nameIsEmptyString_thenAssertConstraintViolation() {
        Company company = new Company(1, LocalDate.now(), "", "Test company", BigDecimal.valueOf(500));
        List<String> messages = validate(company);
        System.out.println(messages);
        assertEquals(2,messages.size());
        assertTrue(messages.contains("Company must have a name."));
        assertTrue(messages.contains("Company name must start with a capital letter."));
    }
    @Test
    public void whenDescriptionMissing_thenAssertConstraintViolation() {
        Company company = new Company(1, LocalDate.now(), "DHL", BigDecimal.valueOf(500));
        List<String> messages = validate(company);
        System.out.println(messages);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Company description cannot be empty."));
    }
    @Test
    public void whenProfitIsNegative_thenAssertConstraintViolation() {
        Company company = new Company(1, LocalDate.now(), "DHL", "Test company", BigDecimal.valueOf(-500));
        List<String> messages = validate(company);
        System.out.println(messages);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Profit must not be negative number."));
    }
}