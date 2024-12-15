package org.example.dto.Route;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CreateRouteDto {
    @NotBlank(message = "Route must have a starting point.")
    private String startingPoint;
    @NotBlank(message = "Route must have a destination.")
    private String destination;
    @FutureOrPresent(message = "Starting date must be in the present or in the future.")
    private LocalDate startDate;
    @Future(message = "Delivery date must be in the future.")
    private LocalDate deliveryDate;
}