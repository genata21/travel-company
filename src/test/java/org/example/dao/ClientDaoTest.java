package org.example.dao;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.entity.Client;
import org.example.entity.Company;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientDaoTest {

    private List<String> validate(Client client) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(client)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    @Test
    public void whenInvalidClientName_firstLetterNotCapital_thenAssertConstraintViolation() {
        Client client = new Client(1, LocalDate.now(), "client");
        List<String> messages = validate(client);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Client name must start with a capital letter."));
    }

    @Test
    public void whenInvalidClientName_nameMissing_thenAssertConstraintViolation() {
        Client client = new Client(1, LocalDate.now());
        List<String> messages = validate(client);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Client must have a name."));
    }

    @Test
    public void whenDebtIsNegative_thenAssertConstraintViolation() {
        Client client = new Client(1, LocalDate.now(), "Client", BigDecimal.valueOf(-500));
        List<String> messages = validate(client);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Debt can't be negative number"));
    }
}