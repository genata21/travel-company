package org.example.dao;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.entity.*;
import org.example.enums.CargoType;
import java.math.BigDecimal;

import org.example.enums.Category;
import org.example.enums.VehicleType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteDaoTest {
    private final Company company1 = new Company(1,LocalDate.now(),"Enigma", "hotels",BigDecimal.valueOf(4));
    private final Vehicle vehicle1 = new Vehicle(1,LocalDate.now(),"VT 2108 KT",VehicleType.BUS,company1);
    private final Employee employee1 = new Employee(1,LocalDate.now(),"Evgeni", Category.BUS_DRIVER,BigDecimal.valueOf(3),company1);
    private final Client client1 = new Client(1,LocalDate.now(),"Petko",BigDecimal.valueOf(8));

    private List<String> validate(Route client) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(client)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    @Test
    public void whenStartingPointIsBlank_thenConstraintViolation() {

        Route route = new Route(
                1,
                LocalDate.now(),
                "sofia",
                LocalDate.now(),
                LocalDate.now(),
                CargoType.FUEL,
                false,
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(2),
                vehicle1,
                company1,
                employee1,
                client1);

        List<String> messages = validate(route);
        System.out.println(messages);
        assertEquals(1, messages.size());
        assertTrue(messages.contains("Starting point cannot be empty."));
    }

    @Test
    public void whenDestinationIsBlank_thenConstraintViolation() {

        Route route = new Route(
                1,
                LocalDate.now(),
                LocalDate.now(),
                LocalDate.now(),
                CargoType.FUEL,
                "sofia",
                false,
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(2),
                vehicle1,
                company1,
                employee1,
                client1);

        List<String> messages = validate(route);
        System.out.println(messages);
        assertEquals(1, messages.size());
        assertTrue(messages.contains("Destination cannot be empty."));
    }

    @Test
    public void whenStartDateIsBlank_thenConstraintViolation() {

        Route route = new Route(
                1,
                LocalDate.now(),
                "Sofia",
                "Plovdiv",
                LocalDate.now(),
                CargoType.FUEL,
                false,
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(2),
                vehicle1,
                company1,
                employee1,
                client1);


        List<String> messages = validate(route);
        System.out.println(messages);
        assertEquals(1, messages.size());
        assertTrue(messages.contains("Starting Date cannot be empty."));
    }

    @Test
    public void whenDeliveryDateIsBlank_thenConstraintViolation() {

        Route route = new Route(
                1,
                LocalDate.now(),
                "Sofia",
                "Plovdiv",
                CargoType.FUEL,
                LocalDate.now(),
                false,
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(2),
                vehicle1,
                company1,
                employee1,
                client1);


        List<String> messages = validate(route);
        System.out.println(messages);
        assertEquals(1, messages.size());
        assertTrue(messages.contains("Delivery Date cannot be empty."));
    }

    @Test
    public void whenCargoTypeIsBlank_thenConstraintViolation() {
        Route route = new Route(1,LocalDate.now(),"Sofia","Plovdiv",LocalDate.now(),LocalDate.now(),false,BigDecimal.valueOf(2), BigDecimal.valueOf(2), vehicle1, company1,employee1,client1);

        List<String> messages = validate(route);
        System.out.println(messages);
        assertEquals(1, messages.size());
        assertTrue(messages.contains("Cargo type cannot be empty."));
    }

    @Test
    public void whenIsPaidIsBlank_thenConstraintViolation() {
        Route route = new Route(
                1,
                LocalDate.now(),
                CargoType.FUEL,
                "Sofia",
                "Plovdiv",
                LocalDate.now(),
                LocalDate.now(),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(2),
                vehicle1,
                company1,
                client1,
                employee1);

        List<String> messages = validate(route);
        System.out.println(messages);
        assertEquals(1, messages.size());
        assertTrue(messages.contains("Paid cannot be empty."));
    }

    @Test
    public void whenCostIsBlank_thenConstraintViolation() {
        Route route = new Route(
                1,
                LocalDate.now(),
                "Sofia",
                "Plovdiv",
                LocalDate.now(),
                LocalDate.now(),
                CargoType.FUEL,
                false,
                BigDecimal.valueOf(2),
                vehicle1,
                company1,
                employee1,
                client1);

        List<String> messages = validate(route);
        System.out.println(messages);
        assertEquals(1, messages.size());
        assertTrue(messages.contains("Cost cannot be empty."));
    }

    @Test
    public void whenWeightIsBlank_thenConstraintViolation() {
        Route route = new Route(
                1,
                LocalDate.now(),
                "Sofia",
                "Plovdiv",
                LocalDate.now(),
                LocalDate.now(),
                CargoType.FUEL,
                false,
                vehicle1,
                company1,
                employee1,
                client1,
                BigDecimal.valueOf(2));

        List<String> messages = validate(route);
        System.out.println(messages);
        assertEquals(1, messages.size());
        assertTrue(messages.contains("Weight cannot be empty."));
    }

    @Test
    public void whenVehicleIsBlank_thenConstraintViolation() {

        Route route = new Route(
                1,
                LocalDate.now(),
                "plovdiv",
                "sofia",
                LocalDate.now(),
                LocalDate.now(),
                CargoType.FUEL,
                false,
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(2),
                company1,
                employee1,
                client1);

        List<String> messages = validate(route);
        System.out.println(messages);
        assertEquals(1, messages.size());
        assertTrue(messages.contains("Route must be have at least one vehicle"));
    }

    @Test
    public void whenCompanyIsBlank_thenConstraintViolation() {

        Route route = new Route(
                1,
                LocalDate.now(),
                "plovdiv",
                "sofia",
                LocalDate.now(),
                LocalDate.now(),
                CargoType.FUEL,
                false,
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(2),
                vehicle1,
                employee1,
                client1);

        List<String> messages = validate(route);
        System.out.println(messages);
        assertEquals(1, messages.size());
        assertTrue(messages.contains("Route must have company"));
    }

    @Test
    public void whenEmployeeIsBlank_thenConstraintViolation() {

        Route route = new Route(
                1,
                LocalDate.now(),
                "plovdiv",
                "sofia",
                LocalDate.now(),
                LocalDate.now(),
                CargoType.FUEL,
                false,
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(2),
                vehicle1,
                company1,
                client1);

        List<String> messages = validate(route);
        System.out.println(messages);
        assertEquals(1, messages.size());
        assertTrue(messages.contains("Route must be have at least one employee"));
    }

    @Test
    public void whenClientIsBlank_thenConstraintViolation() {

        Route route = new Route(
                1,
                LocalDate.now(),
                "plovdiv",
                "sofia",
                LocalDate.now(),
                LocalDate.now(),
                CargoType.FUEL,
                false,
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(2),
                vehicle1,
                company1,
                employee1);

        List<String> messages = validate(route);
        System.out.println(messages);
        assertEquals(1, messages.size());
        assertTrue(messages.contains("Route must be have at least one client"));
    }
}
