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

    public CreateRouteDto(String startingPoint, String destination, LocalDate startDate, LocalDate deliveryDate) {
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "CreateRouteDto{" +
                "starting point = " + startingPoint +
                ", destination ='" + destination + '\'' +
                ", start date =" + startDate +
                ", delivery date =" + deliveryDate +
                '}';
    }
}
