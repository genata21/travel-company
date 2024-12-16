package org.example.dao;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.entity.Company;
import org.example.entity.Vehicle;
import org.example.enums.VehicleType;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class VehicleDaoTest {
    private List<String> validate(Vehicle vehicle) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(vehicle)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }
    @Test
    public void whenInvalidVehiclePlate_plateMissing_thenAssertConstraintViolation() {
        Company company = new Company(1, LocalDate.now(), "Dhl", "Test company", BigDecimal.valueOf(500));
        Vehicle vehicle = new Vehicle(1,LocalDate.now(), VehicleType.BUS, company);
        List<String> messages = validate(vehicle);
        System.out.println(messages);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Vehicle must have a plate number"));
    }
    @Test
    public void whenVehicleTypeMissing_thenAssertConstraintViolation() {
        Company company = new Company(1, LocalDate.now(), "Dhl", "Test company", BigDecimal.valueOf(500));
        Vehicle vehicle = new Vehicle(1,LocalDate.now(), "BT2663KM", company);
        List<String> messages = validate(vehicle);
        System.out.println(messages);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Vehicle must have a type"));
    }
    @Test
    public void whenVehicleNotAssignedToCompany_thenAssertConstraintViolation() {
        Vehicle vehicle = new Vehicle(1,LocalDate.now(), "BT2663KM", VehicleType.BUS);
        List<String> messages = validate(vehicle);
        System.out.println(messages);
        assertEquals(1,messages.size());
        assertTrue(messages.contains("Vehicle must be assigned to company"));
    }
    @Test
    public void whenVehicleCreatedWithDefaultConstructor_thenAssertConstraintViolation() {
        Vehicle vehicle = new Vehicle();
        List<String> messages = validate(vehicle);
        System.out.println(messages);
        assertEquals(4,messages.size());
        assertTrue(messages.contains("Id must be positive number"));
        assertTrue(messages.contains("Vehicle must have a plate number"));
        assertTrue(messages.contains("Vehicle must have a type"));
        assertTrue(messages.contains("Vehicle must be assigned to company"));
    }
}